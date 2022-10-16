package com.cms.stockapi.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockRequest {

    @NotBlank
    private String name;
    @NotNull
    private Integer price;
    @NotNull
    private Integer amount;

    private String type;
}
