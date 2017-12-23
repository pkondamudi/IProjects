package com.marrker.data.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.marrker.data.beans.Post;
import com.marrker.data.beans.PostType;

public interface PostInterface {

	public boolean createNewPost(Post post) throws SQLException;
	public boolean updatePost(Post post) throws SQLException;
	public boolean deletePost(Post post) throws SQLException;
	public ArrayList<Post> getContextBoardPosts() throws SQLException;
	public ArrayList<PostType> getPostTypes() throws SQLException;
	public Post getCurrentPost(String postId) throws SQLException;
	public boolean doRepost(Post post) throws SQLException;
	public boolean createNewPost(Post post, boolean isOpen) throws SQLException;
	public ArrayList<Post> getUserContextBoardPosts() throws SQLException;
}
