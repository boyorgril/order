package com.groupwork.order.controller;

import com.groupwork.order.datasource.dto.Shop;
import com.groupwork.order.datasource.dto.ShopFood;
import com.groupwork.order.datasource.dto.User;
import com.groupwork.order.service.OrderService;
import com.groupwork.order.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Controller
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private OrderService orderService;
    @Value(value = "${resources_path}")
    String resources_path;//资源文件绝对地址目录

    @RequestMapping("shop/index")
    public String enterIndex(Model model, HttpServletRequest httpRequest){
        Long shopId = (Long) httpRequest.getSession().getAttribute("userId");
        String shopName = shopService.getShopName(shopId);
        if(shopName == null){
            return "redirect:/gotoAddShop";
        }
        model.addAttribute("shopfoods", shopService.getFoods(shopId));
        model.addAttribute("shopname", shopName);
        return "shop/index";
    }

    @RequestMapping("/gotoModify")
    public String gotoModify(Model model, HttpServletRequest httpRequest){
        Long shopId = (Long) httpRequest.getSession().getAttribute("userId");
        model.addAttribute("shop", shopService.getShopInfo(shopId));
        return "shop/shopInfoModify";
    }

    @RequestMapping("/gotoAddShop")
    public String gotoAddShop(Model model, HttpServletRequest httpRequest){
        Long shopId = (Long) httpRequest.getSession().getAttribute("userId");
        Shop shop =new Shop();
        shop.setName("请输入店名");
        shop.setIntroduce("请输入店铺简介");
        shop.setShopImgUrl("/img/666.jpg");
        model.addAttribute("shop", shop);
        return "shop/addShop";
    }

    @RequestMapping("/shop/changeImg")
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

        shopService.updatePic("/" + fileName,userId);

        return "redirect:/gotoModify";
    }

    @RequestMapping("/shopfoodModify/changeImg")
    public String saveFoodImgUrl(@RequestParam("upFile") MultipartFile file, HttpServletRequest servletRequest,Model model){
        Long userId = (Long) servletRequest.getSession().getAttribute("shopModifyFoodId");
        System.out.println(userId);
        String fileName = userId + new Date().getTime() + ".jpg";
        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get(resources_path + fileName);
            Files.write(path, bytes);
        }catch (Exception e){
            e.printStackTrace();
        }

        shopService.updateFoodPic("/" + fileName,userId);

        return "redirect:/shop/modifyFood?sfId="+userId;
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
    public String gotoModifyFood(Model model,@RequestParam("sfId")Long sfid,HttpServletRequest httpServletRequest){
        model.addAttribute("shopfood",shopService.getFoodById(sfid));
        model.addAttribute("sfid",sfid);
        httpServletRequest.getSession().setAttribute("shopModifyFoodId",sfid);
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

    @RequestMapping("/shop/changeShopInfo")
    public String modifyInfo(HttpServletRequest httpServletRequest,@RequestParam("name")String name,@RequestParam("imgUrl")String imgUrl,@RequestParam("introduce")String introduce){
        Long shopId = (Long) httpServletRequest.getSession().getAttribute("userId");
        shopService.updateInfo(name,shopService.getShopImgByid(shopId),introduce,shopId);
        return"redirect:/shop/index";
    }

    @RequestMapping("/shop/addShopInfo")
    public String addInfo(HttpServletRequest httpServletRequest,@RequestParam("name")String name,@RequestParam("imgUrl")String imgUrl,@RequestParam("introduce")String introduce){
        Long shopId = (Long) httpServletRequest.getSession().getAttribute("userId");
        shopService.createShop(shopId,name,imgUrl,introduce);
        return"redirect:/shop/index";
    }

    @RequestMapping("/shop/addFoodDetail")
    public String addFood(HttpServletRequest httpServletRequest,@RequestParam("name")String name,@RequestParam("imgUrl")String imgUrl,@RequestParam("price")String pricestr,@RequestParam("introduce")String introduce){
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(pricestr));
        Long shopId = (Long) httpServletRequest.getSession().getAttribute("userId");
        shopService.addShopFood(name,imgUrl,price,introduce,shopId);
        return "redirect:/shop/index";
    }

    @RequestMapping("/shop/modifyFoodDetail")
    public String modifyFood(@RequestParam("name")String name,@RequestParam("imgUrl")String imgUrl,@RequestParam("price")String pricestr,@RequestParam("introduce")String introduce,@RequestParam("sfId")Long sfid){
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(pricestr));
        shopService.updateShopFood(name,shopService.getFoodImgByFoodId(sfid),price,introduce,sfid);
        return "redirect:/shop/index";
    }

    @RequestMapping("/shop/dropFood")
    public String dropFood(@RequestParam("sfId")Long sfid){
        shopService.dropFood(sfid);
        return "redirect:/shop/index";
    }
}
