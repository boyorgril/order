package com.groupwork.order.datasource.mapper;

import com.groupwork.order.datasource.dto.OperationRecord;
import com.groupwork.order.datasource.dto.OperationRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;


public interface OperationRecordMapper {
    @Insert({
            "insert into operationRecord (userId, describe, createAt)",
            "values (#{userId,jdbcType=BIGINT}, #{describe,jdbcType=VARCHAR}, ",
            " #{createAt,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(OperationRecord record);

}