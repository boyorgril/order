package com.groupwork.order.model;

import com.groupwork.order.datasource.dto.CollectShop;
import com.groupwork.order.datasource.dto.Shop;
import lombok.Data;

import java.util.Date;

@Data
public class CollectShopEntity {

    private Long shopId;
    private String name;
    private String imgUrl;
    private Date collectDate;
    private String introduce;

    public void convert(Shop shop){
        this.shopId = shop.getId();
        this.name = shop.getName();
        this.imgUrl = shop.getShopImgUrl();
        this.introduce =shop.getIntroduce();
    }

}
