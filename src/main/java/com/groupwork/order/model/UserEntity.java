package com.groupwork.order.model;

import com.groupwork.order.datasource.dto.Address;
import com.groupwork.order.datasource.dto.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UserEntity {

    private String name;
    private String imgUrl;
    private Double money;
    private List<Address> userAdderss;

    public void convert(User user){
        this.name = user.getUserName();
        this.imgUrl = user.getImgUrl();
        this.money = user.getMoney();
    }

}
