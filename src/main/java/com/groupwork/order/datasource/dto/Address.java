package com.groupwork.order.datasource.dto;

import java.io.Serializable;

/**
 * address
 * @author 
 */
public class Address implements Serializable {
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 地址
     */
    private String location;

    /**
     * 电话
     */
    private String phoneNumber;

    /**
     * 收货人姓名
     */
    private String name;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}