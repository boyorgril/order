package com.groupwork.order.controller;


import com.groupwork.order.datasource.dto.User;
import com.groupwork.order.service.AccountService;
import com.groupwork.order.service.LoginRecordService;
import com.groupwork.order.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private LoginRecordService loginRecordService;

    @Value(value = "${resources_path}")
    String resources_path;//资源文件绝对地址目录

    @RequestMapping("/login")
    public String login(){
        log.info("{} 访问登录页面 {}", "visitor", new Date());
        return "login";
    }

    @RequestMapping("/loginCheck")
    public String check(HttpServletRequest httpServletRequest, Model model, User user){
        Long userId = accountService.findAccount(user);
        if(!"null".equals(String.valueOf(userId)) && userId > 0){
            //userId放到session缓存中，大多数数据根据userId都可以找到
            log.info("{}登录成功", userId );
            httpServletRequest.getSession().setAttribute("userId",userId);
            httpServletRequest.getSession().setAttribute("userType",user.getType());
            String ipAddress = IpUtil.getIpAddr(httpServletRequest);
            loginRecordService.addRecord(ipAddress + IpUtil.getIpInfo(ipAddress), userId);
            if("BUYER".equals(user.getType())){
                return "redirect:/customer/index";
            }else{
                return "redirect:/shop/index";
            }

        }
        model.addAttribute("errormessage","notfound");
        return "login";
    }

    @RequestMapping("/index")
    public String loginSuccess(HttpServletRequest httpServletRequest){
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        User user = accountService.findUserById(userId);
        return "index";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().removeAttribute("userId");
        return "redirect:/login";
    }

    @RequestMapping("/registerAccount")
    public String registerAccount(User user){
        if(accountService.accountRegister(user) > 0){
            return "redirect:login";
        }else{
            return "redirect:register";
        }
    }

    @RequestMapping(value = "/checkAccount", method = RequestMethod.POST)
    @ResponseBody
    public String checkAccount(@RequestParam(value = "account", required = false) String account){
        String data;
        if(accountService.checkAccount(account)){
            data =  "exit";
        }else {
            data =  "noAccount";
        }
        return data;
    }

}
