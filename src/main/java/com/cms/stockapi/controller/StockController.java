package com.cms.stockapi.controller;

import com.cms.stockapi.model.request.StockRequest;
import com.cms.stockapi.model.response.StockResponse;
import com.cms.stockapi.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class StockController {

    private final StockService stockService;

    @PostMapping("create")
    public Mono<StockResponse> create(@Valid @RequestBody StockRequest request){
        return stockService.create(request);

    }

    @PutMapping("update/{stockId}")
    public Mono<StockResponse> Update(@PathVariable String stockId,@Valid @RequestBody StockRequest request){
        return stockService.update(stockId,request);

    }

    @GetMapping("stock")
    public Flux<StockResponse> findAll(){
        return stockService.findAll();

    }

    @DeleteMapping("remove/{stockId}")
    public Mono<?> deleteById(@PathVariable String stockId){
        return stockService.deleteById(stockId);

    }
}
