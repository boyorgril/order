package com.groupwork.order.service;

import com.groupwork.order.datasource.dto.*;
import com.groupwork.order.datasource.mapper.CollectFoodMapper;
import com.groupwork.order.datasource.mapper.CollectShopMapper;
import com.groupwork.order.datasource.mapper.ShopFoodMapper;
import com.groupwork.order.datasource.mapper.ShopMapper;
import com.groupwork.order.model.OrderEntity;
import com.groupwork.order.model.ShopEntity;
import com.groupwork.order.model.ShopFoodEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ShopFoodMapper shopFoodMapper;
    @Autowired
    private CollectShopMapper collectShopMapper;
    @Autowired
    private CollectFoodMapper collectFoodMapper;

    public List<ShopFood> getFoods(Long shopId){
        return shopFoodMapper.getShopFoodAll(shopId);
    }

    public String getShopName(Long shopId){
        return shopMapper.getShopName(shopId);
    }

    public void createShop(Long shopId,String name,String imgUrl,String introduce){
        shopMapper.createShop(shopId,name,imgUrl,introduce);
    }

    public Shop getShopInfo(Long shopId){
        return shopMapper.getShopInfo(shopId);
    }

    public void updateShopFood(String name, String imgUrl, BigDecimal price, String introduce, Long sfid){
        shopFoodMapper.updateShopFood(name,imgUrl,price,introduce,sfid);
    }

    public void addShopFood(String name,String imgUrl,BigDecimal price,String introduce,Long shopId){
        shopFoodMapper.addShopFood(name,imgUrl,price,introduce,shopId);
    }

    public String getFoodImgByFoodId(Long sfid){
        return shopFoodMapper.getImgUrlByID(sfid);
    }

    public String getShopImgByid(Long shopId){
        return shopMapper.getImg(shopId);
    }

    public ShopFood getFoodById(Long id){
        return shopFoodMapper.getFoodByID(id);
    }

    public void dropFood(Long sfId) {
        shopFoodMapper.dropFoodById(sfId);
    }

    public void updateInfo(String name,String shop_Img_Url,String introduce,Long shopId){
        shopMapper.updateInfo(name,shop_Img_Url,introduce,shopId);
    }

    public void updatePic(String shop_Img_Url,Long shopId){
        shopMapper.updatePic(shop_Img_Url,shopId);
    }

    public void updateFoodPic(String shop_Img_Url,Long shopId){
        shopFoodMapper.updatePic(shop_Img_Url,shopId);
    }

    public List<ShopEntity> allShop(Long userId){
        List<Shop> allShop = shopMapper.selectByExample(new ShopExample());
        List<ShopEntity> shopEntitys = new ArrayList<>();
        allShop.forEach(shop ->{
            ShopEntity shopEntity = new ShopEntity();
            shopEntity.setShopId(shop.getId());
            shopEntity.setShopOwnerId(shop.getUserId());
            shopEntity.setName(shop.getName());
            shopEntity.setShopImgUrl(shop.getShopImgUrl());
            shopEntity.setIntroduce(shop.getIntroduce());
            List<String> foodUrl = shopFoodMapper.shopFoodImg(shop.getId());
            if(foodUrl.size() > 6){
                shopEntity.setFoodImgUrl(foodUrl.subList(0, 5));
            }else {
                shopEntity.setFoodImgUrl(foodUrl);
            }

            CollectShopExample shopExample = new CollectShopExample();
            shopExample.createCriteria().andUserIdEqualTo(userId).andShopIdEqualTo(shop.getId())
                .andStatusEqualTo("COLLECT");
            Long count = collectShopMapper.countByExample(shopExample);
            if (count > 0){
                shopEntity.setCollected("COLLECT");
            }else{
                shopEntity.setCollected("UNCOLLECT");
            }

            shopEntitys.add(shopEntity);
        });
        return shopEntitys;
    }

    public List<ShopFoodEntity> shopFoodByShopId(Long shopId, Long userId){
        ShopFoodExample example = new ShopFoodExample();
        example.createCriteria().andShopIdEqualTo(shopId);
        List<ShopFood> shopFoods = shopFoodMapper.selectByExample(example);
        if(shopFoods.size() == 0){
            return null;
        }
        List<ShopFoodEntity> entities = new ArrayList<>();
        shopFoods.forEach(food ->{
            ShopFoodEntity entity = new ShopFoodEntity();
            entity.convert(food);

            CollectFoodExample foodExample = new CollectFoodExample();
            foodExample.createCriteria().andUserIdEqualTo(userId).andSfidEqualTo(food.getId())
                    .andStatusEqualTo("COLLECT");
            Long count = collectFoodMapper.countByExample(foodExample);
            if (count > 0){
                entity.setCollected("COLLECT");
            }else{
                entity.setCollected("UNCOLLECT");
            }

            entities.add(entity);
        });
        return entities;
    }

    public ShopEntity findShopById(Long shopId) {
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.convert(shopMapper.selectByPrimaryKey(shopId));
        return shopEntity;
    }
}
