package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.Shop;
import com.groupwork.order.datasource.dto.ShopExample;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.*;

import com.groupwork.order.datasource.dto.ShopFood;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ShopMapper {
    long countByExample(ShopExample example);

    int deleteByExample(ShopExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Shop record);

    int insertSelective(Shop record);

    @SelectProvider(type = ShopSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="user_id", property="userId", jdbcType= JdbcType.BIGINT),
            @Result(column="shop_img_url", property="shopImgUrl", jdbcType= JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="introduce", property="introduce", jdbcType= JdbcType.VARCHAR),
    })
    List<Shop> selectByExample(ShopExample example);


    @Select({
            "select id from shop where user_id = #{user_id,jdbcType=BIGINT}"
    })
    Long getIdByUserId(Long user_id);


    @Select({
            "select id, user_id, shop_img_url, name, introduce from shop where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="user_id", property="userId", jdbcType= JdbcType.BIGINT),
            @Result(column="shop_img_url", property="shopImgUrl", jdbcType= JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="introduce", property="introduce", jdbcType= JdbcType.VARCHAR),
    })
    Shop selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Shop record, @Param("example") ShopExample example);

    int updateByExample(@Param("record") Shop record, @Param("example") ShopExample example);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    @Select({
            "select name,shop_Img_Url,introduce from shop where id = #{shopId,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column="shop_img_url", property="shopImgUrl", jdbcType= JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="introduce", property="introduce", jdbcType= JdbcType.VARCHAR),
    })
    Shop getShopInfo(Long shopId);

    @Insert({
            "insert into shop(user_id,name,shop_img_url,introduce) value(#{shopId,jdbcType=BIGINT},#{name,jdbcType=VARCHAR},#{imgUrl,jdbcType=VARCHAR},#{introduce,jdbcType=VARCHAR})"
    })
    int createShop(Long shopId,String name,String imgUrl,String introduce);

    @Select({
            "select name from shop where id = #{shopId,jdbcType=BIGINT}"
    })
    String getShopName(Long shopId);

    @Update({
            "update shop set name = #{name,jdbcType=VARCHAR},shop_Img_Url = #{shop_Img_Url,jdbcType=VARCHAR},introduce = #{introduce,jdbcType=VARCHAR} where id = #{shopId,jdbcType=BIGINT}"
    })
    int updateInfo(String name,String shop_Img_Url,String introduce,Long shopId);

    @Update({
            "update shop set shop_Img_Url = #{shop_Img_Url,jdbcType=VARCHAR} where id = #{shopId,jdbcType=BIGINT}"
    })
    int updatePic(String shop_Img_Url,Long shopId);

    @Select({
        "select shop_Img_Url from shop where id = #{shopId,jdbcType=BIGINT}"
    })
    String getImg(Long shopId);
}