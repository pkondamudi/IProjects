package com.hnotch.interfaces.data;

import java.util.ArrayList;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.hnotch.beans.data.Application;
import com.hnotch.beans.data.ApplicationComment;
import com.hnotch.beans.data.Attribute;
import com.hnotch.beans.data.User;
import com.hnotch.interfaces.database.DataBase;

public interface ApplicationManagerInterface extends DataBase {

	public static final String QUERY_CREATE_APPLICATION = "INSERT INTO \"Applications\"(\"ApplicationId\", \"ApplicationDescription\", \"ApplicationName\", \"Delete\", \"Timestamp\", \"UserId\") VALUES(?, ?, ?, ?, ?, ?)";
	public static final String QUERY_UPDATE_APPLICATION = "UPDATE \"Applications\" SET \"ApplicationDescription\" = ?, \"ApplicationName\" = ? WHERE \"ApplicationId\" = ?";
	public static final String QUERY_DROP_APPLICATION = "UPDATE \"Applications\" SET \"Delete\" = 'Yes' WHERE \"ApplicationId\" = ?";
	public static final String QUERY_GET_USER_APPLICATIONS = "SELECT * FROM \"Applications\" WHERE \"UserId\" = ? AND \"Delete\" = 'No' ALLOW FILTERING";
	public static final String QUERY_GET_APPLICATIONS = "SELECT * FROM \"Applications\" WHERE \"ApplicationId\" = ? ALLOW FILTERING";
	public static final String QUERY_GET_ALL_APPLICATIONS = "SELECT * FROM \"Applications\"  WHERE \"Delete\" = 'No'ALLOW FILTERING";
	
	/* CREATE APPLICATION */
	public static final PreparedStatement STATEMENT_CREATE_APPLICATION = session.prepare(QUERY_CREATE_APPLICATION);
	public static final BoundStatement BOUND_STATEMENT_CREATE_APPLICATION = new BoundStatement(STATEMENT_CREATE_APPLICATION);
	/* CREATE APPLICATION */
	
	/* UPDATE APPLICATION */
	public static final PreparedStatement STATEMENT_UPDATE_APPLICATION = session.prepare(QUERY_UPDATE_APPLICATION);
	public static final BoundStatement BOUND_STATEMENT_UPDATE_APPLICATION = new BoundStatement(STATEMENT_UPDATE_APPLICATION);
	/* UPDATE APPLICATION */
	
	/* DROP APPLICATION */
	public static final PreparedStatement STATEMENT_DROP_APPLICATION = session.prepare(QUERY_DROP_APPLICATION);
	public static final BoundStatement BOUND_STATEMENT_DROP_APPLICATION = new BoundStatement(STATEMENT_DROP_APPLICATION);
	/* DROP APPLICATION */
	
	/* USER APPLICATION */
	public static final PreparedStatement STATEMENT_GET_USER_APPLICATIONS = session.prepare(QUERY_GET_USER_APPLICATIONS);
	public static final BoundStatement BOUND_STATEMENT_GET_USER_APPLICATIONS = new BoundStatement(STATEMENT_GET_USER_APPLICATIONS);
	/* USER APPLICATION */
	
	/* APPLICATIONS */
	public static final PreparedStatement STATEMENT_GET__APPLICATIONS = session.prepare(QUERY_GET_APPLICATIONS);
	public static final BoundStatement BOUND_STATEMENT_GET_APPLICATIONS = new BoundStatement(STATEMENT_GET__APPLICATIONS);
	/* APPLICATIONS */
	
	/* APPLICATIONS */
	public static final PreparedStatement STATEMENT_GET_ALL_APPLICATIONS = session.prepare(QUERY_GET_ALL_APPLICATIONS);
	public static final BoundStatement BOUND_STATEMENT_GET_ALL_APPLICATIONS = new BoundStatement(STATEMENT_GET_ALL_APPLICATIONS);
	/* APPLICATIONS */
	
	
	public boolean createApplication(Application application);
	public boolean createApplication(Application application, ArrayList<Attribute> attributes);
	public boolean updateApplication(Application application);
	public boolean dropApplication(Application application);
	
	public boolean addApplicationComments(ApplicationComment applicationComment);
	public boolean addApplicationAttributes(Application application, ArrayList<Attribute> attributes);
	public boolean addApplicationUserInstallation(User user);
	
	public ArrayList<ApplicationComment> getApplicationComments(Application application);
	public ArrayList<Attribute> getApplicationAttributes(Application application);
	public ArrayList<Application> getUserApplications(User User);
	public ArrayList<Application> getApplications(ArrayList<Application> applications);
	public ArrayList<Application> getAllApplications(User user);
}
