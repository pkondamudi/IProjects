package com.marrker.data.beans;

import java.sql.Timestamp;

public class User {

	private String userId;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String dOB;
	private String email;
	private Timestamp timestamp;
	private boolean disabled;
	private String rePassword;
	private String oldPassword;
	private String defaultContextId;
	private String loc;
	
	
	/* CONSTANTS */
	
	public final String COLUMN_USERID= "UserId";
	public final String COLUMN_FIRSTNAME = "Firstname";
	public final String COLUMN_LASTNAME = "Lastname";
	public final String COLUMN_PASSWORD = "Password";
	public final String COLUMN_EMAILADDRESS = "Email";
	public final String COLUMN_TIMESTAMP = "Timestamp";
	public final String COLUMN_DELETED = "disabled";
	public final String COLUMN_USERNAME = "Username";
	public final String COLUMN_LOC = "Loc";
	
	/* CONSTANTS */
	
	public User() {
		// TODO Auto-generated constructor stub
		this.disabled = false;
	}
	
	public User(String userId) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
	}

	public User(String UserId, String Username, String Password, String firname, String Lastname, String DOB,
			String Email, Timestamp timestamp, int isDisabled) {
		// TODO Auto-generated constructor stub
		this.username = Username;
		this.userId = UserId;
		this.password = Password;
		this.firstname = firname;
		this.lastname = Lastname;
		this.dOB = DOB;
		this.email = Email;
		this.timestamp = timestamp;
		if(isDisabled == 1){
			this.disabled = true;
		}else{
			this.disabled = false;
		}
	}
	
	

	public String getDefaultContextId() {
		return defaultContextId;
	}

	public void setDefaultContextId(String defaultContextId) {
		this.defaultContextId = defaultContextId;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getdOB() {
		return dOB;
	}

	public void setdOB(String dOB) {
		this.dOB = dOB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
	
}
