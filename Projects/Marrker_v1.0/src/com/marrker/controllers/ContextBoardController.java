package com.marrker.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.marrker.constant.values.AppConstants;
import com.marrker.data.beans.ContextBoard;
import com.marrker.data.beans.FollowType;
import com.marrker.data.beans.Post;
import com.marrker.data.beans.User;
import com.marrker.data.managers.ContextBoardManager;
import com.marrker.data.managers.PostManager;
import com.marrker.data.managers.UserManager;

@Controller
@RequestMapping("/ctx")
public class ContextBoardController {

	final static String IMAGE_RESOURCE_PATH = "/uploads";
	
	@RequestMapping(value = { "/createContext" }, method = RequestMethod.POST)
	public String createContextHome(ModelMap modal, @ModelAttribute ContextBoard contextBoard, HttpSession session)
			throws SQLException {

		ContextBoardManager contextBoardManager = new ContextBoardManager(
				(User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		System.out.print(contextBoard.getBoardType());
		String ctxId = contextBoardManager.createNewConetextBoard(contextBoard, false);
		return "redirect:"+ctxId;
	}
	
	@RequestMapping(value = { "/inheritContext" }, method = RequestMethod.POST)
	public String inheritContext(ModelMap modal, @ModelAttribute ContextBoard contextBoard, HttpSession session)
			throws SQLException {

		ContextBoardManager contextBoardManager = new ContextBoardManager(
				(User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		String ctxId = contextBoardManager.createNewConetextBoard(contextBoard, false);
		return "redirect:"+ctxId;
	}
	
	
	@RequestMapping(value = { "/deleteContext/{ctxid}" }, method = RequestMethod.GET)
	public ModelAndView deleteContext(ModelMap modal, @PathVariable("ctxid") String ctxid,  HttpSession session)
			throws SQLException {

		ContextBoardManager contextBoardManager = new ContextBoardManager(
				(User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		contextBoardManager.deleteConetextBoard(new ContextBoard(ctxid));
		return new ModelAndView("redirect:/userCreations");
	}
	
	@RequestMapping(value = { "/unFollow/{ctxid}" }, method = RequestMethod.GET)
	public ModelAndView unFollowContext(ModelMap modal, @PathVariable("ctxid") String ctxid,  HttpSession session)
			throws SQLException {

		UserManager userManager = new UserManager(
				(User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		userManager.unFollowContextBoard((new ContextBoard(ctxid)));
		return new ModelAndView("redirect:/userFollowing");
	}
	
	@RequestMapping(value = { "/editContext" }, method = RequestMethod.POST)
	public ModelAndView editContext(ModelMap modal, @ModelAttribute ContextBoard contextBoard,  HttpSession session)
			throws SQLException {

		ContextBoardManager contextBoardManager = new ContextBoardManager(
				(User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		return new ModelAndView("redirect:/ctx/"+contextBoardManager.updateConetextBoard(contextBoard, false));
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/{ctxid}" }, method = RequestMethod.GET)
	public String contextHome(ModelMap modal, @PathVariable("ctxid") String ctxid,
			@ModelAttribute ContextBoard contextBoard, HttpSession session, HttpServletRequest request) throws SQLException {
		modal.addAttribute("contextBoardObj", new ContextBoard());
		modal.addAttribute("ctxid", ctxid);
		modal.addAttribute("ctxPost", new Post());
		modal.addAttribute("followTypeObj", new FollowType());
		UserManager usermanager = new UserManager((User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		modal.addAttribute("defultContextBoard", usermanager.getDefaultContextBoard());
		PostManager postManager = new PostManager(new ContextBoard(ctxid), (User)session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		PagedListHolder<Post> contextBoardPostList;
		if (request.getParameter("nav") == null) {
			ArrayList<Post> posts = postManager.getContextBoardPosts();
			PagedListHolder<Post> postList = new PagedListHolder<Post>(posts);
			postList.setPage(0);postList.setPageSize(AppConstants.PAGE_SIZE);
			session.setAttribute("contextBoardList", postList);
			modal.addAttribute("posts", postList.getPageList());
		} else {
			String page = request.getParameter("nav");
			contextBoardPostList = (PagedListHolder<Post>) session.getAttribute("contextBoardList");
			if ("nxt".equals(page))
				contextBoardPostList.nextPage();
			else if ("pre".equals(page))
				contextBoardPostList.previousPage();
				modal.addAttribute("posts", contextBoardPostList.getPageList());
		}
		ContextBoardManager contextManager = new ContextBoardManager((User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		ContextBoard currentContext = contextManager.getCurrentContextBoard(ctxid);
		currentContext.setUser(usermanager.getCurrentUser(currentContext.getUser().getUserId()));
		modal.addAttribute("currentContext", currentContext);
		//modal.addAttribute("posts", posts);
		usermanager.updateUserContextVisit(new ContextBoard(ctxid));
		UserManager userManager = new UserManager((User)session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		modal.addAttribute("userCreatedBoards", userManager.getUserContextBoards());
		return "ctxh";
	}

	@RequestMapping(value = { "followContext" }, method = RequestMethod.POST)
	public String followContext(ModelMap modal, @ModelAttribute FollowType followType, HttpSession session)
			throws SQLException {
		UserManager userManager = new UserManager((User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		modal.addAttribute("defultContextBoard", userManager.getDefaultContextBoard());
		userManager.followNewContextBoard(new ContextBoard(followType.getContextBoardId()),
				new FollowType(followType.getFollowTypeName()));
		return "redirect:"+followType.getContextBoardId();
	}

	@RequestMapping(value = { "ctxhome" }, method = RequestMethod.GET)
	public String contextHm(ModelMap modal, @ModelAttribute ContextBoard contextBoard, HttpSession session)
			throws SQLException {
		return "ctxh";
	}
			
	@RequestMapping(value = { "uploadCtxCoverImage" }, method = RequestMethod.POST)
	public String uploadCtxCoverImage(ModelMap modal, @ModelAttribute Post post, HttpSession session, HttpServletRequest request)
			throws SQLException, IOException {
		String iRootDirectory = IMAGE_RESOURCE_PATH+"/coverImages";
		final List<String> allowedMIMEImageTypes = Arrays.asList("image/jpeg");
		BufferedOutputStream stream;
		List<MultipartFile> files = post.getFiles();
		ContextBoardManager contextManager = new ContextBoardManager((User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		ContextBoard contextBoard = contextManager.getCurrentContextBoard(post.getContextBoardId());
		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				System.out.println("Uploading files...");
				if (allowedMIMEImageTypes.contains(multipartFile.getContentType())) {
					System.out.println("Uploading image file...");
					String iFileName = contextBoard.getBoardId();
					String iExtension = ".jpg";
					String iDirectoryPath = 
							request.getServletContext().getRealPath(iRootDirectory);
					File iFile = new File(iDirectoryPath +"\\"+ iFileName + iExtension);
					iFile.createNewFile();
					stream = new BufferedOutputStream(
							new FileOutputStream(iFile));
					stream.write(multipartFile.getBytes());
					stream.close();
					contextBoard.setCoverImageLocation(iFileName);
					boolean isDefault = false;
					if(contextBoard.getIsDefault() == 1){
						isDefault = true;
					}
					contextManager.updateConetextBoard(contextBoard, isDefault);
				}

			}
		}

		return "redirect:"+post.getContextBoardId();
	}

	@RequestMapping(value = { "doCtxPost" }, method = RequestMethod.POST)
	public String doCntextPost(ModelMap modal, @ModelAttribute Post post, HttpSession session, HttpServletRequest request)
			throws SQLException, IOException {
		String vRootDirectory = IMAGE_RESOURCE_PATH+"/videos";
		String iRootDirectory = IMAGE_RESOURCE_PATH+"/images";
		final List<String> allowedMIMEImageTypes = Arrays.asList("image/jpeg");
		final List<String> allowedMIMEVideoTypes = Arrays.asList("video/mp4");
		PostManager postManager = new PostManager((User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		BufferedOutputStream stream;
		List<MultipartFile> files = post.getFiles();
		if (post.getPost().trim().length() > 0) {
			System.out.println("doing post...");
			if(post.getPostType().equalsIgnoreCase("open")){
				postManager.createNewPost(post,true);
			}
			else{
				postManager.createNewPost(post);
			}
		} else if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				System.out.println("Uploading files...");
				String vDirectoryPath = 
						request.getServletContext().getRealPath(vRootDirectory);
				if (allowedMIMEVideoTypes.contains(multipartFile.getContentType())) {
					System.out.println("Uploading video file...");
					String vFileName = UUID.randomUUID().toString() + UUID.randomUUID().toString();
					String vExtension = ".mp4";
					File vFile = new File(vDirectoryPath +"\\"+ vFileName + vExtension);
					vFile.createNewFile();
					stream = new BufferedOutputStream(
							new FileOutputStream(vFile));
					stream.write(multipartFile.getBytes());
					stream.close();
					post.setvLocation(vFileName);
					if (post.getPostType().equalsIgnoreCase("open")) {
						postManager.createNewPost(post, true);
					}
					else{
						postManager.createNewPost(post);
					}
				} else if (allowedMIMEImageTypes.contains(multipartFile.getContentType())) {
					System.out.println("Uploading image file...");
					String iDirectoryPath = 
							request.getServletContext().getRealPath(iRootDirectory);
					String iFileName = UUID.randomUUID().toString() + UUID.randomUUID().toString();
					String iExtension = ".jpg";
					File iFile = new File(iDirectoryPath +"\\"+ iFileName + iExtension);
					System.out.print(iDirectoryPath +"\\"+ iFileName + iExtension);
					iFile.createNewFile();
					stream = new BufferedOutputStream(
							new FileOutputStream(iFile));
					stream.write(multipartFile.getBytes());
					stream.close();
					post.setiLocation(iFileName);

					if(post.getPostType().equalsIgnoreCase("open")){
						postManager.createNewPost(post,true);
					}
					else{
						postManager.createNewPost(post);
					}
				}

			}
		}

		return "redirect:"+post.getContextBoardId();
	}

}
