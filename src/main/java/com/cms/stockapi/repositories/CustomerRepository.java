package com.cms.stockapi.repositories;

import com.cms.stockapi.repositories.dao.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer,String> {

    Mono<Boolean> existsByUsername(String username);

    Mono<Boolean> existsByUsernameAndPassword(String username, String password);

    Mono<Customer> findByUsername(String username);
}
