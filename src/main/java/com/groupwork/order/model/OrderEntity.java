package com.groupwork.order.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderEntity {
    private String imgUrl;
    private BigDecimal totalMoney;
    private String who;
    private String address;
    private String status;
}
