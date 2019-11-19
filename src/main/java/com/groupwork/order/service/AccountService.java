package com.groupwork.order.service;

import com.groupwork.order.annotation.OperationLog;
import com.groupwork.order.datasource.dto.User;
import com.groupwork.order.datasource.dto.UserExample;
import com.groupwork.order.datasource.mapper.AccountMapper;
import com.groupwork.order.model.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    public Long findAccount(User user){
        return accountMapper.selectCount(user);
    }

    public int accountRegister(User user){
        user.setCreateAt(new Date());
        user.setUserName(user.getAccount());
        if("BUYER".equals(user.getType())){
            user.setMoney(500.00);
        }
        user.setImgUrl("/img/initial.png");
       return accountMapper.accountRegister(user);
    }

    public boolean checkAccount(String account){
        return accountMapper.findAccountNumber(account) > 0 ? true :false;
    }

    public User findUserById(Long userId){
        return accountMapper.userInfo(userId);
    }

    public List findUserByExample(){
        UserExample example = new UserExample();
        example.createCriteria().andAccountEqualTo("123");
        return accountMapper.selectByExample(example);
    }

    public void updateUserName(Long userId, String userName){
        accountMapper.updateNameByKey(userId, userName);
    }

}
