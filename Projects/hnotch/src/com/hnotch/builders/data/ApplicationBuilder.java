package com.hnotch.builders.data;

import java.util.ArrayList;
import java.util.HashMap;

import com.hnotch.beans.data.Application;
import com.hnotch.beans.data.ApplicationComment;
import com.hnotch.beans.data.User;

public class ApplicationBuilder {

	private Application application = new Application();
	
	
	public ApplicationBuilder setApplicationId(String applicationId) {
		this.application.setApplicationId(applicationId);
		return this;
	}
	
	public ApplicationBuilder setApplicationName(String applicationName) {
		this.application.setApplicationName(applicationName);
		return this;
	}
	
	public ApplicationBuilder setApplicationDescription(String applicationDescription) {
		this.application.setApplicationDescription(applicationDescription);
		return this;
	}
	
	public ApplicationBuilder setTimestamp(String timestamp) {
		this.application.setTimestamp(timestamp);
		return this;
	}
	
	public ApplicationBuilder setUser(User user) {
		this.application.setUser(user);
		return this;
	}
	
	public ApplicationBuilder setUsers(ArrayList<User> users) {
		this.application.setUsers(users);
		return this;
	}
	
	public ApplicationBuilder setDeleted(String deleted) {
		this.application.setDeleted(deleted);
		return this;
	}
	public ApplicationBuilder setComments(ArrayList<ApplicationComment> comments) {
		this.application.setComments(comments);
		return this;
	}
	public ApplicationBuilder setAttributes(HashMap<String, String> atributes) {
		this.application.setAttributes(atributes);
		return this;
	}
	public Application build(){
		return this.application;
	}
}
