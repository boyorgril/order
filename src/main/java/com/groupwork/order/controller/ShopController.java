package com.groupwork.order.controller;

import com.groupwork.order.datasource.dto.ShopFood;
import com.groupwork.order.service.OrderService;
import com.groupwork.order.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String gotoOrderDetail(Model model,@RequestParam("orderId")Long oid){
        model.addAttribute("ShopFoods", orderService.getOrderDetail(oid));
        model.addAttribute("orderId",oid);
        model.addAttribute("orderStatus",orderService.getStatus(oid));
        return "shop/orderDetail";
    }

    @RequestMapping("/shop/checkOrder")
    public String checkOrder(@RequestParam("orderId")Long oid){
        //数据库修改
        orderService.updateStatus(oid);
        //重定向
        return "redirect:/shop/gotoOrderList";
    }

    @RequestMapping("/shop/modifyFood")
    public String gotoModifyFood(Model model,@RequestParam("sfId")Long sfid){
        model.addAttribute("shopfood",shopService.getFoodById(sfid));
        return "/shop/modifyFood";
    }

    @RequestMapping("/shop/addFood")
    public String gotoAddFood(Model model){
        ShopFood shopFood = new ShopFood();
        shopFood.setImgUrl("/img/666.jpg");
        shopFood.setName("请在此输入菜品名");
        shopFood.setIntroduce("请在此输入菜品简介");
        model.addAttribute("shopfood",shopFood);
        return "/shop/addFood";
    }
}
