package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.User;
import com.groupwork.order.datasource.dto.UserExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface AccountMapper {

    @Select({
            "select id from user",
            "where account = #{account,jdbcType=VARCHAR} and passWord =  #{passWord,jdbcType=VARCHAR}",
            " and type = #{type,jdbcType=VARCHAR}"
    })
    Long selectCount(User user);

    @Insert({
            "insert into user(account, passWord, type, userName, imgUrl, money, createAt) values " ,
             "(#{account,jdbcType=VARCHAR}, #{passWord,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, ",
             "#{userName,jdbcType=VARCHAR}, #{imgUrl,jdbcType=VARCHAR}, #{money,jdbcType=DOUBLE}, " ,
             "#{createAt,jdbcType=TIMESTAMP})",
    })
    int accountRegister(User user);

    @Select({
            "select count(account) from user ",
            "where account = #{account,jdbcType=VARCHAR} "
    })
    int findAccountNumber(String account);

    @Select({
            "select id, account, userName, passWord from user where id = #{userId,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column = "account", property = "account", jdbcType = JdbcType.VARCHAR),
            @Result(column = "userName", property = "userName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "passWord", property = "passWord", jdbcType = JdbcType.VARCHAR),
    })
    User userInfo(Long userId);

    @SelectProvider(type=AccountSqlProvider.class, method="selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column = "account", property = "account", jdbcType = JdbcType.VARCHAR),
            @Result(column = "userName", property = "userName", jdbcType = JdbcType.VARCHAR),
    })
    List<User> selectByExample(UserExample userExample);

    @Update({
            "update user set userName = #{userName,jdbcType=VARCHAR} where id = #{userId,jdbcType=VARCHAR}"
    })
    void updateNameByKey(Long userId, String userName);
}
