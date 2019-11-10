package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.CollectFood;
import com.groupwork.order.datasource.dto.CollectFoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface CollectFoodMapper {
    long countByExample(CollectFoodExample example);

    int deleteByExample(CollectFoodExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CollectFood record);

    int insertSelective(CollectFood record);

    List<CollectFood> selectByExample(CollectFoodExample example);

    CollectFood selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CollectFood record, @Param("example") CollectFoodExample example);

    int updateByExample(@Param("record") CollectFood record, @Param("example") CollectFoodExample example);

    int updateByPrimaryKeySelective(CollectFood record);

    int updateByPrimaryKey(CollectFood record);
}