package com.hnotch.builders.data;

import com.hnotch.beans.data.User;

public class UserBuilder {

	private User User = new User();
	
	public UserBuilder setUserId(String userId) {
		this.User.setUserId(userId);
		return this;
	}
	
	public UserBuilder setFirstName(String firstName) {
		this.User.setFirstName(firstName);
		return this;
	}
	
	public UserBuilder setLastName(String lastName) {
		this.User.setLastName(lastName);
		return this;
	}
	
	public UserBuilder setPassword(String password) {
		this.User.setPassword(password);
		return this;
	}
	
	public UserBuilder setEmailaddress(String emailaddress) {
		this.User.setEmailaddress(emailaddress);
		return this;
	}
	
	public UserBuilder setTimestamp(String timestamp) {
		this.User.setTimestamp(timestamp);
		return this;
	}
	public UserBuilder setDeleted(String deleted) {
		this.User.setDeleted(deleted);
		return this;
	}
	public UserBuilder setGender(String gender) {
		this.User.setGender(gender);
		return this;
	}
	public User build(){
		return this.User;
	}
}
