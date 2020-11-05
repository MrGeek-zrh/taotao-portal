package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtil;
import com.taotao.portal.pojo.OrderInfo;
import com.taotao.portal.service.OrderService;

/**
 * 订单Service实现类
* <p>Title: OrderServiceImpl.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-23_09:55:16
* @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService{
	
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;

	@Override
	public String createOrder(OrderInfo orderInfo) {
		//把OrderInfo转换成json
		String json = JsonUtil.ObjectToJSON(orderInfo);
		//提交订单数据
		String jsonResult;
		try {
			jsonResult = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, json);
			//转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.format(jsonResult);
			//取订单号
			String orderId = taotaoResult.getData().toString();
			return orderId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
