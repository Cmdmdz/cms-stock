package com.cms.stockapi.controller;

import com.cms.stockapi.model.request.CustomerRequest;
import com.cms.stockapi.model.response.CustomerResponse;
import com.cms.stockapi.service.SignInService;
import com.cms.stockapi.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {

    private final SignInService signInService;
    private final SignUpService signUpService;

    @PostMapping("signUp")
    public Mono<CustomerResponse> signUp(@Valid @RequestBody CustomerRequest request){
        return signUpService.execute(request);
    }

    @PostMapping("signIn")
    public Mono<CustomerResponse> signIn(@Valid @RequestBody CustomerRequest request){
        return signInService.execute(request);

    }

}
