package com.groupwork.order.model;

import com.groupwork.order.datasource.dto.ShopFood;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderFood {


    private String name;
    private String imgUrl;
    private String introduce;
    private BigDecimal price;
    private Integer number;

    public void convert(ShopFood shopFood){
        this.name = shopFood.getName();
        this.imgUrl = shopFood.getImgUrl();
        this.introduce = shopFood.getIntroduce();
        this.price = shopFood.getPrice();
    }

}
