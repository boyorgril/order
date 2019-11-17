package com.groupwork.order.model;

import com.groupwork.order.datasource.dto.ShopFood;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShopFoodEntity {

    private Long foodId;
    private String name;
    private String foodUrl;
    private String introduce;
    private Double price;
    private Integer saleNum;


    public ShopFoodEntity(ShopFood shopFood){
        this.foodId = shopFood.getId();
        this.name = shopFood.getName();
        this.foodUrl = shopFood.getImgUrl();
        this.introduce = shopFood.getIntroduce();
        this.price = shopFood.getPrice().doubleValue();
        this.saleNum = shopFood.getSaleNum();
    }

}
