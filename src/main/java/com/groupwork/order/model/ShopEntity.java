package com.groupwork.order.model;

import lombok.Data;

import java.util.List;

@Data
public class ShopEntity {
    private String name;
    private String shopImgUrl;
    private String introduce;
    private List<String> foodImgUrl;
}
