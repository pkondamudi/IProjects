package com.marrker.data.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import com.marrker.data.beans.BoardType;
import com.marrker.data.beans.ContextBoard;
import com.marrker.data.beans.Post;
import com.marrker.data.beans.User;
import com.marrker.data.interfaces.ContextBoardInterface;
import com.marrker.db.manager.DataBaseManager;

public class ContextBoardManager implements ContextBoardInterface {

	private User user;
	private ContextBoard contextBoard;
	@SuppressWarnings("unused")
	private String SystemUserId = "MarrkerBot";
	
	public static final String INSERT_CONTEXTBOARD = "INSERT INTO `pavankon_marrker`.`contextboards`"
			+ "(`contextboardid`,`contextboardname`,`contextboarddescription`,"
			+ "`boardtypeid`,`inheritedBoardid`,`userId`,`isdefault`) "
			+ "VALUES(?,?,?,?,?,?,?)";
	public static final String UPDATE_CONTEXTBOARD = "UPDATE `pavankon_marrker`.`contextboards` "
			+ "SET `contextboardname` = ?,"
			+ "`contextboarddescription` = ?,"
			+ "`boardtypeid` = ?,"
			+ "coverimagelocation = ? "
			+ "WHERE `contextboardid` = ?";
	public static final String DELETE_CONTEXTBOARD = "DELETE FROM `pavankon_marrker`.`contextboards` "
			+ "WHERE `contextboardid` = ?";
	
	public static final String GET_USER_CONTEXTBOARDS = "SELECT * FROM `pavankon_marrker`.`contextboards` "
			+ "WHERE `userid` = ? ORDER BY Timestamp DESC";
	
	public static final String GET_USER_CONTEXTBOARD_FOLLOWING = "SELECT * FROM `pavankon_marrker`.`user` "
			+ "WHERE `userid` IN (select  userid from user_board_xref where boardid = ?)";
	
	public static final String GET_USER_FOLLOWING_CONTEXTBOARDS = "SELECT * FROM `pavankon_marrker`.`contextboards` "
			+ "WHERE `contextboardid` IN (select  contextboardid from user_board_xref where userid = ?) ORDER BY TIMESTAMP DESC";
	
	public static final String GET_USER_FOLLOWING_CONTEXTBOARDS_NON_PRIVATE = "SELECT * FROM `pavankon_marrker`.`contextboards` "
			+ "WHERE `contextboardid` IN (select  contextboardid from user_board_xref where userid = ?) AND "
			+ "BoardTypeId IN(SELECT BoardTypeId FROM contextboardtypes WHERE BoardType = 'Protected')  ORDER BY TIMESTAMP DESC";
	
	public static final String GET_USER_RECENTLY_UPDATED_CONTEXTBOARDS = "SELECT DISTINCT CB.* FROM `pavankon_marrker`.`contextboards` CB "
			+ "INNER JOIN `pavankon_marrker`.`posts` P ON P.ContextBoardId = CB.ContextBoardId "
			+ "Where CB.ContextBoardId IN(SELECT contextboardid FROM `pavankon_marrker`.`contextboards` "
			+ "WHERE `contextboardid` IN (select  contextboardid from user_board_xref where userid = ?)) "
			+ "ORDER BY P.Timestamp DESC";
	
	public static final String GET_CURRENT_CONTEXTBOARD = "SELECT * FROM `pavankon_marrker`.`contextboards` WHERE ContextBoardId = ?";
	
	public static final String GET_CURRENT_CONTEXTBOARD_FOLLOWING = "select  userid from `pavankon_marrker`.`user_board_xref` where userid = ? "
			+ "and contextboardid = ?";
	
	public static final String GET_CURRENT_CONTEXTBOARD_OWNER = "select  userid from `pavankon_marrker`.`contextboards` where userid = ? "
			+ "and contextboardid = ?";
	
	public static final String GET_USER_DEFAULT_CONTEXTBOARD = "SELECT * FROM `pavankon_marrker`.`contextboards` WHERE UserId = ? AND isdefault = '1'";
	
	public static final String GET_CONTEXTBOARDS_SEARCH = "SELECT ContextBoardId, ContextBoardName, "
			+ "ContextBoardDescription, MATCH (ContextBoardName, ContextBoardDescription) "
			+ "AGAINST (? IN NATURAL LANGUAGE MODE) AS score "
			+ "FROM `pavankon_marrker`.`contextboards` WHERE MATCH (ContextBoardName, ContextBoardDescription) "
			+ "AGAINST (? IN NATURAL LANGUAGE MODE) ORDER BY SCORE DESC";
	
	public static final String GET_FOLLOWERS_CONTEXTBOARDS = "select  count(*) as totalFollowers from `pavankon_marrker`.`user_board_xref` where contextboardid = ?";
	
	public static final String GET_POSTS_CONTEXTBOARDS = "select  count(*) as totalPosts from `pavankon_marrker`.`posts` where contextboardid = ?";
	
	public static final String GET_FOLLOWING_USERS = "SELECT * FROM `pavankon_marrker`.`contextboards` WHERE UserId IN (SELECT UserId FROM `pavankon_marrker`.`user_board_xref` WHERE ContextBoardId IN (?)) AND isdefault = '1' ";
	
	public static final String GET_TRENDING_CONTEXTBOARD = "SELECT * FROM `pavankon_marrker`.`contextboards` "
			+ "Where ContextBoardId NOT IN (SELECT contextboardid FROM `pavankon_marrker`.`contextboards` "
			+ "WHERE `contextboardid` IN (select  contextboardid from user_board_xref where userid = ?)) AND isdefault = '0'"
			//+ "AND UserId IN(select UserId from user where (Username = ? and Loc = ?) or Username = ?) "
			+ "Order By Timestamp DESC LIMIT 0, 50";
	

	public ContextBoardManager(User user){
		this.setUser(user);
	}

	public ContextBoardManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String createNewConetextBoard(ContextBoard conetextBoard, boolean isDefault) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.INSERT_CONTEXTBOARD);
		BoardType boardType = new BoardType();
		boardType.setContextBoardType(conetextBoard.getBoardType());
		String ctxUUID = UUID.randomUUID().toString();
		pst.setString(1, ctxUUID);
		if(conetextBoard.getBoardName().indexOf(' ') > 0 && ! isDefault){
			pst.setString(2, conetextBoard.getBoardName().substring(0, conetextBoard.getBoardName().indexOf(' ')));
		}else{
			pst.setString(2, conetextBoard.getBoardName());
		}
		pst.setString(3, conetextBoard.getBoardDescription());
		pst.setString(4, boardType.getBoardByName().getBoardTypeId());
		pst.setString(5, conetextBoard.getInheritedContextBoard());
		if(conetextBoard.getUserId() != null){
			pst.setString(6, conetextBoard.getUserId());
		}else{
			pst.setString(6, this.user.getUserId());
		}
		if(isDefault){
			pst.setInt(7, 1);
		}else{
			pst.setInt(7, 0);
		}
		dataBaseManager.getDatabaseOprationResult(pst);
		dataBaseManager.stopDatabaseOperation();
		return ctxUUID;
	}

	@Override
	public String updateConetextBoard(ContextBoard conetextBoard, boolean isDefault) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.UPDATE_CONTEXTBOARD);
		BoardType boardType = new BoardType();
		if(conetextBoard.getBoardType() != null){
			boardType.setContextBoardType(conetextBoard.getBoardType());	
		}else{
			boardType.setBoardTypeId(conetextBoard.getBoardTypeObj().getBoardTypeId());
		}
		if(conetextBoard.getBoardName().indexOf(' ') > 0){
			pst.setString(1, conetextBoard.getBoardName().substring(0, conetextBoard.getBoardName().indexOf(' ')));
		}else{
			pst.setString(1, conetextBoard.getBoardName());
		}
		pst.setString(2, conetextBoard.getBoardDescription());
		if(conetextBoard.getBoardType() != null){
			pst.setString(3, boardType.getBoardByName().getBoardTypeId());	
		}else{
			pst.setString(3, boardType.getBoardById().getBoardTypeId());
		}
		pst.setString(4, conetextBoard.getCoverImageLocation());
		pst.setString(5, conetextBoard.getBoardId());
		dataBaseManager.getDatabaseOprationResult(pst);
		dataBaseManager.stopDatabaseOperation();
		return conetextBoard.getBoardId();
	}

	@Override
	public boolean deleteConetextBoard(ContextBoard conetextBoard) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.DELETE_CONTEXTBOARD);
		pst.setString(1, conetextBoard.getBoardId());
		dataBaseManager.getDatabaseOprationResult(pst);
		dataBaseManager.stopDatabaseOperation();
		return true;
	}

	@Override
	public ArrayList<ContextBoard> getUserContextBoards() throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_USER_CONTEXTBOARDS);
		ArrayList<ContextBoard> contextBoards = new ArrayList<ContextBoard>();
		pst.setString(1, this.user.getUserId());
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_USER_CONTEXTBOARDS);
		while(rs.next()){
			contextBoards.add(new ContextBoard(rs.getString(1),
												rs.getString(2),
												rs.getString(3),
												rs.getTimestamp(4),
												new BoardType(rs.getString(5)),
												new BoardType(rs.getString(6)),
												new User(rs.getString(7)),
												rs.getInt(8),
												rs.getString(9)
												));
		}
		dataBaseManager.stopDatabaseOperation();
		return contextBoards;
	}

	@Override
	public ArrayList<User> getFollowingUsers() throws SQLException {
		// TODO Auto-generated method stub
		
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_USER_CONTEXTBOARD_FOLLOWING);
		ArrayList<User> users = new ArrayList<User>();
		pst.setString(1, this.contextBoard.getBoardId());
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_USER_CONTEXTBOARD_FOLLOWING);
		while(rs.next()){
			users.add(new User(rs.getString(1),
												rs.getString(2),
												rs.getString(3),
												rs.getString(4),
												rs.getString(5),
												rs.getString(6),
												rs.getString(7),
												rs.getTimestamp(8),
												rs.getInt(9)
												));
		}
		dataBaseManager.stopDatabaseOperation();
		return users;
	}

	@Override
	public ArrayList<Post> getContextBoardPosts() throws SQLException {
		// TODO Auto-generated method stub
		PostManager postManager = new PostManager(this.contextBoard);
		return postManager.getContextBoardPosts();
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public ArrayList<ContextBoard> getUserFollowingNonPrivateContextBoards() throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_USER_FOLLOWING_CONTEXTBOARDS_NON_PRIVATE);
		ArrayList<ContextBoard> contextBoards = new ArrayList<ContextBoard>();
		pst.setString(1, this.user.getUserId());
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_USER_FOLLOWING_CONTEXTBOARDS_NON_PRIVATE);
		while(rs.next()){
			contextBoards.add(new ContextBoard(rs.getString(1),
												rs.getString(2),
												rs.getString(3),
												rs.getTimestamp(4),
												new BoardType(rs.getString(5)),
												new BoardType(rs.getString(6)),
												new User(rs.getString(7)),
												rs.getInt(8),
												rs.getString(9)
												));
		}
		dataBaseManager.stopDatabaseOperation();
		return contextBoards;
	}
	
	@Override
	public ArrayList<ContextBoard> getUserFollowingContextBoards() throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_USER_FOLLOWING_CONTEXTBOARDS);
		ArrayList<ContextBoard> contextBoards = new ArrayList<ContextBoard>();
		pst.setString(1, this.user.getUserId());
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_USER_FOLLOWING_CONTEXTBOARDS);
		while(rs.next()){
			contextBoards.add(new ContextBoard(rs.getString(1),
												rs.getString(2),
												rs.getString(3),
												rs.getTimestamp(4),
												new BoardType(rs.getString(5)),
												new BoardType(rs.getString(6)),
												new User(rs.getString(7)),
												rs.getInt(8),
												rs.getString(9)
												));
		}
		dataBaseManager.stopDatabaseOperation();
		return contextBoards;
	}
	
	@Override
	public ArrayList<ContextBoard> getUserRecentlyUpdatedContextBoards() throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_USER_RECENTLY_UPDATED_CONTEXTBOARDS);
		ArrayList<ContextBoard> contextBoards = new ArrayList<ContextBoard>();
		pst.setString(1, this.user.getUserId());
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_USER_RECENTLY_UPDATED_CONTEXTBOARDS);
		while(rs.next()){
			contextBoards.add(new ContextBoard(rs.getString(1),
												rs.getString(2),
												rs.getString(3),
												rs.getTimestamp(4),
												new BoardType(rs.getString(5)),
												new BoardType(rs.getString(6)),
												new User(rs.getString(7)),
												rs.getInt(8),
												rs.getString(9)
												));
		}
		dataBaseManager.stopDatabaseOperation();
		return contextBoards;
	}

	public ContextBoard getCurrentContextBoard(String ctxid) throws SQLException {
		// TODO Auto-generated method stub
		
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_CURRENT_CONTEXTBOARD);
		pst.setString(1, ctxid);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_CURRENT_CONTEXTBOARD);
		ContextBoard contextBoard = null;
		BoardType boardType = null;
		while(rs.next()){
			contextBoard = new ContextBoard(rs.getString(1),
												rs.getString(2),
												rs.getString(3),
												rs.getTimestamp(4),
												new BoardType(rs.getString(5)),
												new BoardType(rs.getString(6)),
												new User(rs.getString(7)),
												rs.getInt(8),
												rs.getString(9)
												);
			boardType = new BoardType(rs.getString(5));
		}
		dataBaseManager.stopDatabaseOperation();
		contextBoard.setIsUserFollowing(isUserFollowingCurrentContextBoard(ctxid));
		contextBoard.setIsOwner(isOwner(ctxid));
		contextBoard.setTotalNumberOfFollowers(getFollowersCount(ctxid));
		contextBoard.setTotalNumberOfPosts(getPostsCount(ctxid));
		contextBoard.setBoardTypeObj(boardType.getBoardById());
		return contextBoard;
	}
	
	private boolean isOwner(String ctxid) throws SQLException {
		// TODO Auto-generated method stub
		boolean isOwner = false;
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_CURRENT_CONTEXTBOARD_OWNER);
		pst.setString(1, this.user.getUserId());
		pst.setString(2, ctxid);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_CURRENT_CONTEXTBOARD_OWNER);
		while(rs.next()){
			isOwner = true;
		}
		dataBaseManager.stopDatabaseOperation();
		return isOwner;
	}
	
	private int getFollowersCount(String ctxid) throws SQLException {
		// TODO Auto-generated method stub
		int followersCounts = 0;
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_FOLLOWERS_CONTEXTBOARDS);
		pst.setString(1, ctxid);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_CURRENT_CONTEXTBOARD_OWNER);
		while(rs.next()){
			followersCounts = rs.getInt(1);
		}
		dataBaseManager.stopDatabaseOperation();
		return followersCounts;
	}
	
	private int getPostsCount(String ctxid) throws SQLException {
		// TODO Auto-generated method stub
		int postsCount = 0;
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_POSTS_CONTEXTBOARDS);
		pst.setString(1, ctxid);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_POSTS_CONTEXTBOARDS);
		while(rs.next()){
			postsCount = rs.getInt(1);
		}
		dataBaseManager.stopDatabaseOperation();
		return postsCount;
	}

	public boolean isUserFollowingCurrentContextBoard(String ctxid) throws SQLException {
		// TODO Auto-generated method stub
		boolean isUserFollowing = false;
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_CURRENT_CONTEXTBOARD_FOLLOWING);
		pst.setString(1, this.user.getUserId());
		pst.setString(2, ctxid);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_CURRENT_CONTEXTBOARD_FOLLOWING);
		while(rs.next()){
			isUserFollowing = true;
		}
		dataBaseManager.stopDatabaseOperation();
		return isUserFollowing;
	}

	@Override
	public ContextBoard getDefaultContextBoard(String userId) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_USER_DEFAULT_CONTEXTBOARD);
		pst.setString(1, userId);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_USER_DEFAULT_CONTEXTBOARD);
		ContextBoard contextBoard = null;
		while(rs.next()){
			contextBoard = new ContextBoard(rs.getString(1),
												rs.getString(2),
												rs.getString(3),
												rs.getTimestamp(4),
												new BoardType(rs.getString(5)),
												new BoardType(rs.getString(6)),
												new User(rs.getString(7)),
												rs.getInt(8),
												rs.getString(9)
												);
		}
		dataBaseManager.stopDatabaseOperation();
		return contextBoard;
	}

	public ArrayList<ContextBoard> getContextBoardSearch(String boardName) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_CONTEXTBOARDS_SEARCH);
		ArrayList<ContextBoard> contextBoards = new ArrayList<ContextBoard>();
		pst.setString(1, boardName);
		pst.setString(2, boardName);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_CONTEXTBOARDS_SEARCH);
		while(rs.next()){
			contextBoards.add(new ContextBoard(rs.getString(1),
												rs.getString(2),
												rs.getString(3)
												));
		}
		dataBaseManager.stopDatabaseOperation();
		return contextBoards;
	}

	public ArrayList<ContextBoard> getFollowingUsers(ContextBoard contextBoard) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<ContextBoard> followingUsers = new ArrayList<ContextBoard>();
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_FOLLOWING_USERS);
		pst.setString(1, contextBoard.getBoardId());
		ResultSet followingUserResult = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_FOLLOWING_USERS);
		while(followingUserResult.next()){
			followingUsers.add(new ContextBoard(followingUserResult.getString(1),
					followingUserResult.getString(2),
					followingUserResult.getString(3),
					followingUserResult.getTimestamp(4),
					new BoardType(followingUserResult.getString(5)),
					new BoardType(followingUserResult.getString(6)),
					new User(followingUserResult.getString(7)),
					followingUserResult.getInt(8),
					followingUserResult.getString(9)
					));
		}
		System.out.println("CM: Follwers count: "+followingUsers.size());
		dataBaseManager.stopDatabaseOperation();
		return followingUsers;
	}

	@Override
	public ContextBoard getDefaultContextBoard() throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_USER_DEFAULT_CONTEXTBOARD);
		pst.setString(1, this.user.getUserId());
		System.out.println(this.user.getUserId());
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_USER_DEFAULT_CONTEXTBOARD);
		ContextBoard contextBoard = null;
		while(rs.next()){
			contextBoard = new ContextBoard(rs.getString(1),
												rs.getString(2),
												rs.getString(3),
												rs.getTimestamp(4),
												new BoardType(rs.getString(5)),
												new BoardType(rs.getString(6)),
												new User(rs.getString(7)),
												rs.getInt(8),
												rs.getString(9)
												);
		}
		dataBaseManager.stopDatabaseOperation();
		return contextBoard;
	}

	@Override
	public ArrayList<ContextBoard> getTrendingContextBoards() throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(ContextBoardManager.GET_TRENDING_CONTEXTBOARD);
		ArrayList<ContextBoard> contextBoards = new ArrayList<ContextBoard>();
		pst.setString(1, this.user.getUserId());
//		pst.setString(2, this.SystemUserId+this.user.getLoc());
//		pst.setString(4, this.SystemUserId);
//		pst.setString(3, this.user.getLoc());
		System.out.println(pst);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, ContextBoardManager.GET_TRENDING_CONTEXTBOARD);
		while(rs.next()){
			contextBoards.add(new ContextBoard(rs.getString(1),
												rs.getString(2),
												rs.getString(3),
												rs.getTimestamp(4),
												new BoardType(rs.getString(5)),
												new BoardType(rs.getString(6)),
												new User(rs.getString(7)),
												rs.getInt(8),
												rs.getString(9)
												));
		}
		dataBaseManager.stopDatabaseOperation();
		return contextBoards;
	}
}
