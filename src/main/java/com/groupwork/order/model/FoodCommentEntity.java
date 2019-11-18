package com.groupwork.order.model;

import lombok.Data;

import java.util.List;

@Data
public class FoodCommentEntity {

    private String foodImgUrl;
    private String name;
    private String introduce;
    List<CommentEntity> userComment;

}
