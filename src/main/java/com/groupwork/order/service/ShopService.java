package com.groupwork.order.service;

import com.groupwork.order.datasource.dto.*;
import com.groupwork.order.datasource.mapper.*;
import com.groupwork.order.model.OrderEntity;
import com.groupwork.order.model.ShopEntity;
import com.groupwork.order.model.ShopFoodEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private UserMapper userMapper;

    /**
     * 取得店铺的所有菜品信息
     * @param shopId
     * @return
     */
    public List<ShopFood> getFoods(Long shopId){
        return shopFoodMapper.getShopFoodAll(shopId);
    }

    /**
     * 通过店铺id取得店铺名
     * @param shopId
     * @return
     */
    public String getShopName(Long shopId){
        return shopMapper.getShopName(shopId);
    }

    /**
     * 创建店铺
     * @param shopId
     * @param name
     * @param imgUrl
     * @param introduce
     */
    public void createShop(Long shopId,String name,String imgUrl,String introduce){
        shopMapper.createShop(shopId,name,imgUrl,introduce);
    }

    /**
     * 查询余额
     * @param id
     * @return
     */
    public BigDecimal getMoney(Long id){
        return userMapper.getMoneyById(id);
    }

    /**
     * 修改余额
     * @param money
     * @param userId
     */
    public void updateMoney(BigDecimal money,Long userId){
        userMapper.updateMoney(money,userId);
    }

    /**
     * 取得店铺信息
     * @param shopId
     * @return
     */
    public Shop getShopInfo(Long shopId){
        return shopMapper.getShopInfo(shopId);
    }

    /**
     * 更新菜品信息
     * @param name
     * @param imgUrl
     * @param price
     * @param introduce
     * @param sfid
     */
    public void updateShopFood(String name, String imgUrl, BigDecimal price, String introduce, Long sfid){
        shopFoodMapper.updateShopFood(name,imgUrl,price,introduce,sfid,new Date());
    }

    /**
     * 添加菜品
     * @param name
     * @param imgUrl
     * @param price
     * @param introduce
     * @param shopId
     */
    public void addShopFood(String name,String imgUrl,BigDecimal price,String introduce,Long shopId){
        shopFoodMapper.addShopFood(name,imgUrl,price,introduce,shopId,new Date());
    }

    /**
     * 通过菜品id取得菜品图标
     * @param sfid
     * @return
     */
    public String getFoodImgByFoodId(Long sfid){
        return shopFoodMapper.getImgUrlByID(sfid);
    }

    /**
     * 通过店铺id取得店铺图标
     * @param shopId
     * @return
     */
    public String getShopImgByid(Long shopId){
        return shopMapper.getImg(shopId);
    }

    /**
     * 通过菜品id取得菜品信息
     * @param id
     * @return
     */
    public ShopFood getFoodById(Long id){
        return shopFoodMapper.getFoodByID(id);
    }

    /**
     * 删除菜品
     * @param sfId
     */
    public void dropFood(Long sfId) {
        shopFoodMapper.dropFoodById(sfId);
    }

    /**
     * 更新店铺信息
     * @param name
     * @param shop_Img_Url
     * @param introduce
     * @param shopId
     */
    public void updateInfo(String name,String shop_Img_Url,String introduce,Long shopId){
        shopMapper.updateInfo(name,shop_Img_Url,introduce,shopId);
    }

    /**
     * 更新店铺图标
     * @param shop_Img_Url
     * @param shopId
     */
    public void updatePic(String shop_Img_Url,Long shopId){
        shopMapper.updatePic(shop_Img_Url,shopId);
    }

    /**
     * 更新菜品图标
     * @param shop_Img_Url
     * @param shopId
     */
    public void updateFoodPic(String shop_Img_Url,Long shopId){
        shopFoodMapper.updatePic(shop_Img_Url,shopId,new Date());
    }

    /**
     * 取得所有的店铺信息
     * @param userId
     * @return
     */
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

    /**
     * 取得店铺所有的菜品
     * @param shopId
     * @param userId
     * @return
     */
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

    /**
     * 通过店铺id取得店铺信息
     * @param shopId
     * @return
     */
    public ShopEntity findShopById(Long shopId) {
        ShopEntity shopEntity = new ShopEntity();
        shopEntity.convert(shopMapper.selectByPrimaryKey(shopId));
        return shopEntity;
    }

    /**
     * 通过店铺id取得对应用户id
     * @param shopId
     * @return
     */
    public Long getId(Long shopId){
        return shopMapper.getIdByUserId(shopId);
    }
}
