package com.cms.stockapi.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {

    private String customerId;
    private String username;
    private String password;
    private String auth;
}
