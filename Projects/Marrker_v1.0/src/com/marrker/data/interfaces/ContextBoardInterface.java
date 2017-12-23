/**
 * 
 */
package com.marrker.data.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.marrker.data.beans.ContextBoard;
import com.marrker.data.beans.Post;
import com.marrker.data.beans.User;

/**
 * @author PAVAN RAVIKANTH
 *
 */
public interface ContextBoardInterface {

	public boolean deleteConetextBoard(ContextBoard conetextBoard) throws SQLException;
	public ArrayList<ContextBoard> getUserContextBoards() throws SQLException;
	public ArrayList<ContextBoard> getUserFollowingContextBoards() throws SQLException;
	public ArrayList<User> getFollowingUsers() throws SQLException;
	public ArrayList<Post> getContextBoardPosts() throws SQLException;
	public String createNewConetextBoard(ContextBoard conetextBoard, boolean isDefault) throws SQLException;
	public String updateConetextBoard(ContextBoard conetextBoard, boolean isDefault) throws SQLException;
	public ArrayList<ContextBoard> getUserRecentlyUpdatedContextBoards() throws SQLException;
	public ContextBoard getDefaultContextBoard() throws SQLException;
	public ArrayList<ContextBoard> getUserFollowingNonPrivateContextBoards() throws SQLException;
	public ContextBoard getDefaultContextBoard(String userId) throws SQLException;
	public ArrayList<ContextBoard> getTrendingContextBoards() throws SQLException;
}
