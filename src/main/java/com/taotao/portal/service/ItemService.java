package com.taotao.portal.service;

import com.taotao.pojo.Item;

/**
 * 商品信息Service
* <p>Title: ItemService.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-19_14:14:46
* @version 1.0
 */
public interface ItemService {

	/**
	 * 调用服务，获取商品的信息
	* <p>Title: getItemByItemId<／p>
	* <p>Description: <／p>
	* @param itemId
	* @return
	* @throws Exception
	 */
	public Item getItemByItemId(Long itemId) throws Exception;

	String getItemDescById(Long itemId) throws Exception;

	String getItemParamById(Long itemId) throws Exception;
}
