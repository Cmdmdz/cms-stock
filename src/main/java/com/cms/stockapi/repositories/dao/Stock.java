package com.cms.stockapi.repositories.dao;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stock {

    @Id
    private String stockId;
    private String name;
    private Integer price;
    private Integer amount;
    private String type;

}
