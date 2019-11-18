package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.User;
import com.groupwork.order.datasource.dto.UserExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    @Select({
            "select userName, imgUrl, money from user where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column = "userName", property = "userName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "imgUrl", property = "imgUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "money", property = "money", jdbcType = JdbcType.DOUBLE)
    })
    User selectByPrimaryKey(Long id);

    @UpdateProvider(type = UserSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select({
            "select shop_img_url,name,introduce from shop"
    })
    @Results({
            @Result(column="shop_img_url", property="imgUrl", jdbcType= JdbcType.VARCHAR, id=true),
            @Result(column = "name", property = "userName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "introduce", property = "type", jdbcType = JdbcType.VARCHAR),
    })
    List<User> selectAllShop();
}