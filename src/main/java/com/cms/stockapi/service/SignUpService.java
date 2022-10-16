package com.cms.stockapi.service;

import com.cms.stockapi.model.request.CustomerRequest;
import com.cms.stockapi.model.response.CustomerResponse;
import com.cms.stockapi.repositories.CustomerRepository;
import com.cms.stockapi.repositories.dao.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import static com.cms.stockapi.constant.CustomerConstant.ERROR_CUSTOMER_DUPLICATE;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final CustomerRepository customerRepository;

    public Mono<CustomerResponse> execute(CustomerRequest request){

        return customerRepository.existsByUsername(request.getUsername())
                .flatMap(res -> {

                    if (Boolean.TRUE.equals(res)){
                        return Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, ERROR_CUSTOMER_DUPLICATE));
                    }

                    return customerRepository.save(Customer.builder()
                                    .username(request.getUsername())
                                    .password(request.getPassword())
                                    .build())
                            .flatMap(customer -> Mono.just(CustomerResponse.builder()
                                            .customerId(customer.getCustomerId())
                                            .username(customer.getUsername())
                                            .password(customer.getPassword())
                                    .build()));
                });
    }

}
