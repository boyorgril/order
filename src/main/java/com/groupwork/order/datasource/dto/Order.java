package com.groupwork.order.datasource.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * order
 * @author 
 */
public class Order implements Serializable {
    private Long id;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 买家id
     */
    private Long buyId;

    /**
     * 卖家id
     */
    private Long sellId;

    /**
     * 收货地址id
     */
    private Long addressId;

    /**
     * 订单金额
     */
    private BigDecimal totalMoney;

    private Date createAt;

    private Date updateWhen;

    private Date payTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getBuyId() {
        return buyId;
    }

    public void setBuyId(Long buyId) {
        this.buyId = buyId;
    }

    public Long getSellId() {
        return sellId;
    }

    public void setSellId(Long sellId) {
        this.sellId = sellId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
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

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}