package com.taotao.portal.service;

import com.taotao.common.pojo.SearchResult;

/**
 * 搜素服务Service类
* <p>Title: SearchService.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-18_10:57:09
* @version 1.0
 */
public interface SearchService {

	public SearchResult search(String keyword,int page,int rows) throws Exception;
	
}
