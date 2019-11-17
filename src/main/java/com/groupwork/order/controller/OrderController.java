package com.groupwork.order.controller;

import com.groupwork.order.datasource.dto.Address;
import com.groupwork.order.datasource.dto.Order;
import com.groupwork.order.model.OrderEntity;
import com.groupwork.order.service.AddressService;
import com.groupwork.order.service.OrderService;
import com.groupwork.order.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private AddressService addressService;

    @RequestMapping("/shop/gotoOrderList")
    public String enterOrderList(Model model, HttpServletRequest httpRequest){
        Long shopId = (Long) httpRequest.getSession().getAttribute("userId");
        List<Order> orders =  orderService.getOrders(shopId);
        List<OrderEntity> orderEntities = new ArrayList<OrderEntity>();
        for (Order order :orders) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setTotalMoney(order.getTotalMoney());
            orderEntity.setStatus(order.getStatus());
            Long foodId = orderService.getFoodId(order.getId());
            if(foodId == null)continue;
            orderEntity.setImgUrl(shopService.getFoodImgByFoodId(foodId));
            Address address = addressService.getAddress(order.getAddressId());
            orderEntity.setWho(address.getName() + "  "+address.getPhoneNumber());
//            System.out.println(orderEntity);
            if(orderEntity!=null) orderEntities.add(orderEntity);
        }
        model.addAttribute("shoporders", orderEntities);
        return "shop/orderList";
    }
}
