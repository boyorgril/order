package com.groupwork.order.service;

import com.groupwork.order.datasource.dto.*;
import com.groupwork.order.datasource.mapper.ShopFoodMapper;
import com.groupwork.order.datasource.mapper.ShopMapper;
import com.groupwork.order.model.OrderEntity;
import com.groupwork.order.model.ShopEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ShopFoodMapper shopFoodMapper;

    public List<ShopFood> getFoods(Long shopId){
        return shopFoodMapper.getShopFoodAll(shopId);
    }

    public Shop getShopInfo(Long shopId){
        return shopMapper.getShopInfo(shopId);
    }

    public String getFoodImgByFoodId(Long sfid){
        return shopFoodMapper.getImgUrlByID(sfid);
    }

    public List<ShopEntity> allShop(){
        List<Shop> allShop = shopMapper.selectByExample(new ShopExample());
        List<ShopEntity> shopEntitys = new ArrayList<>();
        allShop.forEach(shop ->{
            ShopEntity shopEntity = new ShopEntity();
            shopEntity.setName(shop.getName());
            shopEntity.setShopImgUrl(shop.getShopImgUrl());
            shopEntity.setIntroduce(shop.getIntroduce());
            List<String> foodUrl = shopFoodMapper.shopFoodImg(shop.getId());
            if(foodUrl.size() > 6){
                shopEntity.setFoodImgUrl(foodUrl.subList(0, 5));
            }else {
                shopEntity.setFoodImgUrl(foodUrl);
            }
            shopEntitys.add(shopEntity);
        });
        return shopEntitys;
    }
}
