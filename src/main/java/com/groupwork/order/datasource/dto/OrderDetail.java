package com.groupwork.order.datasource.dto;

import java.io.Serializable;

/**
 * orderdetail
 * @author 
 */
public class OrderDetail implements Serializable {
    private Long id;

    /**
     * 订单id
     */
    private Long oid;

    /**
     * 菜品id
     */
    private Long sfid;

    /**
     * 数量
     */
    private Integer number;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getSfid() {
        return sfid;
    }

    public void setSfid(Long sfid) {
        this.sfid = sfid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}