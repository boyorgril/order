package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.Shop;
import com.groupwork.order.datasource.dto.ShopExample;
import java.util.List;

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

    Shop selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Shop record, @Param("example") ShopExample example);

    int updateByExample(@Param("record") Shop record, @Param("example") ShopExample example);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    @Select({
            "select name,shop_Img_Url,introduce from shop where user_id = #{shopId,jdbcType=BIGINT} "
    })
    @Results({
            @Result(column="shop_img_url", property="shopImgUrl", jdbcType= JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="introduce", property="introduce", jdbcType= JdbcType.VARCHAR),
    })
    Shop getShopInfo(Long shopId);

}