package com.cms.stockapi.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockResponse {

    private String stockId;
    private String name;
    private Integer price;
    private Integer amount;
    private String type;
}
