package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.pojo.User;
import com.taotao.portal.service.UserService;

/**
 * 登陆拦截器
* <p>Title: LoginInterceptor.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-22_01:06:54
* @version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired 
	private UserService userService;
	@Value("${SSO_LOGIN_URL}")
	private String SSO_LOGIN_URL;

	/**
	 * 处理器处理之前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1、拦截请求url
		// 2、从cookie中取token
		// 3、如果没有toke跳转到登录页面。
		// 4、取到token，需要调用sso系统的服务查询用户信息。
		User user = userService.getUserByToken(request, response);
		// 5、如果用户session已经过期，跳转到登录页面
		if (user == null) {
			/**
			 * sendRedirect和forward的区别：前者的URL会变，后者不变
			 */
			response.sendRedirect(SSO_LOGIN_URL+"?redirect="+request.getRequestURI());
			return false;
		}
		//将用户对象放入Request中
		request.setAttribute("user", user);
		// 6、如果没有过期，放行。
		return true;
	}

	/**
	 * 在处理器执行之后，返回ModelAndView之前
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 返回modelAndView之后
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
