package com.groupwork.order.datasource.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * colleatshop
 * @author 
 */
public class CollectShop implements Serializable {
    private Long id;

    private Long userId;

    private Long shopId;

    private Date createAt;

    /**
     * 状态
     */
    private String status;

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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}