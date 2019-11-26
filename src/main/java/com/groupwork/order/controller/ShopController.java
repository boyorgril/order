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

    /**
     * 显示商家 index.html 如果商家为新创建的商家用户，则需要提交商家的详细信息
     * @param model
     * @param httpRequest
     * @return
     */
    @RequestMapping("shop/index")
    public String enterIndex(Model model, HttpServletRequest httpRequest){
        Long userId = (Long) httpRequest.getSession().getAttribute("userId");
        Long shopId = shopService.getId(userId);
        BigDecimal money = shopService.getMoney(userId);
        if(money==null)money =new BigDecimal(0);
        httpRequest.getSession().setAttribute("shopId",shopId);
        String shopName = shopService.getShopName(shopId);
        if(shopName == null){
            return "redirect:/gotoAddShop";
        }
        model.addAttribute("shopfoods", shopService.getFoods(shopId));
        model.addAttribute("shopname", shopName);
        model.addAttribute("money",money);
        return "shop/index";
    }

    /**
     * 商家修改商家信息，跳转 shopInfoModify.html
     * @param model
     * @param httpRequest
     * @return
     */
    @RequestMapping("/gotoModify")
    public String gotoModify(Model model, HttpServletRequest httpRequest){
        Long shopId = (Long) httpRequest.getSession().getAttribute("shopId");
        model.addAttribute("shop", shopService.getShopInfo(shopId));
        return "shop/shopInfoModify";
    }

    /**
     * 新注册的商家用户提交店铺的详细信息
     * @param model
     * @param httpRequest
     * @return
     */
    @RequestMapping("/gotoAddShop")
    public String gotoAddShop(Model model, HttpServletRequest httpRequest){
        Long shopId = (Long) httpRequest.getSession().getAttribute("shopId");
        Shop shop =new Shop();
        shop.setName("请输入店名");
        shop.setIntroduce("请输入店铺简介");
        shop.setShopImgUrl("/img/666.jpg");
        model.addAttribute("shop", shop);
        return "shop/addShop";
    }

    /**
     * 商家修改头像，并且确认提交，将头像保存到服务器端
     * @param file
     * @param servletRequest
     * @return
     */
    @RequestMapping("/shop/changeImg")
    public String saveImgUrl(@RequestParam("upFile") MultipartFile file, HttpServletRequest servletRequest){
        Long userId = (Long) servletRequest.getSession().getAttribute("shopId");
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

    /**
     * 商家菜品修改头像，将信息保存到服务器端
     * @param file
     * @param servletRequest
     * @param model
     * @return
     */
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

    /**
     * 商家进入商家的订单详情 orderDetail.html
     * @param model
     * @param oid
     * @return
     */
    @RequestMapping("/shop/gotoOrderDetail")
    public String gotoOrderDetail(Model model,@RequestParam("orderId")Long oid){
        model.addAttribute("ShopFoods", orderService.getOrderDetail(oid));
        model.addAttribute("orderId",oid);
        model.addAttribute("orderStatus",orderService.getStatus(oid));
        return "shop/orderDetail";
    }

    /**
     * 商家收到订单后，完成订单，并且重新回到商家的 orderList.html
     * @param oid
     * @param httpRequest
     * @return
     */
    @RequestMapping("/shop/checkOrder")
    public String checkOrder(@RequestParam("orderId")Long oid,HttpServletRequest httpRequest){
        //数据库修改
        orderService.updateStatus(oid);
        Long userId = (Long) httpRequest.getSession().getAttribute("userId");
        Long shopId = shopService.getId(userId);
        BigDecimal money = shopService.getMoney(userId);
        if(money==null)money =new BigDecimal(0);
        BigDecimal orderMoney = orderService.getOrderMoney(oid);
        if(orderMoney==null)orderMoney =new BigDecimal(0);
        money = money.add(orderMoney);
        shopService.updateMoney(money,userId);
        //重定向
        return "redirect:/shop/gotoOrderList";
    }

    /**
     * 商家修改菜品信息
     * @param model
     * @param sfid
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/shop/modifyFood")
    public String gotoModifyFood(Model model,@RequestParam("sfId")Long sfid,HttpServletRequest httpServletRequest){
        model.addAttribute("shopfood",shopService.getFoodById(sfid));
        model.addAttribute("sfid",sfid);
        httpServletRequest.getSession().setAttribute("shopModifyFoodId",sfid);
        return "/shop/modifyFood";
    }

    /**
     * 商家添加新菜品，跳转进入 addFood.html
     * @param model
     * @return
     */
    @RequestMapping("/shop/addFood")
    public String gotoAddFood(Model model){
        ShopFood shopFood = new ShopFood();
        shopFood.setImgUrl("/img/666.jpg");
        shopFood.setName("请在此输入菜品名");
        shopFood.setIntroduce("请在此输入菜品简介");
        model.addAttribute("shopfood",shopFood);
        return "/shop/addFood";
    }

    /**
     * 商家修改店铺信息，并刷新商家  index.html
     * @param httpServletRequest
     * @param name
     * @param imgUrl
     * @param introduce
     * @return
     */
    @RequestMapping("/shop/changeShopInfo")
    public String modifyInfo(HttpServletRequest httpServletRequest,@RequestParam("name")String name,@RequestParam("imgUrl")String imgUrl,@RequestParam("introduce")String introduce){
        Long shopId = (Long) httpServletRequest.getSession().getAttribute("shopId");
        shopService.updateInfo(name,shopService.getShopImgByid(shopId),introduce,shopId);
        return"redirect:/shop/index";
    }

    /**
     * 新注册商家用户提交完信息后，进入到商家的 index.html
     * @param httpServletRequest
     * @param name
     * @param imgUrl
     * @param introduce
     * @return
     */
    @RequestMapping("/shop/addShopInfo")
    public String addInfo(HttpServletRequest httpServletRequest,@RequestParam("name")String name,@RequestParam("imgUrl")String imgUrl,@RequestParam("introduce")String introduce){
        Long shopId = (Long) httpServletRequest.getSession().getAttribute("userId");
        shopService.createShop(shopId,name,imgUrl,introduce);
        return"redirect:/shop/index";
    }

    /**
     * 添加菜品详情后，刷新进入商家 index.html
     * @param httpServletRequest
     * @param name
     * @param imgUrl
     * @param pricestr
     * @param introduce
     * @return
     */
    @RequestMapping("/shop/addFoodDetail")
    public String addFood(HttpServletRequest httpServletRequest,@RequestParam("name")String name,@RequestParam("imgUrl")String imgUrl,@RequestParam("price")String pricestr,@RequestParam("introduce")String introduce){
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(pricestr));
        Long shopId = (Long) httpServletRequest.getSession().getAttribute("shopId");
        shopService.addShopFood(name,imgUrl,price,introduce,shopId);
        return "redirect:/shop/index";
    }

    /**
     * 商家修改完菜品详情后，刷新重新进入 index.html
     * @param name
     * @param imgUrl
     * @param pricestr
     * @param introduce
     * @param sfid
     * @return
     */
    @RequestMapping("/shop/modifyFoodDetail")
    public String modifyFood(@RequestParam("name")String name,@RequestParam("imgUrl")String imgUrl,@RequestParam("price")String pricestr,
                             @RequestParam("introduce")String introduce,@RequestParam("sfId")Long sfid){
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(pricestr));
        shopService.updateShopFood(name,shopService.getFoodImgByFoodId(sfid),price,introduce,sfid);
        return "redirect:/shop/index";
    }

    /**
     * 商家删除菜品，并且刷新商家 index.html
     * @param sfid
     * @return
     */
    @RequestMapping("/shop/dropFood")
    public String dropFood(@RequestParam("sfId")Long sfid){
        shopService.dropFood(sfid);
        return "redirect:/shop/index";
    }
}
