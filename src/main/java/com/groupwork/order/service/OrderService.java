package com.groupwork.order.service;

import com.groupwork.order.datasource.dto.Order;
import com.groupwork.order.datasource.dto.OrderDetail;
import com.groupwork.order.datasource.dto.ShopFood;
import com.groupwork.order.datasource.mapper.OrderDetailMapper;
import com.groupwork.order.datasource.mapper.OrderMapper;
import com.groupwork.order.datasource.mapper.ShopFoodMapper;
import com.groupwork.order.model.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ShopFoodMapper shopFoodMapper;

    public List<Order> getOrders(Long shopID){
        return orderMapper.getOrders(shopID);
    }

    public Long getFoodId(Long oid){
        List<OrderDetail> orderDetails =  orderDetailMapper.getOrdersByOid(oid);
        if(orderDetails.size() ==0)return null;
        OrderDetail orderDetail =  orderDetails.get(0);
        return orderDetail.getSfid();
    }

    public List<ShopFood> getOrderDetail(Long oid){
        List<Long> sfids = orderDetailMapper.getFoodsIdByOid(oid);
        List<ShopFood> sfs = new ArrayList<>();
        for (Long sfid : sfids) {
            sfs.add(shopFoodMapper.getFoodByID(sfid));
        }
        return sfs;
    }
}
