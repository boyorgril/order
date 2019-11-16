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
}