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
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/order/gotoOrderList")
    public String enterOrderList(Model model, HttpServletRequest httpRequest){
        Long userId = (Long) httpRequest.getSession().getAttribute("userId");
        String type = (String)httpRequest.getSession().getAttribute("userType");
        List<Order> orders =  new ArrayList<>();

        if("SELLER".equals(type)){
            orders = orderService.getOrders(userId);
        }else{
            orders = orderService.getUserOrders(userId);
        }
        List<OrderEntity> orderEntities = new ArrayList<OrderEntity>();
        for (Order order :orders)   {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setTotalMoney(order.getTotalMoney());
            orderEntity.setOid(order.getId());
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
        return "order/orderList";
    }

    @RequestMapping("/order/gotoOrderDetail")
    public String gotoOrderDetail(Model model,@RequestParam("orderId")Long oid, HttpServletRequest httpServletRequest){
        model.addAttribute("ShopFoods", orderService.getOrderDetail(oid));
        model.addAttribute("userType", (String)httpServletRequest.getSession().getAttribute("userType"));
        return "order/orderDetail";
    }

    @RequestMapping("/user/index")
    public String userIndex(HttpServletRequest httpRequest){
        String type = (String)httpRequest.getSession().getAttribute("userType");
        if("SELLER".equals(type)){
            return "redirect:/shop/index";
        }else{
            return "redirect:/customer/index";
        }
    }

}
