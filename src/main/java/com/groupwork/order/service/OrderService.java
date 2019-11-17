package com.groupwork.order.service;

import com.groupwork.order.datasource.dto.Order;
import com.groupwork.order.datasource.dto.OrderDetail;
import com.groupwork.order.datasource.dto.ShopFood;
import com.groupwork.order.datasource.mapper.OrderDetailMapper;
import com.groupwork.order.datasource.mapper.OrderMapper;
import com.groupwork.order.datasource.mapper.ShopFoodMapper;
import com.groupwork.order.model.OrderDetailFoodEntity;
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

    public void updateStatus(Long oid){
        orderMapper.updateStatus(oid);
    }
    public String getStatus(Long oid){return orderMapper.getStatus(oid);}

    public List<OrderDetailFoodEntity> getOrderDetail(Long oid){
        List<OrderDetail> orders = orderDetailMapper.getOrderDetailByOid(oid);
        List<Long> sfids = new ArrayList<>();
        for (OrderDetail order : orders) {
            sfids.add(order.getSfid());
        }
        List<OrderDetailFoodEntity> sfs = new ArrayList<>();
        int i=0;
        for (Long sfid : sfids) {
            ShopFood sf = shopFoodMapper.getFoodByID(sfid);
            OrderDetailFoodEntity odfe = getProperty(sf);
            odfe.setNumber(orders.get(i).getNumber());
            i++;
            sfs.add(odfe);
        }
        return sfs;
    }

    public OrderDetailFoodEntity getProperty(ShopFood sf){
        OrderDetailFoodEntity odfe = new OrderDetailFoodEntity();
        odfe.setId(sf.getId());
        odfe.setImgUrl(sf.getImgUrl());
        odfe.setIntroduce(sf.getIntroduce());
        odfe.setName(sf.getName());
        odfe.setPrice(sf.getPrice());
        odfe.setSaleNum(sf.getSaleNum());
        odfe.setShopId(sf.getShopId());
        return odfe;
    }
}
