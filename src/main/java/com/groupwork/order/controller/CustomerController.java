package com.groupwork.order.controller;

import com.groupwork.order.datasource.dto.Order;
import com.groupwork.order.datasource.dto.OrderDetail;
import com.groupwork.order.model.OrderCountEntity;
import com.groupwork.order.service.OrderService;
import com.groupwork.order.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("customer/index")
    public String enterIndex(Model model){
        model.addAttribute("shops", shopService.allShop());
        return "customer/index";
    }

    @RequestMapping("customer/visit/{shopId}")
    public String shopFood(@PathVariable Long shopId, Model model){
        model.addAttribute("shopFood", shopService.shopFoodByShopId(shopId));
        model.addAttribute("shopInfo", shopService.findShopById(shopId));
        return "customer/shopFood";
    }

    @RequestMapping("customer/saveOrder")
    public String saveOrder(@RequestParam("totalMoney") String totalMoney, @RequestParam("shopId") String shopId,
                            @RequestParam("orderNum") String orderNum, HttpServletRequest httpServletRequest, Model model){
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        Order order = orderService.saveOrder(userId, new BigDecimal(shopId).longValue(),
                new BigDecimal(totalMoney).doubleValue());
        List<OrderDetail> detailList = orderService.saveOrderDetail(order.getId(), orderNum);

        OrderCountEntity countEntity = orderService.buildModel(order,detailList);

        model.addAttribute("countEntity", countEntity);

        return "customer/countMoney";
    }

}
