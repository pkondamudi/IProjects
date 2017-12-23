package com.marrker.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.marrker.data.beans.ContextBoard;
import com.marrker.data.beans.User;
import com.marrker.data.managers.InvitationReferenceManager;

@Controller
@RequestMapping(value = { "/{refId}" })
public class InvitationReferenceController {
	
	@RequestMapping(value = { "/{refId}" }, method = RequestMethod.GET)
	public String verifyReference(ModelMap modal, @PathVariable("refId") String refId,
			@ModelAttribute ContextBoard contextBoard, HttpSession session, HttpServletRequest request) throws SQLException {
		String returnLoc = "main";
		InvitationReferenceManager invitationReferenceManager = new InvitationReferenceManager();
		if(invitationReferenceManager.isRefCodeExists(refId)){
			modal.addAttribute("User", new User());
			returnLoc = "signup";
		}
		return returnLoc;
	}
}
