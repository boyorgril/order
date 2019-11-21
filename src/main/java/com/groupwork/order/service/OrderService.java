package com.groupwork.order.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.groupwork.order.datasource.dto.*;
import com.groupwork.order.datasource.mapper.*;
import com.groupwork.order.model.CommentEntity;
import com.groupwork.order.model.OrderCountEntity;
import com.groupwork.order.model.OrderDetailFoodEntity;
import com.groupwork.order.model.OrderFood;
import com.groupwork.order.datasource.dto.Order;
import com.groupwork.order.datasource.dto.OrderDetail;
import com.groupwork.order.datasource.dto.ShopFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private ShopFoodMapper shopFoodMapper;
    @Autowired
    private CommentMapper commentMapper;

    public Order saveOrder(Long buyId, Long sellId, double totalMoney){
        Order order = new Order();
        order.setBuyId(buyId);
        order.setSellId(sellId);
        order.setTotalMoney(new BigDecimal(totalMoney));
        order.setCreateAt(new Date());
        order.setUpdateWhen(new Date());
        orderMapper.insert(order);
        return order;
    }

    public List<OrderDetail> saveOrderDetail(Long orderId, String orderDetail){
        List<OrderDetail> detailList = new ArrayList<>();
        JSONArray obj = JSONObject.parseArray(orderDetail);
        for (int i = 0; i < obj.size(); i++) {
            OrderDetail detail = new OrderDetail();
            JSONObject job = obj.getJSONObject(i);
            detail.setOid(orderId);
            detail.setSfid(new BigDecimal(job.get("foodId").toString()).longValue());
            if(new BigDecimal(job.get("number").toString()).intValue() != 0){
                detail.setNumber(new BigDecimal(job.get("number").toString()).intValue());
                orderDetailMapper.insert(detail);
                detailList.add(detail);
            }
        }
        return detailList;
    }


    public OrderCountEntity buildModel(Order order, List<OrderDetail> detailList) {
        OrderCountEntity orderCountEntity = new OrderCountEntity();
        orderCountEntity.setOrderId(order.getId());
        orderCountEntity.setTotalMoney(order.getTotalMoney().doubleValue());

        AddressExample example = new AddressExample();
        example.createCriteria().andUserIdEqualTo(order.getBuyId());
        List<Address> addresses = addressMapper.selectByExample(example);
        List<OrderFood> foods = buildFoods(detailList);

        orderCountEntity.setOrderFoods(foods);
        orderCountEntity.setUserAdderss(addresses);

        return orderCountEntity;
    }

    public List<OrderFood> buildFoods(List<OrderDetail> detailList){
        List<OrderFood> orderFoods = new ArrayList<>();
        for (int i = 0; i < detailList.size() ; i++) {
            OrderFood orderFood = new OrderFood();
            ShopFood shopFood = shopFoodMapper.selectById(detailList.get(i).getSfid());
            orderFood.convert(shopFood);
            orderFood.setNumber(detailList.get(i).getNumber());
            orderFoods.add(orderFood);
        }
        return orderFoods;
    }

    public List<Order> getOrders(Long shopID){
        return orderMapper.getOrders(shopID);
    }

    public void updateStatus(Long oid){
        orderMapper.updateStatus(oid);
    }
    public String getStatus(Long oid){return orderMapper.getStatus(oid);}

    public Long getFoodId(Long oid){
        List<OrderDetail> orderDetails =  orderDetailMapper.getOrdersByOid(oid);
        if(orderDetails.size() ==0)return null;
        OrderDetail orderDetail =  orderDetails.get(0);
        return orderDetail.getSfid();
    }

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

    public List<Order> getUserOrders(Long userId) {
        return orderMapper.getUserOrders(userId);
    }

    public void saveOrderAddressInfo(String orderNum, String addressId) {
        orderMapper.saveOrderAddress(new BigDecimal(orderNum).longValue(), new BigDecimal(addressId).longValue(), "NOCOMPLETE", new Date());
    }

    public CommentEntity findOrderComment(Long orderId){
        CommentEntity entity = new CommentEntity();
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andOrderIdEqualTo(orderId);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments.size() > 0){
            entity.setComment(comments.get(0).getContent());
        }
        return entity;
    }

    public void insertComment(Long userId, Long orderId, String content){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setOrderId(orderId);
        comment.setUserId(userId);
        comment.setCreateAt(new Date());
        commentMapper.insert(comment);
    }

}
