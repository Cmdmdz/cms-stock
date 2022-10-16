package com.cms.stockapi.service;

import com.cms.stockapi.model.request.StockRequest;
import com.cms.stockapi.model.response.StockResponse;
import com.cms.stockapi.repositories.StockRepository;
import com.cms.stockapi.repositories.dao.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.cms.stockapi.constant.CustomerConstant.ERROR_NOT_FOUND_STOCK_ID;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public Mono<StockResponse> create(StockRequest request) {

        return stockRepository.save(Stock.builder()
                        .name(request.getName())
                        .price(request.getPrice())
                        .amount(request.getAmount())
                        .type(request.getType())
                        .build())
                .flatMap(stock -> Mono.just(StockResponse.builder()
                        .stockId(stock.getStockId())
                        .amount(stock.getAmount())
                        .name(stock.getName())
                        .price(stock.getPrice())
                        .type(stock.getType())
                        .build()));
    }

    public Mono<StockResponse> update(String stockId, StockRequest request) {

        return stockRepository.findById(stockId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, ERROR_NOT_FOUND_STOCK_ID)))
                .then(stockRepository.save(Stock.builder()
                        .amount(request.getAmount())
                        .stockId(stockId)
                        .price(request.getPrice())
                        .name(request.getName())
                        .type(request.getType())
                        .build()))
                .map(stock -> StockResponse.builder()
                        .stockId(stock.getStockId())
                        .price(stock.getPrice())
                        .name(stock.getName())
                        .amount(stock.getAmount())
                        .type(stock.getType())
                        .build());

    }

    public Flux<StockResponse> findAll() {
        return stockRepository.findAll()
                .flatMap(stock -> Flux.just(StockResponse.builder()
                        .amount(stock.getAmount())
                        .name(stock.getName())
                        .stockId(stock.getStockId())
                        .price(stock.getPrice())
                        .type(stock.getType())
                        .build()));
    }

    public Mono<?> deleteById(String id) {
        return stockRepository.deleteById(id);
    }

}
