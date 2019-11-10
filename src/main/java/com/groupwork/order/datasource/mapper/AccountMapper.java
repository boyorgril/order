package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.User;
import com.groupwork.order.datasource.dto.UserExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface AccountMapper {

    @Select({
            "select id from user",
            "where account = #{account,jdbcType=VARCHAR} and password =  #{password,jdbcType=VARCHAR}"
    })
    Long selectCount(User user);

    @Insert({
            "insert into user(account, password) values (#{account,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR})"
    })
    int accountRegister(User user);

    @Select({
            "select count(account) from user ",
            "where account = #{account,jdbcType=VARCHAR} "
    })
    int findAccountNumber(String account);

    @Select({
            "select id, account, username, password from user where id = #{userId,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column = "account", property = "account", jdbcType = JdbcType.VARCHAR),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
    })
    User userInfo(Long userId);

    @SelectProvider(type=AccountSqlProvider.class, method="selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column = "account", property = "account", jdbcType = JdbcType.VARCHAR),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
    })
    List<User> selectByExample(UserExample userExample);
}
