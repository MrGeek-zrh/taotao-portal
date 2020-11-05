package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.pojo.User;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.OrderInfo;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;

/**
 * 展示购物车商品列表Controller
* <p>Title: OrderController.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-23_09:13:53
* @version 1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 展示订单信息处理器
	* <p>Title: showOrderCart<／p>
	* <p>Description: <／p>
	* @param model
	* @param request
	* @return
	 */
	@RequestMapping("/order-cart")
	public String showOrderCart(Model model,HttpServletRequest request) {
		//获取购物车商品列表
		List<CartItem>list = cartService.getCartItems(request);
		model.addAttribute("cartList", list);
		return "views/order-cart";
	}
	
	@RequestMapping("/create")
	public String createOrder(OrderInfo orderInfo, Model model, HttpServletRequest request) {
		//取用户信息
		User user = (User) request.getAttribute("user");
		//补全orderIn的属性
		orderInfo.setUserId(user.getId());
		orderInfo.setBuyerNick(user.getUsername());
		//调用服务
		String orderId = orderService.createOrder(orderInfo);
		//把订单号传递个页面
		model.addAttribute("orderId", orderId);
		model.addAttribute("payment", orderInfo.getPayment());
		DateTime dateTime = new DateTime();
		dateTime = dateTime.plusDays(3);
		model.addAttribute("date", dateTime.toString("yyyy-MM-dd"));
		//返回逻辑视图
		return "success";
	}
}
