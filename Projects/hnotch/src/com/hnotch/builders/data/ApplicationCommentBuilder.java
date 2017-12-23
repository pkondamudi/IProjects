package com.hnotch.builders.data;

import com.hnotch.beans.data.Application;
import com.hnotch.beans.data.ApplicationComment;
import com.hnotch.beans.data.User;

public class ApplicationCommentBuilder {

	private ApplicationComment comment = new ApplicationComment();
	
	public ApplicationCommentBuilder setCommentId(String commentId) {
		this.comment.setCommentId(commentId);
		return this;
	}
	
	public ApplicationCommentBuilder setApplication(Application application) {
		this.comment.setApplication(application);
		return this;
	}
	public ApplicationCommentBuilder setUser(User user) {
		this.comment.setUser(user);
		return this;
	}
	public ApplicationCommentBuilder setComment(String comment) {
		this.comment.setComment(comment);
		return this;
	}
	public ApplicationCommentBuilder setDeleted(String deleted) {
		this.comment.setDeleted(deleted);
		return this;
	}
	public ApplicationCommentBuilder setTimestamp(String timestamp) {
		this.comment.setTimestamp(timestamp);
		return this;
	}
	
	public ApplicationComment build(){
		return this.comment;
	}
}
