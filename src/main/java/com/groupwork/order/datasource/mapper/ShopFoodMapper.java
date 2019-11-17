package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.ShopFood;
import com.groupwork.order.datasource.dto.ShopFoodExample;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;


public interface ShopFoodMapper {

    @Select({
            "select imgUrl from shopfood where shopId = #{shopId,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column="imgUrl", property="imgUrl", jdbcType= JdbcType.VARCHAR),
    })
    List<String> shopFoodImg(Long shopId);

    @SelectProvider(type = ShopFoodSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="shopId", property="shopId", jdbcType= JdbcType.BIGINT),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="imgUrl", property="imgUrl", jdbcType= JdbcType.VARCHAR),
            @Result(column="price", property="price", jdbcType= JdbcType.DECIMAL),
            @Result(column="saleNum", property="saleNum", jdbcType= JdbcType.INTEGER),
            @Result(column="introduce", property="introduce", jdbcType= JdbcType.INTEGER),
            @Result(column="createAt", property="createAt", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="updateWhen", property="updateWhen", jdbcType= JdbcType.TIMESTAMP)
    })
    List<ShopFood> selectByExample(ShopFoodExample shopFoodExample);

    @Select({
            "select id, shopId, name, imgUrl, price, saleNum, introduce, createAt, updateWhen",
            "from shopfood where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="shopId", property="shopId", jdbcType= JdbcType.BIGINT),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="imgUrl", property="imgUrl", jdbcType= JdbcType.VARCHAR),
            @Result(column="price", property="price", jdbcType= JdbcType.DECIMAL),
            @Result(column="saleNum", property="saleNum", jdbcType= JdbcType.INTEGER),
            @Result(column="introduce", property="introduce", jdbcType= JdbcType.INTEGER),
            @Result(column="createAt", property="createAt", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="updateWhen", property="updateWhen", jdbcType= JdbcType.TIMESTAMP)
    })
    ShopFood selectById(Long id);

    @Select({
            "select id,name,imgUrl,introduce from shopfood where shopId = #{shopId,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT,id=true),
            @Result(column="imgUrl", property="imgUrl", jdbcType= JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="introduce", property="introduce", jdbcType= JdbcType.VARCHAR),
    })
    List<ShopFood> getShopFoodAll(Long shopId);

    @Select({
            "select imgUrl from shopfood where id = #{id,jdbcType=BIGINT}"
    })
    String getImgUrlByID(Long id);

    @Select({
            "select id,shopId,name,imgUrl,price,saleNum,introduce from shopfood where id = #{id,jdbcType=BIGINT}"
    })
    ShopFood getFoodByID(Long id);

    //没写完
    @Update({
            "update shopfood set name = #{name,jdbcType=VARCHAR},imgUrl = #{imgUrl,jdbcType=VARCHAR},price = #{price,jdbcType=BIGINT},introduce"
    })
    int updateShopFood(String name,String imgUrl,String intrduce,BigDecimal price ,Long sfid);
}