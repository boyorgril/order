package com.groupwork.order.controller;

import com.groupwork.order.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

    @Autowired
    private ShopService shopService;

    @RequestMapping("customer/index")
    public String enterIndex(Model model){
        model.addAttribute("shops", shopService.allShop());
        return "customer/index";
    }

}
