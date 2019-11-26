package com.groupwork.order.service;

import com.groupwork.order.datasource.dto.*;
import com.groupwork.order.datasource.mapper.*;
import com.groupwork.order.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CollectFoodMapper collectFoodMapper;
    @Autowired
    private CollectShopMapper collectShopMapper;
    @Autowired
    private ShopFoodMapper shopFoodMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private AddressMapper addressMapper;


    /**
     * 寻找店铺所有的菜品信息
     * @param userId
     * @return
     */
    public List<CollectFoodEntity> searchFood(Long userId){
        List<CollectFoodEntity> foodEntities = new ArrayList<>();

        CollectFoodExample foodExample = new CollectFoodExample();
        foodExample.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo("COLLECT");

        List<CollectFood> foods = collectFoodMapper.selectByExample(foodExample);

        foods.forEach(food -> {
            CollectFoodEntity entity = new CollectFoodEntity();
            ShopFood shopFood = shopFoodMapper.selectById(food.getSfid());
            entity.convert(shopFood);
            entity.setCollectDate(food.getCreateAt());
            foodEntities.add(entity);
        });

        return foodEntities;
    }

    /**
     * 搜寻所有店铺
     * @param userId
     * @return
     */
    public List<CollectShopEntity> searchShop(Long userId){
        List<CollectShopEntity> shopEntities = new ArrayList<>();

        CollectShopExample shopExample = new CollectShopExample();
        shopExample.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo("COLLECT");

        List<CollectShop> shopList = collectShopMapper.selectByExample(shopExample);

        shopList.forEach(collectShop -> {
            CollectShopEntity shopEntity = new CollectShopEntity();
            Shop shop = shopMapper.selectByPrimaryKey(collectShop.getShopId());
            shopEntity.convert(shop);
            shopEntity.setCollectDate(collectShop.getCreateAt());
            shopEntities.add(shopEntity);
        });

        return shopEntities;
    }

    /**
     * 收藏店铺
     * @param userId
     * @param shopId
     */
    public void collectShop(Long userId, Long shopId){
        CollectShopExample example = new CollectShopExample();
        example.createCriteria().andUserIdEqualTo(userId).andShopIdEqualTo(shopId);
        Long count = collectShopMapper.countByExample(example);

        if(count > 0){
            updateCollectShopStatus(userId, shopId, "COLLECT");
        }else{
            CollectShop shop = new CollectShop();
            shop.setShopId(shopId);
            shop.setUserId(userId);
            shop.setCreateAt(new Date());
            shop.setStatus("COLLECT");
            collectShopMapper.insert(shop);
        }
    }

    /**
     * 收藏菜品
     * @param userId
     * @param foodId
     */
    public void collectFood(Long userId, Long foodId) {

        CollectFoodExample example = new CollectFoodExample();
        example.createCriteria().andUserIdEqualTo(userId).andSfidEqualTo(foodId);
        Long count = collectFoodMapper.countByExample(example);

        if(count > 0){
            updateCollectFoodStatus(userId, foodId, "COLLECT");
        }else{
            CollectFood collectFood = new CollectFood();
            collectFood.setUserId(userId);
            collectFood.setSfid(foodId);
            collectFood.setCreateAt(new Date());
            collectFood.setStatus("COLLECT");
            collectFoodMapper.insert(collectFood);
        }


    }

    /**
     * 更新店铺收藏状态
     * @param userId
     * @param shopId
     * @param status
     */
    public void updateCollectShopStatus(Long userId, long shopId, String status) {
        CollectShopExample example = new CollectShopExample();
        example.createCriteria().andUserIdEqualTo(userId).andShopIdEqualTo(shopId);
        CollectShop collectShop = new CollectShop();
        collectShop.setStatus(status);
        collectShopMapper.updateByExampleSelective(collectShop,example);
    }

    /**
     * 更新菜品收藏状态
     * @param userId
     * @param foodId
     * @param status
     */
    public void updateCollectFoodStatus(Long userId, long foodId, String status) {
        CollectFoodExample example = new CollectFoodExample();
        example.createCriteria().andUserIdEqualTo(userId).andSfidEqualTo(foodId);
        CollectFood collectFood = new CollectFood();
        collectFood.setStatus(status);
        collectFoodMapper.updateByExampleSelective(collectFood,example);
    }

    /**
     * 取得用户信息
     * @param userId
     * @return
     */
    public UserEntity findUserInfo(Long userId) {
        UserEntity entity = new UserEntity();
        entity.convert(userMapper.selectByPrimaryKey(userId));
        return entity;
    }

    /**
     * 取得对应菜品的所有评论
     * @param foodId
     * @return
     */
    public FoodCommentEntity foodComment(Long foodId){
        FoodCommentEntity commentEntity = new FoodCommentEntity();
        ShopFood shopFood = shopFoodMapper.selectById(foodId);
        commentEntity.setFoodImgUrl(shopFood.getImgUrl());
        commentEntity.setName(shopFood.getName());
        commentEntity.setIntroduce(shopFood.getIntroduce());
        OrderDetailExample example = new OrderDetailExample();
        example.createCriteria().andSfidEqualTo(foodId);
        List<OrderDetail> orderId = orderDetailMapper.selectByExample(example);
        if(orderId.size() == 0){
            return commentEntity;
        }
        commentEntity.setUserComment(findFoodComment(orderId));

        return commentEntity;
    }

    /**
     * 取得对应订单的所有评价
     * @param orderDetails
     * @return
     */
    public List<CommentEntity> findFoodComment(List<OrderDetail> orderDetails){
        List<CommentEntity> entityList = new ArrayList<>();

        List<Long> orderIds = new ArrayList<>();
        orderDetails.forEach(orderDetail -> {
            orderIds.add(orderDetail.getOid().longValue());
        });

        CommentExample example = new CommentExample();
        example.createCriteria().andOrderIdIn(orderIds);

        List<Comment> comments = commentMapper.selectByExample(example);

        if(comments.size() == 0){
            return null;
        }

        comments.forEach(comment -> {
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setComment(comment.getContent());
            User user = userMapper.selectByPrimaryKey(comment.getUserId());
            commentEntity.setUsername(user.getUserName());
            commentEntity.setImgUrl(user.getImgUrl());
            entityList.add(commentEntity);
        });

        return entityList;
    }

    /**
     * 取得用户所有信息，包括用户所有收货地址
     * @param userId
     * @return
     */
    public UserEntity findDetailById(Long userId){
        UserEntity userEntity = findUserInfo(userId);
        AddressExample example = new AddressExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<Address> addresses = addressMapper.selectByExample(example);
        userEntity.setUserAdderss(addresses);
        return userEntity;
    }

    /**
     * 更新用户信息
     * @param user
     */
    public void updateUserInfo(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(user.getId());

        user.setUpdateWhen(new Date());
        userMapper.updateByExampleSelective(user, example);
    }

    /**
     * 查询用户余额
     * @param userId
     * @return
     */
    public double userMoney(Long userId){
        User user = userMapper.selectByPrimaryKey(userId);
        return user.getMoney();
    }

    /**
     * 更新用户的余额
     * @param orderMoney
     * @param userId
     */
    public void updateUserMoney(double orderMoney, Long userId){
        userMapper.updateUserMoney(orderMoney, userId);
    }

}
