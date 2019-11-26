package com.groupwork.order.service;

import com.groupwork.order.datasource.dto.OperationRecord;
import com.groupwork.order.datasource.mapper.OperationRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationRecordService {

    @Autowired
    private OperationRecordMapper operationRecordMapper;

    /**
     * 添加操作记录
     * @param record
     */
    public void saveRecord(OperationRecord record){
        operationRecordMapper.insert(record);
    }

}
