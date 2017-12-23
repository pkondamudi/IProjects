package com.hnotch.beans.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class Application {

	private String applicationId;
	private String applicationName;
	private String applicationDescription;
	private String timestamp;
	private String deleted;
	private User user;
	private ArrayList<User> users;
	private ArrayList<ApplicationComment> comments;
	private HashMap<String, String> attributes;
	
	/* CONSTANTS */
	public static final String COLUMN_APPLICATION_ID = "ApplicationId";
	public static final String COLUMN_APPLICATION_NAME = "ApplicationName";
	public static final String COLUMN_APPLICATION_DESCRIPTION = "ApplicationDescription";
	public static final String COLUMN_APPLICATION_DELETED = "Delete";
	public static final String COLUMN_APPLICATION_TIMESTAMP = "timestamp";
	/* CONSTANTS */
	
	public Application(){
		this.applicationId = UUID.randomUUID().toString() + UUID.randomUUID().toString();
		this.timestamp = new Timestamp(new Date().getTime()).toString();
		this.deleted = "No";
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public void setApplicationDescription(String applicationDescription) {
		this.applicationDescription = applicationDescription;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public void setComments(ArrayList<ApplicationComment> comments) {
		this.comments = comments;
	}

	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public String getApplicationDescription() {
		return applicationDescription;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getDeleted() {
		return deleted;
	}

	public User getUser() {
		return user;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public ArrayList<ApplicationComment> getComments() {
		return comments;
	}

	public HashMap<String, String> getAttributes() {
		return attributes;
	}
	
	
}
