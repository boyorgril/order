package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.ShopFood;
import com.groupwork.order.datasource.dto.ShopFoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface ShopFoodMapper {
    long countByExample(ShopFoodExample example);

    int deleteByExample(ShopFoodExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopFood record);

    int insertSelective(ShopFood record);

    List<ShopFood> selectByExample(ShopFoodExample example);

    ShopFood selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopFood record, @Param("example") ShopFoodExample example);

    int updateByExample(@Param("record") ShopFood record, @Param("example") ShopFoodExample example);

    int updateByPrimaryKeySelective(ShopFood record);

    int updateByPrimaryKey(ShopFood record);
}