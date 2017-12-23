package com.marrker.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.marrker.constant.values.AppConstants;
import com.marrker.data.beans.Post;
import com.marrker.data.beans.User;
import com.marrker.data.managers.PostManager;

@Controller
@RequestMapping(value = "/pst")
public class PostController {

	final static String IMAGE_RESOURCE_PATH = "/uploads";

	@RequestMapping(value = { "/deletePost/{pstid}/{ctxid}" }, method = RequestMethod.GET)
	public ModelAndView deleteContext(ModelMap modal, @PathVariable("pstid") String pstid,
			@PathVariable("ctxid") String ctxid, HttpSession session) throws SQLException {

		PostManager postManager = new PostManager((User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		postManager.deletePost(new Post(pstid));
		return new ModelAndView("redirect:/ctx/" + ctxid);
	}

	@RequestMapping(value = { "/Repost/{pstid}/{ctxid}" }, method = RequestMethod.GET)
	public ModelAndView rePost(ModelMap modal, @PathVariable("pstid") String pstid, @PathVariable("ctxid") String ctxid,
			HttpSession session) throws SQLException {

		PostManager postManager = new PostManager((User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		Post post = postManager.getCurrentPost(pstid);
		postManager.doRepost(post);
		return new ModelAndView("redirect:/ctx/" + ctxid);
	}

	@RequestMapping(value = { "/multiPost" }, method = RequestMethod.POST)
	public String doCntextPost(ModelMap modal, @ModelAttribute Post post, HttpSession session,
			HttpServletRequest request) throws SQLException, IOException {
		String vRootDirectory = IMAGE_RESOURCE_PATH + "/videos";
		String iRootDirectory = IMAGE_RESOURCE_PATH + "/images";
		final List<String> allowedMIMEImageTypes = Arrays.asList("image/jpeg");
		final List<String> allowedMIMEVideoTypes = Arrays.asList("video/mp4");
		PostManager postManager = new PostManager((User) session.getAttribute(AppConstants.SESSION_ATTRIBUTE_USER));
		BufferedOutputStream stream;
		List<MultipartFile> files = post.getFiles();
		String publishBoardIds[] = request.getParameterValues("publishBoardIds");
		for (int i = 0; i < publishBoardIds.length; i++) {

		}
		if (post.getPost().trim().length() > 0) {
			System.out.println("doing post...");
			for (int i = 0; i < publishBoardIds.length; i++) {
				post.setContextBoardId(publishBoardIds[i]);
				System.out.println("Inserting for: "+publishBoardIds[i]);
				postManager.createNewPost(post);
			}
			if (post.getPostType().equalsIgnoreCase("open")) {
				postManager.doRepost(post);
			}
		} else if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				System.out.println("Uploading files...");
				String vDirectoryPath = request.getServletContext().getRealPath(vRootDirectory);
				if (allowedMIMEVideoTypes.contains(multipartFile.getContentType())) {
					System.out.println("Uploading video file...");
					String vFileName = UUID.randomUUID().toString() + UUID.randomUUID().toString();
					String vExtension = ".mp4";
					File vFile = new File(vDirectoryPath + "\\" + vFileName + vExtension);
					vFile.createNewFile();
					stream = new BufferedOutputStream(new FileOutputStream(vFile));
					stream.write(multipartFile.getBytes());
					stream.close();
					post.setvLocation(vFileName);
					for (int i = 0; i < publishBoardIds.length; i++) {
						post.setContextBoardId(publishBoardIds[i]);
						postManager.createNewPost(post);
					}
					if (post.getPostType().equalsIgnoreCase("open")) {
						postManager.doRepost(post);
					}
				} else if (allowedMIMEImageTypes.contains(multipartFile.getContentType())) {
					System.out.println("Uploading image file...");
					String iDirectoryPath = request.getServletContext().getRealPath(iRootDirectory);
					String iFileName = UUID.randomUUID().toString() + UUID.randomUUID().toString();
					String iExtension = ".jpg";
					File iFile = new File(iDirectoryPath + "\\" + iFileName + iExtension);
					System.out.print(iDirectoryPath + "\\" + iFileName + iExtension);
					iFile.createNewFile();
					stream = new BufferedOutputStream(new FileOutputStream(iFile));
					stream.write(multipartFile.getBytes());
					stream.close();
					post.setiLocation(iFileName);
					for (int i = 0; i < publishBoardIds.length; i++) {
						post.setContextBoardId(publishBoardIds[i]);
						postManager.createNewPost(post);
					}
					if (post.getPostType().equalsIgnoreCase("open")) {
						postManager.doRepost(post);
					}
				}

			}
		}

		return "redirect:/postsHome";
	}
}
