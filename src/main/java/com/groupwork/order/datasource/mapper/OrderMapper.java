package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.Order;
import com.groupwork.order.datasource.dto.OrderExample;
import java.util.List;

import com.groupwork.order.datasource.dto.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;


public interface OrderMapper {

    @Select({
            "select id,status,buyId,addressId,totalMoney from `order` where sellId = #{userId,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR),
            @Result(column = "addressId", property = "addressId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "totalMoney", property = "totalMoney", jdbcType = JdbcType.VARCHAR),
    })
    List<Order> getOrders(Long sellId);

    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into `order` (buyId, sellId, createAt, updateWhen, status, totalMoney)",
            "values (#{buyId,jdbcType=BIGINT}, #{sellId,jdbcType=BIGINT}, #{createAt,jdbcType=TIMESTAMP},",
            "#{updateWhen,jdbcType=TIMESTAMP}, #{status,jdbcType=VARCHAR}, #{totalMoney,jdbcType=DOUBLE})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}