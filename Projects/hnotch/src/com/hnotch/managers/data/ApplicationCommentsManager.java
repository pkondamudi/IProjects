package com.hnotch.managers.data;

import java.util.ArrayList;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.hnotch.beans.data.Application;
import com.hnotch.beans.data.ApplicationComment;
import com.hnotch.beans.data.User;
import com.hnotch.builders.data.ApplicationCommentBuilder;
import com.hnotch.builders.data.UserBuilder;
import com.hnotch.interfaces.data.ApplicationCommentsManagerInterface;

public class ApplicationCommentsManager implements ApplicationCommentsManagerInterface{

	ResultSet results = null;
	
	@Override
	public boolean createApplicationComment(Application application, ApplicationComment applicationComment, User user) {
		
		ApplicationCommentsManager.session.execute(BOUND_STATEMENT_CREATE_COMMENT.bind(applicationComment.getCommentId(), application.getApplicationId(), applicationComment.getComment(), user.getUserId(), applicationComment.getTimestamp(), applicationComment.getDeleted()));
		return true;
	}

	@Override
	public boolean updateApplicationComment(ApplicationComment applicationComment) {
		
		ApplicationCommentsManager.session.execute(BOUND_STATEMENT_UPDATE_COMMENT.bind(applicationComment.getComment(), applicationComment.getCommentId()));
		return true;
	}

	@Override
	public boolean dropApplicationComment(ApplicationComment applicationComment) {
		
		ApplicationCommentsManager.session.execute(BOUND_STATEMENT_DROP_COMMENT.bind(applicationComment.getCommentId()));
		return true;
	}

	@Override
	public ArrayList<ApplicationComment> getApplicationComments(Application application) {
		// TODO Auto-generated method stub
		ArrayList<ApplicationComment> applicationsComments = new ArrayList<ApplicationComment>();
		this.results = ApplicationCommentsManager.session.execute(BOUND_STATEMENT_GET_APPLICATION_COMMENTS.bind(application.getApplicationId()));
		for (Row row : results){
			applicationsComments.add(new ApplicationCommentBuilder().setApplication(application)
					.setComment(row.getString(ApplicationComment.COLUMN_COMMENT))
					.setCommentId(row.getString(ApplicationComment.COLUMN_COMMENTID))
					.setDeleted(row.getString(ApplicationComment.COLUMN_DELETED))
					.setTimestamp(row.getString(ApplicationComment.COLUMN_TIMESTAMP))
					.setUser(new UserManager().getUserInfo(new UserBuilder().setUserId(row.getString("UserId")).build()))
					.build());
		}
		return applicationsComments;
	}

	
}
