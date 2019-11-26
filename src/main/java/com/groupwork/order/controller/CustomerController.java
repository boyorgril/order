package com.groupwork.order.controller;

import com.alibaba.druid.util.StringUtils;
import com.groupwork.order.datasource.dto.Address;
import com.groupwork.order.datasource.dto.Order;
import com.groupwork.order.datasource.dto.OrderDetail;
import com.groupwork.order.datasource.dto.User;
import com.groupwork.order.datasource.mapper.UserMapper;
import com.groupwork.order.model.CollectFoodEntity;
import com.groupwork.order.model.CollectShopEntity;
import com.groupwork.order.model.OrderCountEntity;
import com.groupwork.order.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AccountService accountService;

    @Value(value = "${resources_path}")
    String resources_path;//资源文件绝对地址目录

    /**
     * 取得店铺信息，并展示在顾客的主页 index.html
     * @param model
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("customer/index")
    public String enterIndex(Model model, HttpServletRequest httpServletRequest){
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        model.addAttribute("shops", shopService.allShop(userId));
        return "customer/index";
    }

    /**
     * 通过商家shopId，来跳转到访问的商家页面
     * @param shopId
     * @param model
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("customer/visit/{shopId}")
    public String shopFood(@PathVariable Long shopId, Model model, HttpServletRequest httpServletRequest){
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        model.addAttribute("shopFood", shopService.shopFoodByShopId(shopId, userId));
        model.addAttribute("shopInfo", shopService.findShopById(shopId));
        return "customer/shopFood";
    }

    /**
     * 顾客收藏商家
     * @param shopId
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("customer/collectShop")
    @ResponseBody
    public String collectShop(@RequestParam(value = "shopId", required = false) String shopId, HttpServletRequest httpServletRequest){
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        customerService.collectShop(userId, new BigDecimal(shopId).longValue());
        return "success";
    }

    /**
     * 顾客取消收藏商家
     * @param shopId
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("customer/cancelCollectShop")
    @ResponseBody
    public String cancelCollectShop(@RequestParam(value = "shopId", required = false) String shopId, HttpServletRequest httpServletRequest){
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        customerService.updateCollectShopStatus(userId, new BigDecimal(shopId).longValue(), "UNCOLLECT");
        return "success";
    }

    /**
     * 顾客收藏菜品
     * @param foodId
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("customer/collectFood")
    @ResponseBody
    public String collectFood(@RequestParam(value = "foodId", required = false) String foodId, HttpServletRequest httpServletRequest){
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        customerService.collectFood(userId, new BigDecimal(foodId).longValue());
        return "success";
    }

    /**
     * 顾客取消收藏菜品
     * @param shopId
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("customer/cancelCollectFood")
    @ResponseBody
    public String cancelCollectFood(@RequestParam(value = "foodId", required = false) String shopId, HttpServletRequest httpServletRequest){
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        customerService.updateCollectFoodStatus(userId, new BigDecimal(shopId).longValue(), "UNCOLLECT");
        return "success";
    }

    /**
     * 顾客确认订单，如果此时账户余额不足或地址不全，则点击提交时会返回404页面；
     *                 如果账户余额充足，会计算此次订单的总费用
     * @param totalMoney
     * @param shopId
     * @param orderNum
     * @param httpServletRequest
     * @param model
     * @return
     */
    @RequestMapping("customer/saveOrder")
    public String saveOrder(@RequestParam("totalMoney") String totalMoney, @RequestParam("shopId") String shopId,
                            @RequestParam("orderNum") String orderNum, HttpServletRequest httpServletRequest, Model model){
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        double userMoney = customerService.userMoney(userId);
        if (new BigDecimal(totalMoney).doubleValue()> userMoney){
            model.addAttribute("reason","账户余额不足");
            return "404";
        }

        if(orderService.findUserAddress(userId).size() <= 0){
            model.addAttribute("reason","请维护个人地址信息");
            return "404";
        }

        Order order = orderService.saveOrder(userId, new BigDecimal(shopId).longValue(),
                new BigDecimal(totalMoney).doubleValue());
        List<OrderDetail> detailList = orderService.saveOrderDetail(order.getId(), orderNum);

        OrderCountEntity countEntity = orderService.buildModel(order,detailList);

        customerService.updateUserMoney(new BigDecimal(totalMoney).doubleValue(), userId);
        model.addAttribute("countEntity", countEntity);

        return "customer/countMoney";
    }

    /**
     * 顾客保存订单的地址和联系人及联系方式
     * @param orderNum
     * @param addressId
     * @return
     */
    @RequestMapping("customer/saveOrderAddress")
    public String saveOrderAddressInfo(String orderNum, String addressId){
        orderService.saveOrderAddressInfo(orderNum, addressId);
        return "redirect:/order/gotoOrderList";
    }

    /**
     * 显示顾客的个人用户信息
     * @param httpServletRequest
     * @param model
     * @return
     */
    @RequestMapping("customer/personalInfo")
    public String showPesonalInfo(HttpServletRequest httpServletRequest, Model model){
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        List<CollectFoodEntity> foodEntities = customerService.searchFood(userId);
        List<CollectShopEntity> shopEntities = customerService.searchShop(userId);
        model.addAttribute("collectShops", shopEntities);
        model.addAttribute("collectFoods", foodEntities);
        model.addAttribute("userInfo", customerService.findUserInfo(userId));
        return "customer/personalInfo";
    }

    /**
     * 显示顾客对菜品的评论信息
     * @param model
     * @param foodId
     * @return
     */
    @RequestMapping("/customer/seeFoodComment/{foodId}")
    public String showFoodComment(Model model, @PathVariable Long foodId){
        model.addAttribute("comment",customerService.foodComment(foodId));
        return "customer/foodComment";
    }

    /**
     * 修改顾客个人信息详情，并且刷新 updateInfo.html
     * @param model
     * @param servletRequest
     * @return
     */
    @RequestMapping("/customer/infoDetail")
    public String infoDeatil(Model model, HttpServletRequest servletRequest){
        Long userId = (Long) servletRequest.getSession().getAttribute("userId");
        model.addAttribute("userInfo", customerService.findDetailById(userId));
        return "customer/updateInfo";
    }

    /**
     * 顾客上传头像，并将其保存到服务器端，同时刷新 updateInfo.html
     * @param file
     * @param servletRequest
     * @return
     */
    @RequestMapping("/customer/changeImg")
    public String saveImgUrl(@RequestParam("upFile") MultipartFile file, HttpServletRequest servletRequest){
        Long userId = (Long) servletRequest.getSession().getAttribute("userId");
        String fileName = userId + new Date().getTime() + ".jpg";
        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get(resources_path + fileName);
            Files.write(path, bytes);
        }catch (Exception e){
            e.printStackTrace();
        }

        User user = new User();
        user.setImgUrl("/" + fileName);
        user.setId(userId);

        customerService.updateUserInfo(user);

        return "redirect:/customer/infoDetail";
    }

    /**
     * 顾客变更地址详情
     * @param address
     * @param servletRequest
     * @return
     */
    @RequestMapping("/customer/updateAddress")
    public String updateAddressInfo(Address address, HttpServletRequest servletRequest){
        Long userId = (Long) servletRequest.getSession().getAttribute("userId");
        address.setUserId(userId);
        addressService.insertOrUpdateAddr(address);
        return "redirect:/customer/infoDetail";
    }

    /**
     * 修改顾客的昵称
     * @param name
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/customer/updateUserName")
    @ResponseBody
    public String updateName(@RequestParam(value = "name", required = false) String name, HttpServletRequest httpServletRequest){
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        accountService.updateUserName(userId, name);
        return "success";
    }



}
