package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.Address;
import com.groupwork.order.datasource.dto.AddressExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;



public interface AddressMapper {

    @Delete({
            "delete from address ",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteById(Long id);

    @DeleteProvider(type = AddressSqlProvider.class, method = "deleteByExample")
    int deleteByExample(AddressExample example);

    @SelectProvider(type = AddressSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="userId", property="userId", jdbcType= JdbcType.BIGINT),
            @Result(column="location", property="location", jdbcType= JdbcType.VARCHAR),
            @Result(column="phoneNumber", property="phoneNumber", jdbcType= JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR)
    })
    List<Address> selectByExample(AddressExample example);

    @Select({
            "select id,userId,location,phoneNumber,name from address where id = #{id,jdbcType=BIGINT} "
    })
    Address getAddressById(Long id);

    @Insert({
            "insert into address (userId, location, phoneNumber, name)",
            "values (#{userId,jdbcType=BIGINT}, #{location,jdbcType=VARCHAR}, ",
            "#{phoneNumber,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Address record);

    @Update({
            "update address set ",
            "userId = #{userId,jdbcType=BIGINT}, location = #{location,jdbcType=VARCHAR},",
            "phoneNumber = #{phoneNumber,jdbcType=VARCHAR}, name = #{name,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Address record);
}