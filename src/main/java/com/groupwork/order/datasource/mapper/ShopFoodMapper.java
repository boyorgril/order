package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.ShopFood;
import com.groupwork.order.datasource.dto.ShopFoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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
}