package com.groupwork.order.datasource.dto;

import java.io.Serializable;

/**
 * shop
 * @author 
 */
public class Shop implements Serializable {
    private Long id;

    /**
     * 商家拥有者
     */
    private Long userId;

    /**
     * 图标地址
     */
    private String shopImgUrl;

    /**
     * 商铺名称
     */
    private String name;

    /**
     * 简介
     */
    private String introduce;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getShopImgUrl() {
        return shopImgUrl;
    }

    public void setShopImgUrl(String shopImgUrl) {
        this.shopImgUrl = shopImgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}