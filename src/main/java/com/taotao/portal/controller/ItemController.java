package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.Item;
import com.taotao.portal.service.ItemService;

/**
 * item Controller
* <p>Title: ItemController.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-19_14:21:43
* @version 1.0
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	/**
	* <p>Title: showItemInfo<／p>
	* <p>Description: <／p>
	* @param itemId
	* @param model
	* @return
	 */
	@RequestMapping("/item/{itemId}")
	public String showItemInfo(@PathVariable Long itemId,Model model) {
		Item item=null;
		try {
			item = itemService.getItemByItemId(itemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("item", item);
		return "views/item";
	}
	
	/**
	* <p>Title: getItemDesc<／p>
	* <p>Description: <／p>
	* @param itemId
	* @return
	 */
	@RequestMapping(value="/item/desc/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId) {
		String desc=null;
		try {
			desc = itemService.getItemDescById(itemId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return desc;
	}
	
	@RequestMapping(value="/item/param/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId) {
		String paramHtml=null;
		try {
			paramHtml = itemService.getItemParamById(itemId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paramHtml;
	}
}
