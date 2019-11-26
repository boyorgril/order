package com.groupwork.order.service;

import com.groupwork.order.datasource.dto.Address;
import com.groupwork.order.datasource.dto.AddressExample;
import com.groupwork.order.datasource.dto.Order;
import com.groupwork.order.datasource.dto.OrderDetail;
import com.groupwork.order.datasource.mapper.AddressMapper;
import com.groupwork.order.datasource.mapper.OrderDetailMapper;
import com.groupwork.order.datasource.mapper.OrderMapper;
import com.groupwork.order.datasource.mapper.ShopFoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 通过地址id获取地址信息
     * @param addressId
     * @return
     */
    public Address getAddress(Long addressId){
        return addressMapper.getAddressById(addressId);
    }

    /**
     * 更新地址信息
     * @param address
     */
    public void updateAddress(Address address){
        addressMapper.updateByPrimaryKey(address);
    }

    /**
     * 插入地址信息
     * @param address
     */
    public void insertAddress(Address address){
        addressMapper.insert(address);
    }

    /**
     * 插入或更新地址信息
     * @param address
     */
    public void insertOrUpdateAddr(Address address) {

        if (StringUtils.isEmpty(address.getId())){
            insertAddress(address);
        }{
            updateAddress(address);
        }

    }
}