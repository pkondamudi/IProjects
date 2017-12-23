package com.hnotch.beans.data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class ApplicationComment {

	private String commentId;
	private User user;
	private String comment;
	private String deleted;
	private String timestamp;
	private Application application;
	
	/* CONSTANTS */
	
	public static final String COLUMN_COMMENTID = "CommentId";
	public static final String COLUMN_COMMENT = "Comment";
	public static final String COLUMN_DELETED = "Deleted";
	public static final String COLUMN_TIMESTAMP = "Timestamp";
	/* CONSTANTS */
	
	public ApplicationComment() {
		// TODO Auto-generated constructor stub
		this.commentId = UUID.randomUUID().toString() + UUID.randomUUID().toString();
		this.timestamp = new Timestamp(new Date().getTime()).toString();
		this.deleted = "No";
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getCommentId() {
		return commentId;
	}

	public User getUser() {
		return user;
	}

	public String getComment() {
		return comment;
	}

	public String getDeleted() {
		return deleted;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public Application getApplication() {
		return application;
	}
	
	
	
	
}
