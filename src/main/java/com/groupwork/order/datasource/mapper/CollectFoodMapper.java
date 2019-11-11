package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.CollectFood;
import com.groupwork.order.datasource.dto.CollectFoodExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;


public interface CollectFoodMapper {
    @SelectProvider(type=CollectFoodSqlProvider.class, method = "countByExample")
    long countByExample(CollectFoodExample example);

    @InsertProvider(type=CollectFoodSqlProvider.class, method = "insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(CollectFood record);

    @SelectProvider(type=CollectFoodSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="sfid", property="sfid", jdbcType= JdbcType.BIGINT),
            @Result(column="userId", property="userId", jdbcType= JdbcType.BIGINT),
            @Result(column="createAt", property="createAt", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR)
    })
    List<CollectFood> selectByExample(CollectFoodExample example);

    @SelectProvider(type=CollectFoodSqlProvider.class, method = "selectById")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="sfid", property="sfid", jdbcType= JdbcType.BIGINT),
            @Result(column="userId", property="userId", jdbcType= JdbcType.BIGINT),
            @Result(column="createAt", property="createAt", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR)
    })
    CollectFood selectByPrimaryKey(Long id);

    @UpdateProvider(type=CollectFoodSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record")CollectFood record,  @Param("example") CollectFoodExample example);

    @UpdateProvider(type=CollectFoodSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record")CollectFood record,  @Param("example") CollectFoodExample example);

    @UpdateProvider(type=CollectFoodSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CollectFood record);

    @Update({
            "update collectFood set ",
            "sfid = #{sfid,jdbcType=BIGINT}, ",
            "userId = #{userId,jdbcType=BIGINT}, ",
            "createdAt = #{createdAt,jdbcType=TIMESTAMP}, ",
            "status = #{status,jdbcType=VARCHAR} ",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CollectFood record);
}