package com.hnotch.interfaces.data;

import java.util.ArrayList;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.hnotch.beans.data.Application;
import com.hnotch.beans.data.User;
import com.hnotch.interfaces.database.DataBase;

public interface UserManagerInterface extends DataBase {

	public static final String QUERY_CREATE_USER = "INSERT INTO \"Users\"(\"UserId\", \"Deleted\", \"Emailaddress\", \"Firstname\", \"Lastname\",\"Password\", \"Timestamp\", \"Gender\") VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
	public static final String QUERY_UPDATE_USER = "UPDATE \"Users\" SET \"Emailaddress\" = ?, \"Firstname\" = ?, \"Lastname\" = ?,\"Password\" = ? WHERE \"UserId\" = ?";
	public static final String QUERY_DROP_USER = "UPDATE \"Users\" SET \"Deleted\" = 'Yes' WHERE \"UserId\" = ?";
	public static final String QUERY_VALIDATE_USER = "SELECT * FROM \"Users\" WHERE \"Emailaddress\" = ? ALLOW FILTERING";
	public static final String QUERY_DETAILS_USER = "SELECT * FROM \"Users\" WHERE \"UserId\" = ? ALLOW FILTERING";
	public static final String QUERY_AUTHENTICATE_USER = "SELECT * FROM \"Users\" WHERE \"Emailaddress\" = ? and \"Password\" = ? ALLOW FILTERING";
	public static final String QUERY_USER_BY_EMAIL = "SELECT * FROM \"Users\" WHERE \"Emailaddress\" = ? ALLOW FILTERING";
	
	/* CREATE USER */
	public static final PreparedStatement STATEMENT_CREATE_USER = session.prepare(QUERY_CREATE_USER);
	public static final BoundStatement BOUND_STATEMENT_CREATE_USER= new BoundStatement(STATEMENT_CREATE_USER);
	/* CREATE USER */
	
	/* UPDATE USER */
	public static final PreparedStatement STATEMENT_UPDATE_USER = session.prepare(QUERY_UPDATE_USER);
	public static final BoundStatement BOUND_STATEMENT_UPDATE_USER = new BoundStatement(STATEMENT_UPDATE_USER);
	/* UPDATE USER */
	
	/* DROP USER */
	public static final PreparedStatement STATEMENT_DROP_USER = session.prepare(QUERY_DROP_USER);
	public static final BoundStatement BOUND_STATEMENT_DROP_USER = new BoundStatement(STATEMENT_DROP_USER);
	/* DROP USER */
	
	/* AUTHENTICATE USER */
	public static final PreparedStatement STATEMENT_AUTHENTICATE_USER = session.prepare(QUERY_AUTHENTICATE_USER);
	public static final BoundStatement BOUND_STATEMENT_AUTHENTICATE_USER = new BoundStatement(STATEMENT_AUTHENTICATE_USER);
	/* AUTHENTICATE USER */
	
	/* VALIDATE USER */
	public static final PreparedStatement STATEMENT_VALIDATE_USER = session.prepare(QUERY_VALIDATE_USER);
	public static final BoundStatement BOUND_STATEMENT_VALIDATE_USER = new BoundStatement(STATEMENT_VALIDATE_USER);
	/* VALIDATE USER */
	
	/* VALIDATE USER */
	public static final PreparedStatement STATEMENT_DETAILS_USER = session.prepare(QUERY_DETAILS_USER);
	public static final BoundStatement BOUND_STATEMENT_DETAILS_USER = new BoundStatement(STATEMENT_DETAILS_USER);
	/* VALIDATE USER */
	
	/* USER BY EMAIL */
	public static final PreparedStatement STATEMENT_USER_BY_EMAIL = session.prepare(QUERY_USER_BY_EMAIL);
	public static final BoundStatement BOUND_STATEMENT_USER_BY_EMAIL = new BoundStatement(STATEMENT_USER_BY_EMAIL);
	/* USER BY EMAIL */
	
	
	public boolean createUser(User User);
	public boolean updateUser(User User);
	public boolean dropUser(User User);
	public User authenticateUser(User User);
	public User getUserByEmail(User User);
	
	public ArrayList<Application> getUserApplications(User User);
	public ArrayList<Application> getInstalledApplications(User User);
	public User getUserInfo(User User);
	boolean validateUser(User User); 
}
