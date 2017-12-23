package com.marrker.data.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import com.marrker.data.beans.ContextBoard;
import com.marrker.data.beans.FollowType;
import com.marrker.data.beans.User;
import com.marrker.data.interfaces.UserInterface;
import com.marrker.db.manager.DataBaseManager;
import com.marrker.util.MD5Util;

public class UserManager implements UserInterface{

	private User user;
	public static final String INSERT_USER = "INSERT INTO `pavankon_marrker`.`user`"
			+ "(`userid`,`username`,`password`,`firstname`,`lastname`,`DOB`,`email`, `loc`) "
			+ "VALUES(?,?,?,?,?,?,?,?)";
	
	public static final String UPDATE_USER = "UPDATE `pavankon_marrker`.`user`"
			+ " SET `password` = ?,"
			+ "`firstname` = ?,`lastname` = ?,`DOB` = ?,"
			+ "`email` = ?,`disables` = ? "
			+ "WHERE `userid` = ?";
	
	public static final String UPDATE_USER_CONTEXT_VISIT = "UPDATE `pavankon_marrker`.`user_board_xref`"
			+ " SET lastvisit = ?"
			+ " WHERE `userid` = ? AND contextboardid = ?";
	
	public static final String DELETE_USER = "DELETE FROM `pavankon_marrker`.`user` WHERE `userid = ?`";
	
	public static final String FOLLOW_BOARD = "INSERT INTO `pavankon_marrker`.`user_board_xref`(`UserBoardXrefid`,`UserId`,`contextboardid`,`followtypeid`,`lastvisit`) "
			+ "VALUES(UUID(),?,?,?,?);";
	
	
	public static final String UNFOLLOW_BOARD = "DELETE FROM `pavankon_marrker`.`user_board_xref` "
			+ "WHERE ContextBoardId = ? AND UserId = ?";
	
	public static final String AUTHNTICATE_USER = "SELECT * FROM `pavankon_marrker`.`user` WHERE Email = ? AND Password = ?";
	
	public static final String GET_USER_FOLLOWTYPES = "SELECT * FROM followtypes";
	public static final String VALIDATE_USER = "SELECT * FROM `pavankon_marrker`.`user` WHERE email IN(?)";
	public static final String VALIDATE_USERNAME = "SELECT * FROM `pavankon_marrker`.`user` WHERE username IN(?)";
	public static final String GET_CURRENT_USER = "SELECT * FROM `pavankon_marrker`.`user` WHERE UserId IN(?)";
	public static final String GET_USER_BY_EMAIL = "SELECT * FROM `pavankon_marrker`.`user` WHERE Email IN(?)";
	
	public UserManager(User user){
		this.setUser(user);
	}

	public UserManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User authenticate() throws SQLException {
		// TODO Auto-generated method stub

		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(AUTHNTICATE_USER);
		pst.setString(1, this.user.getEmail());
		pst.setString(2, MD5Util.md5(this.user.getPassword()));
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst,UserManager.AUTHNTICATE_USER);
		if(rs.next()){
			prepareUserObject(rs);
		}
		else{
			this.user = null;
		}
		dataBaseManager.stopDatabaseOperation();
		return this.user;
	}

	private void prepareUserObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub

		this.user.setdOB(rs.getString("DOB"));
		this.user.setEmail(rs.getString("email"));
		this.user.setFirstname(rs.getString("firstname"));
		this.user.setLastname(rs.getString("lastname"));
		this.user.setTimestamp(rs.getTimestamp("timestamp"));
		this.user.setUserId(rs.getString("userid"));
		this.user.setUsername(rs.getString("username"));
		this.user.setLoc(rs.getString("loc"));
	}

	@Override
	public String createNewUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(UserManager.INSERT_USER);
		String userUUID = UUID.randomUUID().toString();
		pst.setString(1, userUUID);
		pst.setString(2, user.getUsername());
		pst.setString(3, user.getPassword());
		pst.setString(4, user.getFirstname());
		pst.setString(5, user.getLastname());
		pst.setString(6, user.getdOB());
		pst.setString(7, user.getEmail());
		pst.setString(8, user.getLoc());
		dataBaseManager.getDatabaseOprationResult(pst);
		dataBaseManager.stopDatabaseOperation();
		return userUUID;
	}

	@Override
	public boolean updateUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(UserManager.UPDATE_USER);
		pst.setString(1, user.getPassword());
		pst.setString(2, user.getFirstname());
		pst.setString(3, user.getLastname());
		pst.setString(4, user.getdOB());
		pst.setString(5, user.getEmail());
		if(user.isDisabled()){
			pst.setInt(6, 1);	
		}else{
			pst.setInt(6, 0);
		}
		pst.setString(7, user.getUserId());
		dataBaseManager.getDatabaseOprationResult(pst);
		dataBaseManager.stopDatabaseOperation();
		return true;
	}

	@Override
	public boolean deleteUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(UserManager.DELETE_USER);
		pst.setString(1, user.getUserId());
		dataBaseManager.getDatabaseOprationResult(pst);
		dataBaseManager.stopDatabaseOperation();
		return true;
	}

	@Override
	public ArrayList<ContextBoard> getUserContextBoards() throws SQLException {
		// TODO Auto-generated method stub
		ContextBoardManager contextBoardManager = new ContextBoardManager(this.user);
		return contextBoardManager.getUserContextBoards();
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean followNewContextBoard(ContextBoard contextBoard, FollowType followType) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(UserManager.FOLLOW_BOARD);
		pst.setString(1, this.user.getUserId());
		pst.setString(2, contextBoard.getBoardId());
		System.out.println(followType.getFollowTypeByName().getFollowTypeName());
		pst.setString(3, followType.getFollowTypeByName().getFollowTypeId());
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new Timestamp(calendar.getTime().getTime());
		pst.setTimestamp(4, currentTimestamp);
		dataBaseManager.getDatabaseOprationResult(pst);
		dataBaseManager.stopDatabaseOperation();
		return true;
	}
	
	@Override
	public boolean updateUserContextVisit(ContextBoard contextBoard) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(UserManager.UPDATE_USER_CONTEXT_VISIT);
		pst.setString(2, this.user.getUserId());
		pst.setString(3, contextBoard.getBoardId());
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new Timestamp(calendar.getTime().getTime());
		pst.setTimestamp(1, currentTimestamp);
		dataBaseManager.getDatabaseOprationResult(pst);
		dataBaseManager.stopDatabaseOperation();
		return true;
	}

	@Override
	public boolean unFollowContextBoard(ContextBoard contextBoard) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(UserManager.UNFOLLOW_BOARD);
		pst.setString(1, contextBoard.getBoardId());
		pst.setString(2, this.user.getUserId());
		dataBaseManager.getDatabaseOprationResult(pst);
		dataBaseManager.stopDatabaseOperation();
		return true;
	}

	@Override
	public ArrayList<ContextBoard> getUserFollowingContextBoards() throws SQLException {
		// TODO Auto-generated method stub
		ContextBoardManager contextBoardManager = new ContextBoardManager(this.user);
		return contextBoardManager.getUserFollowingContextBoards();
	}
	
	@Override
	public ArrayList<ContextBoard> getUserFollowingNonPrivateContextBoards() throws SQLException {
		// TODO Auto-generated method stub
		ContextBoardManager contextBoardManager = new ContextBoardManager(this.user);
		return contextBoardManager.getUserFollowingNonPrivateContextBoards();
	}
	
	@Override
	public ArrayList<ContextBoard> getUserRecentlyUpdatedContextBoards() throws SQLException {
		// TODO Auto-generated method stub
		ContextBoardManager contextBoardManager = new ContextBoardManager(this.user);
		return contextBoardManager.getUserRecentlyUpdatedContextBoards();
	}

	@Override
	public boolean validateUser(User user) throws SQLException {
		
		boolean isExists = false;
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(UserManager.VALIDATE_USER);
		pst.setString(1, user.getEmail());
		ResultSet validateUser = dataBaseManager.getDatabaseOprationResult(pst, UserManager.VALIDATE_USER);
		if(validateUser.next()){
			isExists = true;
		}
		dataBaseManager.stopDatabaseOperation();
		return isExists;
	}
	
	@Override
	public boolean validateUsername(User user) throws SQLException {
		
		boolean isExists = false;
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(UserManager.VALIDATE_USERNAME);
		pst.setString(1, user.getUsername());
		ResultSet validateUser = dataBaseManager.getDatabaseOprationResult(pst, UserManager.VALIDATE_USERNAME);
		if(validateUser.next()){
			isExists = true;
		}
		dataBaseManager.stopDatabaseOperation();
		return isExists;
	}
	
	@Override
	public User getCurrentUser(String UserId) throws SQLException {
		
		User user = null;
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(UserManager.GET_CURRENT_USER);
		pst.setString(1, UserId);
		ResultSet validateUser = dataBaseManager.getDatabaseOprationResult(pst, UserManager.GET_CURRENT_USER);
		if(validateUser.next()){
			user = new User(validateUser.getString(1),
							validateUser.getString(2),
							validateUser.getString(3),
							validateUser.getString(4),
							validateUser.getString(5),
							validateUser.getString(6),
							validateUser.getString(7),
							validateUser.getTimestamp(8),
							validateUser.getInt(9));
		}
		user.setDefaultContextId(getDefaultContextBoard(UserId).getBoardId());
		dataBaseManager.stopDatabaseOperation();
		return user;
	}

	@Override
	public ContextBoard getDefaultContextBoard(String userId) throws SQLException {
		// TODO Auto-generated method stub
		ContextBoardManager contextBoardManager = new ContextBoardManager(this.user);
		return contextBoardManager.getDefaultContextBoard(userId);
	}

	public User getUserByEmail(User user) throws SQLException {
		// TODO Auto-generated method stub
		User userByEmail = null;
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(UserManager.GET_USER_BY_EMAIL);
		pst.setString(1, user.getEmail());
		ResultSet validateUser = dataBaseManager.getDatabaseOprationResult(pst, UserManager.GET_USER_BY_EMAIL);
		if(validateUser.next()){
			userByEmail = new User(validateUser.getString(1),
							validateUser.getString(2),
							validateUser.getString(3),
							validateUser.getString(4),
							validateUser.getString(5),
							validateUser.getString(6),
							validateUser.getString(7),
							validateUser.getTimestamp(8),
							validateUser.getInt(9));
			System.out.println(validateUser.getString("firstname"));
		}
		dataBaseManager.stopDatabaseOperation();
		return userByEmail;
	}

	public ArrayList<ContextBoard> getContextBoardSearch(String boardName) throws SQLException {
		// TODO Auto-generated method stub
		ContextBoardManager contextBoardManager = new ContextBoardManager(this.user);
		return contextBoardManager.getContextBoardSearch(boardName);
	}
	
	public ArrayList<ContextBoard> getFollowingUsers(ContextBoard contextBoard) throws SQLException {
		// TODO Auto-generated method stub
		ContextBoardManager contextBoardManager = new ContextBoardManager(this.user);
		return contextBoardManager.getFollowingUsers(contextBoard);
	}

	@Override
	public ContextBoard getDefaultContextBoard() throws SQLException {
		// TODO Auto-generated method stub
		ContextBoardManager contextBoardManager = new ContextBoardManager(this.user);
		return contextBoardManager.getDefaultContextBoard();
	}
	
	public ArrayList<ContextBoard> getTrendingContextBoards() throws SQLException {
		// TODO Auto-generated method stub
		ContextBoardManager contextBoardManager = new ContextBoardManager(this.user);
		return contextBoardManager.getTrendingContextBoards();
	}
	
	
}
