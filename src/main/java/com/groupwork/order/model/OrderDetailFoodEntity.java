package com.groupwork.order.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailFoodEntity {
    private Long id;
    private Long shopId;
    private String name;
    private String imgUrl;
    private BigDecimal price;
    private Integer saleNum;
    private String introduce;
    private Integer number;
}
