package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 静态页面生成Service
* <p>Title: StaricPageService.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-20_08:56:49
* @version 1.0
 */
public interface StaticPageService {

	TaotaoResult genItemHtml(Long itemId) throws Exception;

}
