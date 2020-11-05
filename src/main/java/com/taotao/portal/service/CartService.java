package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

/**
 * 购物车Service
* <p>Title: CartService.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-22_09:02:08
* @version 1.0
 */
public interface CartService {

	public TaotaoResult addCart(Long itemId,Integer num,HttpServletRequest request,HttpServletResponse response);
	
	public List<CartItem> getCartItems(HttpServletRequest request);
	
	public TaotaoResult updateCartItem(Long itemId,Integer num,HttpServletRequest request,HttpServletResponse response);

	public TaotaoResult deleteCartItem(Long itemId,HttpServletRequest request,HttpServletResponse response);

}
