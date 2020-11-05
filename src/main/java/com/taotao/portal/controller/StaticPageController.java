package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.portal.service.StaticPageService;

/**
 * 静态页面Controller
* <p>Title: StaticPageController.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-20_09:31:40
* @version 1.0
 */
@Controller
public class StaticPageController {

	@Autowired
	private StaticPageService staticPageService;
	
	/**
	 * 发布生成页面的服务
	* <p>Title: getItemPage<／p>
	* <p>Description: <／p>
	* @param itemId
	* @return
	 */
	@RequestMapping("/gen/item/{itemId}")
	@ResponseBody
	public TaotaoResult getItemPage(@PathVariable Long itemId) {
		try {
			TaotaoResult result = staticPageService.genItemHtml(itemId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
