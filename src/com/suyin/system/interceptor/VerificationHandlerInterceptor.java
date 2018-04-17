package com.suyin.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.suyin.system.model.LoginUser;

/**
 * 权限拦截器
 * @author madara
 *
 */
public class VerificationHandlerInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		LoginUser loginUser = (LoginUser) request.getSession().getAttribute(
				"loginUser");

		String path = request.getServletPath();
		if (-1 != path.indexOf("login") || -1 != path.indexOf("resources") || -1 != path.indexOf("weChat")
				) {
			return true;
		} else {
			if (null == loginUser) {
				if (request.getHeader("x-requested-with") != null) {
					response.setHeader("sessionstatus", "timeout");
					return false;
				}
				response.sendRedirect(request.getContextPath() + "/login");
				return false;
			} else {
				return true;
			}
		}
	}
}
