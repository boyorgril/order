package com.groupwork.order.service;

import com.groupwork.order.datasource.dto.LoginRecord;
import com.groupwork.order.datasource.mapper.LoginRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginRecordService {

    @Autowired
    private LoginRecordMapper loginRecordMapper;

    public void addRecord(String ipAddress, Long userId){

        LoginRecord record = new LoginRecord();
        record.setIpAddress(ipAddress);
        record.setLoginTime(new Date());
        record.setUserId(userId);
        loginRecordMapper.insert(record);
    }

}
