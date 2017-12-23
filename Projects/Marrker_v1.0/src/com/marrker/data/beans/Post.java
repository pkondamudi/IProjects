package com.marrker.data.beans;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Post {

	private String postId;
	private String postType;
	private String post;
	private String iLocation;
	private String vLocation;
	private String contextBoardId;
	private String userId;
	private Timestamp timestamp;
	private List<MultipartFile> files;
	private ContextBoard contextBoard;
	private User user;
	private boolean isOwner;
	
	public Post(String PostId, String postType, String post, ContextBoard contextBoard, 
			User user,	Timestamp timestamp, String iLocation, String vlocation, boolean isOwner) {
		// TODO Auto-generated constructor stub
		 this.postId = PostId;
		 this.postType = postType;
		 this.post = post;
		 this.contextBoard = contextBoard;
		 this.user = user;
		 this.vLocation = vlocation;
		 this.iLocation = iLocation;
		 this.timestamp = timestamp;
		 this.isOwner = isOwner;
	}
	
	public Post(String PostId, String postType, String post, ContextBoard contextBoard, 
			User user,	Timestamp timestamp, String iLocation, String vlocation) {
		// TODO Auto-generated constructor stub
		 this.postId = PostId;
		 this.postType = postType;
		 this.post = post;
		 this.contextBoard = contextBoard;
		 this.user = user;
		 this.vLocation = vlocation;
		 this.iLocation = iLocation;
		 this.timestamp = timestamp;
	}

	public Post() {
		// TODO Auto-generated constructor stub
	}

	
	public Post(String pstid) {
		// TODO Auto-generated constructor stub
		this.postId = pstid;
	}

	public boolean getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	public ContextBoard getContextBoard() {
		return contextBoard;
	}

	public void setContextBoard(ContextBoard contextBoard) {
		this.contextBoard = contextBoard;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getPost() {
		return post;
	}
	
	public void setPost(String post) {
		this.post = post;
	}
	

	
	
	public String getiLocation() {
		return iLocation;
	}

	public void setiLocation(String iLocation) {
		this.iLocation = iLocation;
	}

	public String getvLocation() {
		return vLocation;
	}

	public void setvLocation(String vLocation) {
		this.vLocation = vLocation;
	}

	public String getContextBoardId() {
		return contextBoardId;
	}

	public void setContextBoardId(String contextBoardId) {
		this.contextBoardId = contextBoardId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
