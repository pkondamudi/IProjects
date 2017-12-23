package com.marrker.data.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.marrker.data.beans.ContextBoard;
import com.marrker.data.beans.FollowType;
import com.marrker.data.beans.User;

public interface UserInterface {

	public User authenticate() throws SQLException;
	public String createNewUser(User user) throws SQLException;
	public boolean updateUser(User user) throws SQLException;
	public boolean deleteUser(User user) throws SQLException;
	public boolean followNewContextBoard(ContextBoard contextBoard, FollowType followType) throws SQLException;
	public ArrayList<ContextBoard> getUserContextBoards() throws SQLException;
	public ArrayList<ContextBoard> getUserFollowingContextBoards() throws SQLException;
	boolean validateUser(User User) throws SQLException;
	boolean updateUserContextVisit(ContextBoard contextBoard) throws SQLException;
	public ArrayList<ContextBoard> getUserRecentlyUpdatedContextBoards() throws SQLException;
	boolean unFollowContextBoard(ContextBoard contextBoard) throws SQLException;
	public User getCurrentUser(String UserId) throws SQLException;
	public ContextBoard getDefaultContextBoard() throws SQLException;
	public ArrayList<ContextBoard> getUserFollowingNonPrivateContextBoards() throws SQLException;
	boolean validateUsername(User user) throws SQLException;
	public ContextBoard getDefaultContextBoard(String userId) throws SQLException;
}
