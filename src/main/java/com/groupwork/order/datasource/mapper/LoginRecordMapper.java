package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.LoginRecord;
import com.groupwork.order.datasource.dto.LoginRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;


public interface LoginRecordMapper {

    @Insert({
            "insert into loginRecord (ipAddress, userId, loginTime)",
            "values (ipAddress = #{ipAddress,jdbcType=VARCHAR}, userId = #{userId,jdbcType=BIGINT}, ",
                    "loginTime = #{loginTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(LoginRecord record);


}