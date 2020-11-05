package com.taotao.portal.service;

import com.taotao.portal.pojo.OrderInfo;

/**
 * 提交订单Service
* <p>Title: OrderService.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-23_09:53:50
* @version 1.0
 */
public interface OrderService {

	public String createOrder(OrderInfo oederInfo);
}
