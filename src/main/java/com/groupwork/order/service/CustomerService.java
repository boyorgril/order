package com.groupwork.order.service;

import com.groupwork.order.datasource.dto.*;
import com.groupwork.order.datasource.mapper.*;
import com.groupwork.order.model.CollectFoodEntity;
import com.groupwork.order.model.CollectShopEntity;
import com.groupwork.order.model.UserEntity;
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

    public void updateCollectShopStatus(Long userId, long shopId, String status) {
        CollectShopExample example = new CollectShopExample();
        example.createCriteria().andUserIdEqualTo(userId).andShopIdEqualTo(shopId);
        CollectShop collectShop = new CollectShop();
        collectShop.setStatus(status);
        collectShopMapper.updateByExampleSelective(collectShop,example);
    }

    public void updateCollectFoodStatus(Long userId, long foodId, String status) {
        CollectFoodExample example = new CollectFoodExample();
        example.createCriteria().andUserIdEqualTo(userId).andSfidEqualTo(foodId);
        CollectFood collectFood = new CollectFood();
        collectFood.setStatus(status);
        collectFoodMapper.updateByExampleSelective(collectFood,example);
    }

    public UserEntity findUserInfo(Long userId) {
        UserEntity entity = new UserEntity();
        entity.convert(userMapper.selectByPrimaryKey(userId));
        return entity;
    }
}
