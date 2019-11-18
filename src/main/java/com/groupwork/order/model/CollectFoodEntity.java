package com.groupwork.order.model;

import com.groupwork.order.datasource.dto.ShopFood;
import lombok.Data;

import java.util.Date;

@Data
public class CollectFoodEntity {

    private String name;
    private String imgUrl;
    private String introduce;
    private Date collectDate;

    public void convert(ShopFood food){
        this.name = food.getName();
        this.imgUrl = food.getImgUrl();
        this.introduce = food.getIntroduce();
    }
}
