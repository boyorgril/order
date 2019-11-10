package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.CollectShop;
import com.groupwork.order.datasource.dto.CollectShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface CollectShopMapper {
    long countByExample(CollectShopExample example);

    int deleteByExample(CollectShopExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CollectShop record);

    int insertSelective(CollectShop record);

    List<CollectShop> selectByExample(CollectShopExample example);

    CollectShop selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CollectShop record, @Param("example") CollectShopExample example);

    int updateByExample(@Param("record") CollectShop record, @Param("example") CollectShopExample example);

    int updateByPrimaryKeySelective(CollectShop record);

    int updateByPrimaryKey(CollectShop record);
}