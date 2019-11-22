package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.Order;
import com.groupwork.order.datasource.dto.OrderExample;

import java.math.BigDecimal;
import java.util.Date;
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
    @Select({
            "select id,status,buyId,addressId,totalMoney from `order` where buyId = #{userId,jdbcType=BIGINT} order by createAt DESC"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR),
            @Result(column = "addressId", property = "addressId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "totalMoney", property = "totalMoney", jdbcType = JdbcType.VARCHAR),
    })
    List<Order> getUserOrders(Long userId);

    @Select({
            "select totalMoney from `order` where id = #{oid,jdbcType=BIGINT}"
    })
    BigDecimal getOrderMoney(Long oid);

    @Update({
          "update `order` set addressId = #{addressId,jdbcType=INTEGER}, status = #{status,jdbcType=INTEGER}, payTime = #{payTime,jdbcType=TIMESTAMP} where id = #{orderId,jdbcType=BIGINT}"
    })
    int saveOrderAddress(long orderId, long addressId, String status, Date payTime);
}