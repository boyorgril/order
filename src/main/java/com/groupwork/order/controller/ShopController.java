package com.groupwork.order.controller;

import com.groupwork.order.service.OrderService;
import com.groupwork.order.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("shop/index")
    public String enterIndex(Model model, HttpServletRequest httpRequest){
        Long shopId = (Long) httpRequest.getSession().getAttribute("userId");
        model.addAttribute("shopfoods", shopService.getFoods(shopId));
        return "shop/index";
    }

    @RequestMapping("/gotoModify")
    public String gotoModify(Model model, HttpServletRequest httpRequest){
        Long shopId = (Long) httpRequest.getSession().getAttribute("userId");
        model.addAttribute("shop", shopService.getShopInfo(shopId));
        return "shop/shopInfoModify";
    }

    @RequestMapping("/shop/gotoOrderDetail")
    public String gotoOrderDetail(Model model, HttpServletRequest httpRequest){
        Long oid = (Long) httpRequest.getSession().getAttribute("userId");
        model.addAttribute("ShopFoods", orderService.getOrderDetail(oid));
        return "shop/shopOrderDetail";
    }
}
