package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.service.SearchService;

/**
 * 搜素服务Service实现类
* <p>Title: SearchServiceImpl.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-18_11:01:07
* @version 1.0
 */
@Service
public class SearchServiceImpl implements SearchService {
	
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	/**
	 * 实现搜索功能
	 */
	@Override
	public SearchResult search(String keyword, int page, int rows) throws Exception {
		//调用服务查询商品列表
		Map<String, String> param = new HashMap<>();
		param.put("keyword", keyword);
		param.put("page", page + "");
		param.put("rows", rows + "");
		//调用服务
		String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
		//转换成java对象
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json,SearchResult.class);
		//取返回的结果
		SearchResult searchResult = (SearchResult) taotaoResult.getData();
		
		return searchResult;
	}

}
