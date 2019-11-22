package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.User;
import com.groupwork.order.datasource.dto.UserExample;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

public interface UserMapper {

    @Update({
         "update user set money = money - #{orderMoney,jdbcType=DOUBLE} where id = #{userId,jdbcType=BIGINT}"
    })
    int updateUserMoney(double orderMoney, Long userId);

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

    @Select({
            "select money from user where id = #{id,jdbcType=BIGINT}"
    })
    BigDecimal getMoneyById(Long id);

    @Update({
            "update user set money = #{money,jdbcType=DECIMAL} where id = #{userId,jdbcType=BIGINT}"
    })
    int updateMoney(BigDecimal money,Long userId);
}