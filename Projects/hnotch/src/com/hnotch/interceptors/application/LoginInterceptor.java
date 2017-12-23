package com.hnotch.interceptors.application;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hnotch.beans.data.User;
import com.hnotch.values.constants.Constants;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
	    throws Exception {
		User user = (User) request.getSession().getAttribute(Constants.SESSION_ATTRIBUTE_USER);
		if(user == null){
			response.sendRedirect("main");
			return false;
		}
		else{
			return true;
		}
		
	}
}
