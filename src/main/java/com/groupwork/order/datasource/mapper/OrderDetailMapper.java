package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.OrderDetail;
import com.groupwork.order.datasource.dto.OrderDetailExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;


public interface OrderDetailMapper {

    @Insert({
            "insert into orderDetail (oid, sfid, number) values ( #{oid,jdbcType=BIGINT}, " ,
                    " #{sfid,jdbcType=BIGINT}, #{number,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(OrderDetail record);

    @SelectProvider(type = OrderDetailSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="oid", property="oid", jdbcType= JdbcType.BIGINT),
            @Result(column="sfid", property="sfid", jdbcType= JdbcType.BIGINT),
            @Result(column="number", property="number", jdbcType= JdbcType.INTEGER),
    })
    List<OrderDetail> selectByExample(OrderDetailExample example);

    @Select({
            "select id, oid, sfid, number from orderDetail where  id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="oid", property="oid", jdbcType= JdbcType.BIGINT),
            @Result(column="sfid", property="sfid", jdbcType= JdbcType.BIGINT),
            @Result(column="number", property="number", jdbcType= JdbcType.INTEGER),
    })
    OrderDetail selectByPrimaryKey(Long id);

    @Select({
            "select id, oid, sfid, number from orderDetail where  oid = #{oid,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="oid", property="oid", jdbcType= JdbcType.BIGINT),
            @Result(column="sfid", property="sfid", jdbcType= JdbcType.BIGINT),
            @Result(column="number", property="number", jdbcType= JdbcType.INTEGER),
    })
    List<OrderDetail> getOrdersByOid(Long oid);

    @UpdateProvider(type = OrderDetailSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    @UpdateProvider(type = OrderDetailSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    @Update({
            "update orderDetail set",
            "oid = #{oid,jdbcType=BIGINT}, sfid = #{sfid,jdbcType=BIGINT}, ",
            "number = #{number,jdbcType=INTEGER} where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(OrderDetail record);
}