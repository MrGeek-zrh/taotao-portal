package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.service.ContentService;

/**
 * 首页访问控制器
* <p>Title: IndexController.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 23, 2020
* @version 1.0
 */
@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;
	
	/**
	 * 跳转到系统首页：index.jsp
	 * @return
	 */
	@RequestMapping("/index")
	public String showIndex(Model model) {
		//获取到大广告位的内容
		String json = contentService.getAdList();
		model.addAttribute("ad1", json);
		return "views/index";
	}
	
	@RequestMapping(value = "/postTest",method = RequestMethod.POST)
	@ResponseBody
	public String postTest(String name ,String pass) {
		return "ok";
	}

}
