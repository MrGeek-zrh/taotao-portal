package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.JsonUtil;
import com.taotao.pojo.Item;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.ItemService;

/**
 * 购物车Srvice实现类
* <p>Title: CartServiceImpl.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-22_09:04:03
* @version 1.0
 */
@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private ItemService itemService;
	@Value("${COOKIE_EXPIRE}")
	private Integer COOKIE_EXPIRE;
	
	/**
	 * 添加购物车
	 */
	@Override
	public TaotaoResult addCart(Long itemId, Integer num, 
			HttpServletRequest request, HttpServletResponse response) {
		// 1、接收商品id
		// 2、从cookie中购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 3、从商品列表中查询列表是否存在此商品
		boolean haveFlg = false;
		for (CartItem cartItem : itemList) {
			//如果商品存在数量相加
			// 4、如果存在商品的数量加上参数中的商品数量
			if (cartItem.getId().longValue() == itemId) {
				cartItem.setNum(cartItem.getNum() + num);
				haveFlg = true;
				break;
			}
		}
		// 5、如果不存在，调用rest服务，根据商品id获得商品数据。
		try {
			if (!haveFlg) {
				Item item;
				item = itemService.getItemByItemId(itemId);
				//转换成CartItem
				CartItem cartItem = new CartItem();
				cartItem.setId(itemId);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
				cartItem.setTitle(item.getTitle());
				if (StringUtils.isNotBlank(item.getImage())) {
					String image = item.getImage();
					String[] strings = image.split(",");
					cartItem.setImage(strings[0]);
				}
				//添加到购物车商品列表
				// 6、把商品数据添加到列表中
				itemList.add(cartItem);
			}
			// 7、把购物车商品列表写入cookie
			CookieUtils.setCookie(request, response, "TT_CART", JsonUtil.ObjectToJSON(itemList), COOKIE_EXPIRE, true);
			// 8、返回TaotaoResult
			return TaotaoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	/**
	 * 从cokie中获取购物车列表
	* <p>Title: getCartItemList<／p>
	* <p>Description: <／p>
	* @param request
	* @return
	 */
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		try {
			//从cookie中取商品列表
			String json = CookieUtils.getCookieValue(request, "TT_CART", true);
			//把json转换成java对象
			List<CartItem> list = JsonUtil.jsonToList(json, CartItem.class);
			return list==null?new ArrayList<CartItem>():list;
		} catch (Exception e) {
			return new ArrayList<CartItem>();
		}
	}

	/**
	 * 从购物车中获取商品
	 */
	@Override
	public List<CartItem> getCartItems(HttpServletRequest request) {
		List<CartItem>list = getCartItemList(request);
		return list;
	}

	/**
	 * 更新购物车的内容
	 */
	@Override
	public TaotaoResult updateCartItem(Long itemId, Integer num,HttpServletRequest request,HttpServletResponse response) {
		// 从cookie中取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		//根据商品id查询商品
		for (CartItem cartItem : itemList) {
			if (cartItem.getId() == itemId) {
				//更新数量
				cartItem.setNum(num);
				break;
			}
		}
		//写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtil.ObjectToJSON(itemList), COOKIE_EXPIRE, true);
		return TaotaoResult.ok();
	}

	/**
	 * 删除购物车中的商品
	 */
	@Override
	public TaotaoResult deleteCartItem(Long itemId, HttpServletRequest request, HttpServletResponse response) {
		// 1、接收商品id
		// 2、从cookie中取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 3、找到对应id的商品
		for (CartItem cartItem : itemList) {
			if (cartItem.getId() == itemId) {
				// 4、删除商品。
				itemList.remove(cartItem);
				break;
			}
		}
		// 5、再重新把商品列表写入cookie。
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtil.ObjectToJSON(itemList), COOKIE_EXPIRE, true);
		// 6、返回成功
		return TaotaoResult.ok();
	}
	
}
