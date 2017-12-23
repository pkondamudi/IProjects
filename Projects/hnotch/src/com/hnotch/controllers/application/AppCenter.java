package com.hnotch.controllers.application;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hnotch.beans.data.ApplicationComment;
import com.hnotch.beans.data.Installation;
import com.hnotch.beans.data.User;
import com.hnotch.builders.data.ApplicationBuilder;
import com.hnotch.builders.data.ApplicationCommentBuilder;
import com.hnotch.managers.data.ApplicationCommentsManager;
import com.hnotch.managers.data.ApplicationManager;
import com.hnotch.managers.data.InstallationManager;
import com.hnotch.values.constants.Constants;

@Controller
@RequestMapping("/app")
public class AppCenter {

	
	@RequestMapping(value = "delete/{applicationId}", method = RequestMethod.GET)
	public String appCenterdeleteApp(HttpSession session, @PathVariable("applicationId") int applicationId, @ModelAttribute ApplicationComment applicationComment, BindingResult results, ModelMap model) {
		//model.addAttribute("User", new User());
		ApplicationManager applicationManager = new ApplicationManager();
		applicationManager.dropApplication(new ApplicationBuilder().setApplicationId(new Integer(applicationId).toString()).build());
		model.addAttribute(Constants.MODEL_ATTRIBUTE_COMMENT, new ApplicationComment());
		model.addAttribute(Constants.MODEL_MESSAGE_ATTRIBUTE, 1);
		return "redirect:/appcenter";
	}
	
	@RequestMapping(value = "install/{applicationId}", method = RequestMethod.GET)
	public String appCenterinstallApp(HttpSession session, @PathVariable("applicationId") int applicationId, @ModelAttribute ApplicationComment applicationComment, BindingResult results, ModelMap model) {
		//model.addAttribute("User", new User());
		InstallationManager installationManager = new InstallationManager();
		installationManager.createInstallation(new Installation(), new ApplicationBuilder().setApplicationId(new Integer(applicationId).toString()).build(),  (User)session.getAttribute(Constants.SESSION_ATTRIBUTE_USER));
		model.addAttribute(Constants.MODEL_ATTRIBUTE_COMMENT, new ApplicationComment());
		model.addAttribute(Constants.MODEL_MESSAGE_ATTRIBUTE, 11);
		return "redirect:/allapps";
	}
	
	@RequestMapping(value = "uninstall/{applicationId}", method = RequestMethod.GET)
	public String appCenteruninstallApp(HttpSession session, @PathVariable("applicationId") int applicationId, ModelMap model) {
		//model.addAttribute("User", new User());
		Installation installation;
		InstallationManager installationManager = new InstallationManager();
		installation = installationManager.getInstallation(new ApplicationBuilder().setApplicationId(new Integer(applicationId).toString()).build(),  (User)session.getAttribute(Constants.SESSION_ATTRIBUTE_USER));
		installationManager.dropInstallation(installation);
		return "redirect:/home";
	}
	
	@RequestMapping(value = "comment/{applicationId}", method = RequestMethod.POST)
	public String appCenterAddComment(HttpSession session, @PathVariable("applicationId") int applicationId, @ModelAttribute ApplicationComment applicationComment, BindingResult results, ModelMap model) {
		//model.addAttribute("User", new User());
		ApplicationCommentsManager applicationCommentsManagger = new ApplicationCommentsManager();
		applicationCommentsManagger.createApplicationComment(new ApplicationBuilder().setApplicationId(new Integer(applicationId).toString()).build(), applicationComment, (User)session.getAttribute(Constants.SESSION_ATTRIBUTE_USER));
		model.addAttribute(Constants.MODEL_ATTRIBUTE_COMMENT, new ApplicationComment());
		model.addAttribute(Constants.MODEL_MESSAGE_ATTRIBUTE, 3);
		return "redirect:/appcenter";
	}
	
	@RequestMapping(value = "comment/delete/{commentId}", method = RequestMethod.GET)
	public String appCenterAddComment(HttpSession session, @PathVariable("commentId") String commentId, @ModelAttribute ApplicationComment applicationComment, BindingResult results, ModelMap model) {
		//model.addAttribute("User", new User());
		ApplicationCommentsManager applicationCommentsManager = new ApplicationCommentsManager();
		applicationCommentsManager.dropApplicationComment(new ApplicationCommentBuilder().setCommentId(commentId).build());
		model.addAttribute(Constants.MODEL_ATTRIBUTE_COMMENT, new ApplicationComment());
		model.addAttribute(Constants.MODEL_MESSAGE_ATTRIBUTE, 6);
		return "redirect:/appcenter";
	}
	
}
