package com.hnotch.beans.data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class User {

	private String userId;
	private String firstName;
	private String lastName;
	private String password;
	private String emailaddress;
	private String timestamp;
	private String gender;
	private String deleted;
	private String rePassword;
	private String oldPassword;
	
	/* CONSTANTS */
	
	public final String COLUMN_USERID= "UserId";
	public final String COLUMN_FIRSTNAME = "FirstName";
	public final String COLUMN_LASTNAME = "LastName";
	public final String COLUMN_PASSWORD = "Password";
	public final String COLUMN_EMAILADDRESS = "Emailaddress";
	public final String COLUMN_TIMESTAMP = "Timestamp";
	public final String COLUMN_DELETED = "Deleted";
	public final String COLUMN_GENDER = "Gender";
	
	/* CONSTANTS */
	
	public User() {
		// TODO Auto-generated constructor stub
		this.userId = UUID.randomUUID().toString() + UUID.randomUUID().toString();
		this.timestamp = new Timestamp(new Date().getTime()).toString();
		this.deleted = "No";
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getGender() {
		return gender;
	}

	public String getDeleted() {
		return deleted;
	}

	public String getRePassword() {
		return rePassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	
}
