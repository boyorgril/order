package com.groupwork.order.controller;

import com.groupwork.order.datasource.dto.User;
import com.groupwork.order.service.AccountService;
import com.groupwork.order.utils.FileUtilsDiy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Value(value = "${resources_path}")
    String resources_path;//资源文件绝对地址目录

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginCheck")
    public String check(HttpServletRequest httpServletRequest, Model model, User user){
        Long userId = accountService.findAccount(user);
        if(!"null".equals(String.valueOf(userId)) && userId > 0){
            //userId放到session缓存中，大多数数据根据userId都可以找到
            httpServletRequest.getSession().setAttribute("userId",userId);
            List<User> users = accountService.findUserByExample();
            System.out.println(users.size());
            //直接返回到页面
            //return "index";
            //访问controller的index
            return "forward:index";
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
        return "login";
    }

    @RequestMapping("/registerAccount")
    public String registerAccount(User user){
        if(accountService.accountRegister(user) > 0){
            return "forward:login";
        }else{
            return "forward:register";
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
