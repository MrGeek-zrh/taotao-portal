package com.taotao.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.pojo.User;

/**
 * 获取用户信息相关操作
* <p>Title: UserService.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-22_00:51:18
* @version 1.0
 */
public interface UserService {

	/**
	 * 通过token从redis中获取用户信息
	* <p>Title: getUserByToken<／p>
	* <p>Description: <／p>
	* @param token
	* @return
	 */
	public User getUserByToken(HttpServletRequest request,HttpServletResponse response);
}
