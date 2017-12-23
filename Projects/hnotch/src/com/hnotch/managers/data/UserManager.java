package com.hnotch.managers.data;

import java.util.ArrayList;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.hnotch.beans.data.Application;
import com.hnotch.beans.data.User;
import com.hnotch.builders.data.UserBuilder;
import com.hnotch.interfaces.data.UserManagerInterface;

public class UserManager implements UserManagerInterface{


	public ResultSet results = null;
	InstallationManager installationManager;
	ApplicationManager applicationManager;
	
	@Override
	public boolean createUser(User User) {
		
		UserManager.session.execute(BOUND_STATEMENT_CREATE_USER.bind(User.getUserId(), User.getDeleted(), User.getEmailaddress(), User.getFirstName(), User.getLastName(), User.getPassword(), User.getTimestamp(), User.getGender()));
		return true;
	}

	@Override
	public boolean updateUser(User User) {
		
		UserManager.session.execute(BOUND_STATEMENT_UPDATE_USER.bind(User.getEmailaddress(), User.getFirstName(), User.getLastName(), User.getPassword(), User.getUserId()));
		return true;
	}

	@Override
	public boolean dropUser(User User) {
		
		UserManager.session.execute(BOUND_STATEMENT_DROP_USER.bind(User.getDeleted(), User.getUserId()));
		return true;
	}
	
	@Override
	public boolean validateUser(User User) {
		
		this.results = UserManager.session.execute(BOUND_STATEMENT_VALIDATE_USER.bind(User.getEmailaddress()));
		boolean isExists = false;
		
		if(this.results.all().size() > 0){
			isExists = true;
		}
		
		return isExists;
	}

	@Override
	public User authenticateUser(User User) {
		
		User user = null;
		this.results = UserManager.session.execute(BOUND_STATEMENT_AUTHENTICATE_USER.bind(User.getEmailaddress(), User.getPassword()));
		for (Row row : this.results){
			user = new UserBuilder()
					.setFirstName(row.getString(User.COLUMN_FIRSTNAME))
					.setLastName(row.getString(User.COLUMN_LASTNAME))
					.setEmailaddress(row.getString(User.COLUMN_EMAILADDRESS))
					.setDeleted(row.getString(User.COLUMN_DELETED))
					.setPassword(row.getString(User.COLUMN_PASSWORD))
					.setUserId(row.getString(User.COLUMN_USERID))
					.build();
		}
		
		return user;
	}

	@Override
	public ArrayList<Application> getUserApplications(User User) {
		this.applicationManager = new ApplicationManager();
		return this.applicationManager.getUserApplications(User);
	}

	@Override
	public ArrayList<Application> getInstalledApplications(User User) {
		this.installationManager = new InstallationManager();
		return this.installationManager.getInstalledApplications(User);
	}

	@Override
	public User getUserInfo(User User) {
		// TODO Auto-generated method stub
		User user = null;
		this.results = UserManager.session.execute(BOUND_STATEMENT_DETAILS_USER.bind(User.getUserId()));
		for (Row row : this.results){
			user = new UserBuilder()
					.setFirstName(row.getString(User.COLUMN_FIRSTNAME))
					.setLastName(row.getString(User.COLUMN_LASTNAME))
					.setEmailaddress(row.getString(User.COLUMN_EMAILADDRESS))
					.setDeleted(row.getString(User.COLUMN_DELETED))
					.setPassword(row.getString(User.COLUMN_PASSWORD))
					.setUserId(row.getString(User.COLUMN_USERID))
					.build();
		}
		return user;
	}

	@Override
	public User getUserByEmail(User User) {
		// TODO Auto-generated method stub
		User user = null;
		this.results = UserManager.session.execute(BOUND_STATEMENT_USER_BY_EMAIL.bind(User.getEmailaddress()));
		for (Row row : this.results){
			user = new UserBuilder()
					.setFirstName(row.getString(User.COLUMN_FIRSTNAME))
					.setLastName(row.getString(User.COLUMN_LASTNAME))
					.setEmailaddress(row.getString(User.COLUMN_EMAILADDRESS))
					.setDeleted(row.getString(User.COLUMN_DELETED))
					.setPassword(row.getString(User.COLUMN_PASSWORD))
					.setUserId(row.getString(User.COLUMN_USERID))
					.build();
		}
		return user;
	}
}
