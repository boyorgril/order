package com.groupwork.order.datasource.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * shopfood
 * @author 
 */
public class ShopFood implements Serializable {
    private Long id;

    /**
     * 商家id
     */
    private Long shopId;

    /**
     * 菜名
     */
    private String name;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 销量
     */
    private Integer saleNum;

    /**
     * 介绍
     */
    private String introduce;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 更新时间
     */
    private Date updateWhen;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateWhen() {
        return updateWhen;
    }

    public void setUpdateWhen(Date updateWhen) {
        this.updateWhen = updateWhen;
    }
}