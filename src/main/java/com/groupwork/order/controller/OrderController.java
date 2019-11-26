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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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

    /**
     * 判断用户类型，并将该顾客的订单列表显示其 orderList.html
     * @param model
     * @param httpRequest
     * @return
     */
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
            orderEntity.setWho("姓名： "+ address.getName() + "     电话："+address.getPhoneNumber());
            orderEntity.setAddress(address.getLocation());
//            System.out.println(orderEntity);
            if(orderEntity!=null) orderEntities.add(orderEntity);
        }
        model.addAttribute("shoporders", orderEntities);
        return "order/orderList";
    }

    /**
     * 判断用户类型，并将其显示到商家的 orderList.html
     * @param model
     * @param httpRequest
     * @return
     */
    @RequestMapping("/shop/gotoOrderList")
    public String enterShopOrderList(Model model, HttpServletRequest httpRequest){
        Long userId = (Long) httpRequest.getSession().getAttribute("shopId");
        String type = (String)httpRequest.getSession().getAttribute("userType");
        List<Order> orders =  new ArrayList<>();

        if("SELLER".equals(type)){
            orders = orderService.getOrders(userId);
        }else{
            orders = orderService.getUserOrders(userId);
        }
        for (Order o:orders
             ) {
            System.out.println(o);
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
            orderEntity.setAddress(address.getLocation());
//            System.out.println(orderEntity);
            if(orderEntity!=null) {
                if(orderEntity.getStatus().equals("COMPLETE")||orderEntity.getStatus().equals("NOCOMPLETE")){
                    orderEntities.add(orderEntity);
                }
            }
        }
        model.addAttribute("shoporders", orderEntities);
        return "shop/orderList";
    }

    /**
     * 显示用户订单详情 orderDetail.html
     * @param model
     * @param oid
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/order/gotoOrderDetail")
    public String gotoOrderDetail(Model model,@RequestParam("orderId")Long oid, HttpServletRequest httpServletRequest){
        model.addAttribute("ShopFoods", orderService.getOrderDetail(oid));
        model.addAttribute("orderStatus",orderService.getStatus(oid));
        model.addAttribute("userType", (String)httpServletRequest.getSession().getAttribute("userType"));
        model.addAttribute("orderId", oid);
        model.addAttribute("comment", orderService.findOrderComment(oid));
        return "order/orderDetail";
    }

    /**
     * 顾客提交对订单的评价
     * @param comment
     * @param oid
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/order/commentSubmit")
    @ResponseBody
    public String saveComment( @RequestParam("comment")String comment, @RequestParam("orderId")String oid, HttpServletRequest httpServletRequest){
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        orderService.insertComment(userId, new BigDecimal(oid).longValue(), comment);
        return "success";
    }

    /**
     * 根据用户类型，判断进入顾客 index.html 或商家 index.html
     * @param httpRequest
     * @return
     */
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
