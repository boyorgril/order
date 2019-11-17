package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.Order;
import com.groupwork.order.datasource.dto.OrderExample;
import java.util.List;

import com.groupwork.order.datasource.dto.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;


public interface OrderMapper {

    @Select({
            "select id,status,buyId,addressId,totalMoney from `order` where sellId = #{sellId,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR),
            @Result(column = "addressId", property = "addressId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "totalMoney", property = "totalMoney", jdbcType = JdbcType.VARCHAR),
    })
    List<Order> getOrders(Long sellId);

    @Select({
            "Select status from `order` where id = #{oid,jdbcType=BIGINT}"
    })
    String getStatus(Long oid);

    @Update({
            "update `order` set status = 'COMPLETE' where id = #{oid,jdbcType=BIGINT}"
    })
    int updateStatus(Long oid);

    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}