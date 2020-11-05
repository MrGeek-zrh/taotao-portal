package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * 搜索Controller
* <p>Title: SearchController.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-18_16:13:46
* @version 1.0
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String search(
			@RequestParam("q")String keyword,
			@RequestParam(defaultValue = "1")Integer page,
			@RequestParam(defaultValue = "60")Integer rows,
			Model model
			) {
		SearchResult searchResult;
		try {
			keyword = new String(keyword.getBytes("ISO-8859-1"),"utf-8");
			searchResult = searchService.search(keyword, page, rows);
			//参数传递给页面
			model.addAttribute("query", keyword);
			model.addAttribute("totalPages",searchResult.getPageCount());
			model.addAttribute("itemList", searchResult.getItemList());
			model.addAttribute("page", searchResult.getCurPage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回逻辑视图
		return "views/search";
	}
}
