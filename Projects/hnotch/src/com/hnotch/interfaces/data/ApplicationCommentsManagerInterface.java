package com.hnotch.interfaces.data;

import java.util.ArrayList;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.hnotch.beans.data.Application;
import com.hnotch.beans.data.ApplicationComment;
import com.hnotch.beans.data.User;
import com.hnotch.interfaces.database.DataBase;

public interface ApplicationCommentsManagerInterface extends DataBase {

	public static final String QUERY_CREATE_COMMENT = "INSERT INTO \"ApplicationComments\"(\"CommentId\", \"ApplicationId\", \"Comment\" , \"UserId\", \"Timestamp\", \"Deleted\") VALUES(?, ?, ?, ?, ?, ?)";
	public static final String QUERY_UPDATE_COMMENT = "UPDATE \"ApplicationComments\" SET \"Comment\" = ? WHERE \"CommentId\" = ?";
	public static final String QUERY_DROP_COMMENT = "UPDATE \"ApplicationComments\" SET \"Deleted\" = 'Yes' WHERE \"CommentId\" = ?";
	public static final String QUERY_GET_APPLICATION_COMMENTS = "SELECT * FROM \"ApplicationComments\" WHERE \"ApplicationId\" = ? AND \"Deleted\" = 'No' ALLOW FILTERING";
	
	/* CREATE APPLICATION */
	public static final PreparedStatement STATEMENT_CREATE_COMMENT = session.prepare(QUERY_CREATE_COMMENT);
	public static final BoundStatement BOUND_STATEMENT_CREATE_COMMENT = new BoundStatement(STATEMENT_CREATE_COMMENT);
	/* CREATE APPLICATION */
	
	/* UPDATE APPLICATION */
	public static final PreparedStatement STATEMENT_UPDATE_COMMENT = session.prepare(QUERY_UPDATE_COMMENT);
	public static final BoundStatement BOUND_STATEMENT_UPDATE_COMMENT = new BoundStatement(STATEMENT_UPDATE_COMMENT);
	/* UPDATE APPLICATION */
	
	/* DROP APPLICATION */
	public static final PreparedStatement STATEMENT_DROP_COMMENTS = session.prepare(QUERY_DROP_COMMENT);
	public static final BoundStatement BOUND_STATEMENT_DROP_COMMENT= new BoundStatement(STATEMENT_DROP_COMMENTS);
	/* DROP APPLICATION */
	
	/* DROP APPLICATION */
	public static final PreparedStatement STATEMENT_GET_APPLICATION_COMMENTS = session.prepare(QUERY_GET_APPLICATION_COMMENTS);
	public static final BoundStatement BOUND_STATEMENT_GET_APPLICATION_COMMENTS= new BoundStatement(STATEMENT_GET_APPLICATION_COMMENTS);
	/* DROP APPLICATION */
	
	public boolean createApplicationComment(Application application, ApplicationComment applicationComment, User user);
	public boolean updateApplicationComment(ApplicationComment applicationComment);
	public boolean dropApplicationComment(ApplicationComment applicationComment);
	public ArrayList<ApplicationComment> getApplicationComments(Application application);
	
}
