package com.hnotch.controllers.application;

import java.util.ArrayList;
import java.util.Random;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hnotch.beans.data.User;
import com.hnotch.managers.data.UserManager;
import com.hnotch.util.EmailHelper;
import com.hnotch.util.HashGenerator;
import com.hnotch.values.constants.Constants;

@Controller
public class UserController {

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupUser(ModelMap model) {
		model.addAttribute("User", new User());
		return "signup";
	}
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String changePassword(ModelMap model) {
		model.addAttribute("User", new User());
		return "changepassword";
	}
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String processChangePassword(HttpSession session, @ModelAttribute User user, BindingResult result, ModelMap model) throws Exception {
		int errors = 0 ;
		User changeUser;
		UserManager userManager = new UserManager();
		changeUser = userManager.getUserInfo((User)session.getAttribute(Constants.SESSION_ATTRIBUTE_USER));
		System.out.println("Old Password:"+ changeUser.getPassword());
		if(!HashGenerator.generateMD5(user.getOldPassword()).contentEquals(changeUser.getPassword())){
			errors = 32;
			System.out.println(errors);
		}
		if(!user.getPassword().contentEquals(user.getRePassword())){
			errors = 45;
			System.out.println(errors);
		}
		if(errors == 0 ){
			changeUser.setPassword(HashGenerator.generateMD5(changeUser.getPassword()));
			System.out.println("New Password:"+ changeUser.getPassword());
			userManager.updateUser(changeUser);
			errors = 54;
		}
		model.addAttribute("errors", errors);
		model.addAttribute("User", new User());
		return "redirect:/changepassword";
	}
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String forgotPassword(ModelMap model) {
		model.addAttribute("User", new User());
		return "forgotpassword";
	}
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String processForgotPassword(@ModelAttribute User user, BindingResult result, ModelMap model) throws Exception {
		int errors = 0 ;
		
		User changeUser;
		
		if(! isValidEmailAddress(user.getEmailaddress())){
			errors = 1;
		}
		
		if(errors == 0){
			changeUser = new UserManager().getUserByEmail(user);
			if(changeUser != null){
				String newPassword = new Integer(new Random().nextInt(8)*100000).toString();
				System.out.println("Old Password:"+ changeUser.getPassword());
				changeUser.setPassword(HashGenerator.generateMD5(newPassword));
				System.out.println("New Password:"+ changeUser.getPassword());
				new UserManager().updateUser(changeUser);
				new EmailHelper().sendEmail(changeUser.getEmailaddress(), newPassword);
				errors = 87;
			}
			else{
				errors = 99;
			}
		}
		
		model.addAttribute("errors", errors);
		model.addAttribute("User", new User());
		return "redirect:/forgotpassword";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String processSignup(@ModelAttribute User User, BindingResult result, ModelMap model) throws Exception {
		
		String returnLocation = "signup";
		
		ArrayList<String> errors = new ArrayList<String>();
		
		UserManager userManager = new UserManager();
		
		
		if(!User.getPassword().contentEquals(User.getRePassword())){
			errors.add("Passwords did not match");
		}
		
		if(User.getPassword().length() == 0) {
			errors.add("Password field is empty");
		}
		
		if(User.getFirstName().length() == 0){
			errors.add("Firstname is empty");
		}
		
		if(User.getLastName().length() == 0){
			errors.add("Lastname is empty");
		}
		
		if(User.getEmailaddress().length() == 0){
			errors.add("Email is empty");
		}
		
		if(! isValidEmailAddress(User.getEmailaddress())){
			errors.add("Not a valid email");
		}
		
		if(User.getGender().contentEquals("Gender")){
			errors.add("Gender not selected");
		}
		
		if(userManager.validateUser(User)){
			errors.add("This Emailaddress is already used.");
		}
		
		if(errors.size() == 0){
			User.setPassword(HashGenerator.generateMD5(User.getPassword()));
			//User.prepareFinalObject(new User());
			userManager.createUser(User);
			model.addAttribute("msg", "Signup successful. Please Signin.");
			returnLocation = "signin";
		}
		else{
			model.addAttribute("errors", errors);
			setRequestAttributes(model, User);
		}
		model.addAttribute("User", new User());
		return returnLocation;
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin(ModelMap model) {
		model.addAttribute("User", new User());
		return "signin";
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String processSignin(@ModelAttribute User User, HttpSession session, BindingResult result, ModelMap model) throws Exception {
		
		int errors = 0;
		
		String controlReturn = "signin";
		
		UserManager userManager = new UserManager();
		
		model.addAttribute(Constants.SESSION_ATTRIBUTE_USER, new User());
		
		User.setPassword(HashGenerator.generateMD5(User.getPassword()));
		
		User authUser = userManager.authenticateUser(User);
		
		if(authUser != null){
			session.setAttribute("User", authUser);
			controlReturn = "home";
		}
		else{
			errors = 1;
		}
		
		if(! isValidEmailAddress(User.getEmailaddress())){
			errors = 7;
		}
		
		model.addAttribute("errors", errors);
		return "redirect:"+controlReturn;
	}
	
	private void setRequestAttributes(ModelMap model, User User){
		model.addAttribute(User.COLUMN_FIRSTNAME, User.getFirstName());
		model.addAttribute(User.COLUMN_LASTNAME, User.getLastName());
		model.addAttribute(User.COLUMN_EMAILADDRESS, User.getEmailaddress());
		model.addAttribute(User.COLUMN_GENDER, User.getGender());
	}
	
	public boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
}
