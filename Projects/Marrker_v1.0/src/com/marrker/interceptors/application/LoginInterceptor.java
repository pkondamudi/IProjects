package com.marrker.interceptors.application;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.marrker.constant.values.AppConstants;
import com.marrker.data.beans.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
	    throws Exception {
		User user = (User) request.getSession().getAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
		if(user == null){
			response.sendRedirect(request.getContextPath());
			return false;
		}
		else{
			return true;
		}
		
	}
}
