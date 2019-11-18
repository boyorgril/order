package com.groupwork.order.model;

import com.groupwork.order.datasource.dto.User;
import lombok.Data;

@Data
public class UserEntity {

    private String name;
    private String imgUrl;

    public void convert(User user){
        this.name = user.getUserName();
        this.imgUrl = user.getImgUrl();
    }

}
