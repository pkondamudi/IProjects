package com.marrker.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.marrker.constant.values.AppConstants;
import com.marrker.data.beans.ContextBoard;
import com.marrker.data.beans.FollowType;
import com.marrker.data.beans.Post;
import com.marrker.data.beans.User;
import com.marrker.data.managers.ContextBoardManager;
import com.marrker.data.managers.InvitationReferenceManager;
import com.marrker.data.managers.PostManager;
import com.marrker.data.managers.UserManager;
import com.marrker.util.EmailHelper;
import com.marrker.util.MD5Util;

@Controller
public class UserController {

	@RequestMapping(value = {"signup"}, method =  RequestMethod.GET)
	public String signup(ModelMap model, @ModelAttribute User user, BindingResult result){
		model.addAttribute("User", new User());
		return "signup";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"followingUsers/{ctxid}"}, method =  RequestMethod.GET)
	public String followingUsers(ModelMap modal, @ModelAttribute User user, 
			BindingResult result, HttpSession session, 
			HttpServletRequest request,
			@PathVariable("ctxid") String ctxid) throws SQLException{
		modal.addAttribute("contextBoard", new ContextBoard());
		User authUser = (User)session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
		UserManager userManager = new UserManager(authUser);
		PagedListHolder<ContextBoard> contextBoardList;
		if(authUser != null){
			if (request.getParameter("nav") == null) {
				ArrayList<ContextBoard> followingUsers = userManager.getFollowingUsers(new ContextBoard(ctxid));
				System.out.println("UC: Follwers count: "+followingUsers.size());
				PagedListHolder<ContextBoard> followingUsersList = new PagedListHolder<ContextBoard>(followingUsers);
				followingUsersList.setPage(0);followingUsersList.setPageSize(AppConstants.PAGE_SIZE);
				session.setAttribute("followingUsersList", followingUsersList);
				modal.addAttribute("followingUsers", followingUsersList.getPageList());
			} else {
				String page = request.getParameter("nav");
				contextBoardList = (PagedListHolder<ContextBoard>) session.getAttribute("followingUsersList");
				if ("nxt".equals(page))
					contextBoardList.nextPage();
				else if ("pre".equals(page))
					contextBoardList.previousPage();
				
				modal.addAttribute("followingUsers", contextBoardList.getPageList());
			}
			ArrayList<ContextBoard> userNewPostBoards = userManager.getUserContextBoards();
			userNewPostBoards.addAll(userManager.getUserFollowingNonPrivateContextBoards());
			modal.addAttribute("userCreatedBoards", userNewPostBoards);
			modal.addAttribute("multiPost", new Post());
			
			ContextBoardManager contextManager = new ContextBoardManager((User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
			ContextBoard currentContext = contextManager.getCurrentContextBoard(ctxid);
			currentContext.setUser(userManager.getCurrentUser(currentContext.getUser().getUserId()));
			modal.addAttribute("currentContext", currentContext);
			
			//modal.addAttribute("userBoards", userBoards);
		}
		modal.addAttribute("defultContextBoard", userManager.getDefaultContextBoard());
		return "followingUsers";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"userHome"}, method =  RequestMethod.GET)
	public String userHome(ModelMap modal, @ModelAttribute User user, BindingResult result, HttpSession session, HttpServletRequest request) throws SQLException{
		modal.addAttribute("contextBoard", new ContextBoard());
		User authUser = (User)session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
		PagedListHolder<ContextBoard> contextBoardList;
		if(authUser != null){
			UserManager userManager = new UserManager(authUser);
			modal.addAttribute("defultContextBoard", userManager.getDefaultContextBoard());
			if (request.getParameter("nav") == null) {
				ArrayList<ContextBoard> userBoards = userManager.getUserRecentlyUpdatedContextBoards();
				PagedListHolder<ContextBoard> userBoardsList = new PagedListHolder<ContextBoard>(userBoards);
				userBoardsList.setPage(0);userBoardsList.setPageSize(AppConstants.PAGE_SIZE);
				session.setAttribute("userBoardsListRecentlyUpdated", userBoardsList);
				modal.addAttribute("userBoards", userBoardsList.getPageList());
			} else {
				String page = request.getParameter("nav");
				contextBoardList = (PagedListHolder<ContextBoard>) session.getAttribute("userBoardsListRecentlyUpdated");
				if ("nxt".equals(page))
					contextBoardList.nextPage();
				else if ("pre".equals(page))
					contextBoardList.previousPage();
				
				modal.addAttribute("userBoards", contextBoardList.getPageList());
			}
			ArrayList<ContextBoard> userNewPostBoards = userManager.getUserContextBoards();
			userNewPostBoards.addAll(userManager.getUserFollowingNonPrivateContextBoards());
			modal.addAttribute("userCreatedBoards", userNewPostBoards);
			modal.addAttribute("multiPost", new Post());
			//modal.addAttribute("userBoards", userBoards);
		}
		return "userhome";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"trending"}, method =  RequestMethod.GET)
	public String Trending(ModelMap modal, @ModelAttribute User user, BindingResult result, HttpSession session, HttpServletRequest request) throws SQLException{
		modal.addAttribute("contextBoard", new ContextBoard());
		User authUser = (User)session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
		PagedListHolder<ContextBoard> contextBoardList;
		if(authUser != null){
			UserManager userManager = new UserManager(authUser);
			modal.addAttribute("defultContextBoard", userManager.getDefaultContextBoard());
			if (request.getParameter("nav") == null) {
				ArrayList<ContextBoard> userBoards = userManager.getTrendingContextBoards();
				PagedListHolder<ContextBoard> userBoardsList = new PagedListHolder<ContextBoard>(userBoards);
				userBoardsList.setPage(0);userBoardsList.setPageSize(AppConstants.PAGE_SIZE);
				session.setAttribute("trendingUserBoardsList", userBoardsList);
				modal.addAttribute("trendingUserBoards", userBoardsList.getPageList());
			} else {
				String page = request.getParameter("nav");
				contextBoardList = (PagedListHolder<ContextBoard>) session.getAttribute("trendingUserBoardsList");
				if ("nxt".equals(page))
					contextBoardList.nextPage();
				else if ("pre".equals(page))
					contextBoardList.previousPage();
				
				modal.addAttribute("trendingUserBoards", contextBoardList.getPageList());
			}
			ArrayList<ContextBoard> userNewPostBoards = userManager.getUserContextBoards();
			userNewPostBoards.addAll(userManager.getUserFollowingNonPrivateContextBoards());
			modal.addAttribute("userCreatedBoards", userNewPostBoards);
			modal.addAttribute("multiPost", new Post());
			//modal.addAttribute("userBoards", userBoards);
		}
		return "trending";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"postsHome"}, method =  RequestMethod.GET)
	public String postsHome(ModelMap modal, @ModelAttribute User user, BindingResult result, HttpSession session, HttpServletRequest request) throws SQLException{
		modal.addAttribute("contextBoard", new ContextBoard());
		User authUser = (User)session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
		PagedListHolder<Post> postList;
		if(authUser != null){
			PostManager postManager = new PostManager(authUser);
			UserManager userManager = new UserManager(authUser);
			modal.addAttribute("defultContextBoard", userManager.getDefaultContextBoard());
			if (request.getParameter("nav") == null) {
				//ArrayList<ContextBoard> userBoards = userManager.getUserRecentlyUpdatedContextBoards();
				ArrayList<Post> userPosts = postManager.getUserContextBoardPosts();
				PagedListHolder<Post> userPostsList = new PagedListHolder<Post>(userPosts);
				userPostsList.setPage(0);userPostsList.setPageSize(AppConstants.PAGE_SIZE);
				session.setAttribute("userPostsListRecentlyUpdated", userPostsList);
				modal.addAttribute("userPosts", userPostsList.getPageList());
			} else {
				String page = request.getParameter("nav");
				postList = (PagedListHolder<Post>) session.getAttribute("userPostsListRecentlyUpdated");
				if ("nxt".equals(page))
					postList.nextPage();
				else if ("pre".equals(page))
					postList.previousPage();
				
				modal.addAttribute("userPosts", postList.getPageList());
			}
			ArrayList<ContextBoard> userNewPostBoards = userManager.getUserContextBoards();
			userNewPostBoards.addAll(userManager.getUserFollowingNonPrivateContextBoards());
			modal.addAttribute("userCreatedBoards", userNewPostBoards);
			modal.addAttribute("multiPost", new Post());
			//modal.addAttribute("userBoards", userBoards);
		}
		return "postHome";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"userCreations"}, method =  RequestMethod.GET)
	public String userCreations(ModelMap modal, @ModelAttribute User user, BindingResult result, HttpSession session, HttpServletRequest request) throws SQLException{
		modal.addAttribute("contextBoard", new ContextBoard());
		User authUser = (User)session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
		PagedListHolder<ContextBoard> contextBoardList;
		if(authUser != null){
			UserManager userManager = new UserManager(authUser);
			modal.addAttribute("defultContextBoard", userManager.getDefaultContextBoard());
			if (request.getParameter("nav") == null) {
				ArrayList<ContextBoard> userBoards = userManager.getUserContextBoards();
				PagedListHolder<ContextBoard> userBoardsList = new PagedListHolder<ContextBoard>(userBoards);
				userBoardsList.setPage(0);userBoardsList.setPageSize(AppConstants.PAGE_SIZE);
				session.setAttribute("userBoardsListCreations", userBoardsList);
				modal.addAttribute("userBoards", userBoardsList.getPageList());
			} else {
				String page = request.getParameter("nav");
				contextBoardList = (PagedListHolder<ContextBoard>) session.getAttribute("userBoardsListCreations");
				if ("nxt".equals(page))
					contextBoardList.nextPage();
				else if ("pre".equals(page))
					contextBoardList.previousPage();
					modal.addAttribute("userBoards", contextBoardList.getPageList());
			}
			//modal.addAttribute("userBoards", userBoards);
			ArrayList<ContextBoard> userNewPostBoards = userManager.getUserContextBoards();
			userNewPostBoards.addAll(userManager.getUserFollowingNonPrivateContextBoards());
			modal.addAttribute("userCreatedBoards", userNewPostBoards);
		}
		return "mycreations";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"userFollowing"}, method =  RequestMethod.GET)
	public String userFollowing(ModelMap modal, @ModelAttribute User user, BindingResult result, HttpSession session, HttpServletRequest request) throws SQLException{
		modal.addAttribute("contextBoard", new ContextBoard());
		User authUser = (User)session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
		PagedListHolder<ContextBoard> contextBoardList;
		if(authUser != null){
			UserManager userManager = new UserManager(authUser);
			modal.addAttribute("defultContextBoard", userManager.getDefaultContextBoard());
			if (request.getParameter("nav") == null) {
				ArrayList<ContextBoard> userBoards = userManager.getUserFollowingContextBoards();
				PagedListHolder<ContextBoard> userBoardsList = new PagedListHolder<ContextBoard>(userBoards);
				userBoardsList.setPage(0);userBoardsList.setPageSize(AppConstants.PAGE_SIZE);
				session.setAttribute("userBoardsListFollows", userBoardsList);
				modal.addAttribute("userBoards", userBoardsList.getPageList());
			} else {
				String page = request.getParameter("nav");
				contextBoardList = (PagedListHolder<ContextBoard>) session.getAttribute("userBoardsListFollows");
				if ("nxt".equals(page))
					contextBoardList.nextPage();
				else if ("pre".equals(page))
					contextBoardList.previousPage();
					modal.addAttribute("userBoards", contextBoardList.getPageList());
			}
			//modal.addAttribute("userBoards", userBoards);
			ArrayList<ContextBoard> userNewPostBoards = userManager.getUserContextBoards();
			userNewPostBoards.addAll(userManager.getUserFollowingNonPrivateContextBoards());
			modal.addAttribute("userCreatedBoards", userNewPostBoards);
		}
		return "userfollows";
	}
	
	@RequestMapping(value = {"signup"}, method =  RequestMethod.POST)
	public String signUp(ModelMap model, @ModelAttribute User user, BindingResult resultO) throws SQLException{
		
		String returnLocation = "signup";
		
		ArrayList<String> errors = new ArrayList<String>();
		
		UserManager userManager = new UserManager();
		
		
		if(!user.getPassword().contentEquals(user.getRePassword())){
			errors.add("Passwords did not match");
		}
		
		if(user.getPassword().length() == 0) {
			errors.add("Password field is empty");
		}
		
		if(user.getFirstname().length() == 0){
			errors.add("Firstname is empty");
		}
		
		if(user.getLastname().length() == 0){
			errors.add("Lastname is empty");
		}
		
		if(user.getEmail().length() == 0){
			errors.add("Email is empty");
		}
		
		if(! isValidEmailAddress(user.getEmail())){
			errors.add("Not a valid email");
		}
		
		/*if(user.getGender().contentEquals("Gender")){
			errors.add("Gender not selected");
		}*/
		
		if(userManager.validateUser(user)){
			errors.add("This Emailaddress is already used.");
		}
		
		if(userManager.validateUsername(user)){
			System.out.println(user.getEmail());
			errors.add("This username is already used.");
		}
		
		if(errors.size() == 0){
			user.setPassword(MD5Util.md5(user.getPassword()));
			//User.prepareFinalObject(new User());
			ContextBoard contextBoard = new ContextBoard();
			user.setUserId(userManager.createNewUser(user));
			ContextBoardManager contextBoardManager = new ContextBoardManager(user);
			contextBoard.setBoardName(user.getFirstname()+" "+user.getLastname());
			contextBoard.setBoardDescription(user.getFirstname()+"'s personal board.");
			contextBoard.setBoardType("Private");
			contextBoardManager.createNewConetextBoard(contextBoard, true);
			userManager.setUser(user);
			userManager.followNewContextBoard(new ContextBoard("feee8261-f4c6-412d-abc9-3dd2d584dd9d"),
					new FollowType("Always_Inherit"));
			model.addAttribute("msg", "Signup successful. Please Signin.");
			returnLocation = "signin";
		}
		else{
			model.addAttribute("errors", errors);
			setRequestAttributes(model, user);
		}
		model.addAttribute("User", new User()); 
		return returnLocation;
	}
	
	@RequestMapping(value = {"signin"}, method =  RequestMethod.GET)
	public String signin(ModelMap modal){
		return "signin";
	}
	
	@RequestMapping(value = {"landing"}, method =  RequestMethod.GET)
	public String gotoLanding(ModelMap modal){
		return "landing";
	}
	
	@RequestMapping(value = {"signin"}, method =  RequestMethod.POST)
	public String signinUser(ModelMap modal, HttpSession session, @ModelAttribute User user, BindingResult result) throws SQLException{
		UserManager userManager = new UserManager(user);
		
		int errors = 0;
		
		String controlReturn = "signin";
		
		modal.addAttribute(AppConstants.SESSION_ATTRIBUTE_USER, new User());
		
		User authUser = userManager.authenticate();
		
		if(authUser != null){
			session.setAttribute("User", authUser);
			controlReturn = "userHome";
		}
		else{
			errors = 1;
		}
		
		if(! isValidEmailAddress(user.getEmail())){
			errors = 7;
		}
		modal.addAttribute("errors", errors);
		return "redirect:"+controlReturn;
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
		User userFromSession = (User)session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
		changeUser = userManager.getCurrentUser(userFromSession.getUserId());
		System.out.println("Old Password:"+ user.getPassword());
		
		if(!MD5Util.md5(user.getOldPassword()).contentEquals(changeUser.getPassword())){
			errors = 32;
			System.out.println(errors);
		}
		if(!user.getPassword().contentEquals(user.getRePassword())){
			errors = 45;
			System.out.println(errors);
		}
		if(errors == 0 ){
			System.out.println("New Password:"+ user.getPassword());
			changeUser.setPassword(MD5Util.md5(user.getPassword()));
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
		
		if(! isValidEmailAddress(user.getEmail())){
			errors = 1;
		}
		
		if(errors == 0){
			changeUser = new UserManager().getUserByEmail(user);
			if(changeUser != null){
				String newPassword = new Integer(new Random().nextInt(8)*100000).toString();
				System.out.println("Old Password:"+ changeUser.getPassword());
				changeUser.setPassword(MD5Util.md5(newPassword));
				System.out.println("New Password:"+ changeUser.getPassword());
				new UserManager().updateUser(changeUser);
				new EmailHelper().sendEmail(changeUser.getEmail(), newPassword);
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"search"}, method =  RequestMethod.GET)
	public String search(ModelMap modal, @ModelAttribute ContextBoard contextBoard, BindingResult result, HttpSession session, HttpServletRequest request) throws SQLException{
		modal.addAttribute("contextBoard", new ContextBoard());
		User authUser = (User)session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
		PagedListHolder<ContextBoard> contextBoardList;
		if(authUser != null){
			UserManager userManager = new UserManager(authUser);
			modal.addAttribute("defultContextBoard", userManager.getDefaultContextBoard());
			if (request.getParameter("nav") == null) {
				ArrayList<ContextBoard> searchBoards = userManager.getContextBoardSearch(contextBoard.getBoardName());
				PagedListHolder<ContextBoard> searchBoardsList = new PagedListHolder<ContextBoard>(searchBoards);
				searchBoardsList.setPage(0);searchBoardsList.setPageSize(AppConstants.PAGE_SIZE);
				session.setAttribute("searchBoardsList", searchBoardsList);
				modal.addAttribute("userSearchBoards", searchBoardsList.getPageList());
			} else {
				String page = request.getParameter("nav");
				contextBoardList = (PagedListHolder<ContextBoard>) session.getAttribute("searchBoardsList");
				if ("nxt".equals(page))
					contextBoardList.nextPage();
				else if ("pre".equals(page))
					contextBoardList.previousPage();
				
				modal.addAttribute("userSearchBoards", contextBoardList.getPageList());
			}

			ArrayList<ContextBoard> userNewPostBoards = userManager.getUserContextBoards();
			userNewPostBoards.addAll(userManager.getUserFollowingNonPrivateContextBoards());
			modal.addAttribute("userCreatedBoards", userNewPostBoards);
			
			modal.addAttribute("multiPost", new Post());
			modal.addAttribute("query", contextBoard.getBoardName());
			//modal.addAttribute("userBoards", userBoards);
		}
		return "ctxSearch";
	}
	
	
	@RequestMapping(value = "/invite", method = RequestMethod.GET)
	public String invite(ModelMap model) {
		model.addAttribute("User", new User());
		return "invite";
	}
	
	@RequestMapping(value = "/invite", method = RequestMethod.POST)
	public String processInvite(@ModelAttribute User user, BindingResult result, ModelMap model, HttpSession session) throws Exception {
		int errors = 0 ;
		
		ArrayList<String> validEmails = new ArrayList<String>();
		
		
		StringTokenizer st2 = new StringTokenizer(user.getEmail(), ",");
		String nextEmail = null;
		while (st2.hasMoreElements()) {
			nextEmail = st2.nextElement().toString();
			if(isValidEmailAddress(nextEmail)){
				validEmails.add(nextEmail);
			}
		}
		
		
		if(errors == 0){
			User authUser = (User)session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER);
			authUser = new UserManager(authUser).getUserByEmail(authUser);
			if(authUser != null){
				InvitationReferenceManager invitationReferenceManager = new InvitationReferenceManager();
				invitationReferenceManager.putNewRefCodeAndSendInvitations(validEmails, authUser);
				errors = 88;
			}
			else{
				errors = 94;
			}
		}
		
		model.addAttribute("errors", errors);
		model.addAttribute("User", new User());
		return "redirect:/invite";
	}

	private void setRequestAttributes(ModelMap model, User User){
		model.addAttribute(User.COLUMN_FIRSTNAME, User.getFirstname());
		model.addAttribute(User.COLUMN_LASTNAME, User.getLastname());
		model.addAttribute(User.COLUMN_EMAILADDRESS, User.getEmail());
		model.addAttribute(User.COLUMN_USERNAME, User.getUsername());
	}
	
	
	private boolean isValidEmailAddress(String email) {
		// TODO Auto-generated method stub
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
