package com.marrker.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class Main {

	@RequestMapping(value = {"/", "main"}, method =  RequestMethod.GET)
	public String index(ModelMap modal){
		return "index";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public View logout(HttpSession session, ModelMap model) {
		session.invalidate();
		return new RedirectView("");
	}
	
}
