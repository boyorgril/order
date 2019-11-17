package com.groupwork.order.model;

import com.groupwork.order.datasource.dto.Shop;
import lombok.Data;

import java.util.List;

@Data
public class ShopEntity {


    private Long shopId;
    private String name;
    private String shopImgUrl;
    private String introduce;
    private List<String> foodImgUrl;

    public void convert(Shop shop){
        this.shopId = shop.getId();
        this.name = shop.getName();
        this.shopImgUrl = shop.getShopImgUrl();
        this.introduce = shop.getIntroduce();
    }

}
