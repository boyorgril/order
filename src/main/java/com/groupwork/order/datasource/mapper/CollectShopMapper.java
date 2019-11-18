package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.CollectShop;
import com.groupwork.order.datasource.dto.CollectShopExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;


public interface CollectShopMapper {
    @SelectProvider(type = CollectShopSqlProvider.class, method = "countByExample")
    long countByExample(CollectShopExample example);

    @DeleteProvider(type = CollectShopSqlProvider.class, method = "deleteByExample")
    int deleteByExample(CollectShopExample example);

    @Delete({
            "delete from collectShop where ",
            "id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into collectShop (userId, shopId, createAt, status)",
            "values( #{userId,jdbcType=BIGINT}, #{shopId,jdbcType=BIGINT}, ",
                    "#{createAt,jdbcType=TIMESTAMP}, #{status,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(CollectShop record);


    @SelectProvider(type = CollectShopSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="userId", property="userId", jdbcType= JdbcType.BIGINT),
            @Result(column="shopId", property="shopId", jdbcType= JdbcType.BIGINT),
            @Result(column="createAt", property="createAt", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR)
    })
    List<CollectShop> selectByExample(CollectShopExample example);

    @Select({
            "select id, userId, shopId, createAt, status from collectShop where ",
            "id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="userId", property="userId", jdbcType= JdbcType.BIGINT),
            @Result(column="shopId", property="shopId", jdbcType= JdbcType.BIGINT),
            @Result(column="createAt", property="createAt", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR)
    })
    CollectShop selectByPrimaryKey(Long id);

    @Update({
            "update collectShop set ",
            "shopId = #{shopId,jdbcType=BIGINT}, ",
            "userId = #{userId,jdbcType=BIGINT}, ",
            "createdAt = #{createdAt,jdbcType=TIMESTAMP}, ",
            "status = #{status,jdbcType=VARCHAR} ",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CollectShop record);

    @UpdateProvider(type=CollectShopSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record")CollectShop record,  @Param("example") CollectShopExample example);

}