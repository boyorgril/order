package com.groupwork.order.service;

import com.groupwork.order.datasource.dto.User;
import com.groupwork.order.datasource.dto.UserExample;
import com.groupwork.order.datasource.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 寻找用户id
     * @param user
     * @return
     */
    public Long findAccount(User user){
        return accountMapper.selectCount(user);
    }

    /**
     * 账号注册
     * @param user
     * @return
     */
    public int accountRegister(User user){
        user.setCreateAt(new Date());
        user.setUserName(user.getAccount());
        if("BUYER".equals(user.getType())){
            user.setMoney(500.00);
        }
        user.setImgUrl("/img/initial.png");
       return accountMapper.accountRegister(user);
    }

    /**
     * 检查是否存在该账号
     * @param account
     * @return
     */
    public boolean checkAccount(String account){
        return accountMapper.findAccountNumber(account) > 0 ? true :false;
    }

    /**
     * 通过id获取用户信息
     * @param userId
     * @return
     */
    public User findUserById(Long userId){
        return accountMapper.userInfo(userId);
    }

    /**
     * 测试用例
     * @return
     */
    public List findUserByExample(){
        UserExample example = new UserExample();
        example.createCriteria().andAccountEqualTo("123");
        return accountMapper.selectByExample(example);
    }

    /**
     * 更新用户名
     * @param userId
     * @param userName
     */
    public void updateUserName(Long userId, String userName){
        accountMapper.updateNameByKey(userId, userName);
    }

}
