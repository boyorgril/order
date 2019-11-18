package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.Comment;
import com.groupwork.order.datasource.dto.CommentExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;


public interface CommentMapper {

    @Delete({
            "delete from comment where id = #{id,jdbcType=BIGINT} "
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into comment (order_id, user_id, content, create_at) values ",
            "(#{orderId,jdbcType=BIGINT}, #{userId,jdbcType=BIGINT}, ",
            " #{content,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP}) "
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Comment record);

    @SelectProvider(type = CommentSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="order_id", property="orderId", jdbcType= JdbcType.BIGINT),
            @Result(column="user_id", property="userId", jdbcType= JdbcType.BIGINT),
            @Result(column="content", property="content", jdbcType= JdbcType.VARCHAR),
            @Result(column="create_at", property="createAt", jdbcType= JdbcType.TIMESTAMP)
    })
    List<Comment> selectByExample(CommentExample example);

    @Select({
            "select id, user_id, order_id, content, create_at from comment ",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="user_id", property="userId", jdbcType= JdbcType.BIGINT),
            @Result(column="order_id", property="orderId", jdbcType= JdbcType.BIGINT),
            @Result(column="content", property="content", jdbcType= JdbcType.VARCHAR),
            @Result(column="create_at", property="createAt", jdbcType= JdbcType.TIMESTAMP)
    })
    Comment selectByPrimaryKey(Long id);

}