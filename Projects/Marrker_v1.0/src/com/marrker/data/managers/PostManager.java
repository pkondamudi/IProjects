package com.marrker.data.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.marrker.data.beans.ContextBoard;
import com.marrker.data.beans.Post;
import com.marrker.data.beans.PostType;
import com.marrker.data.beans.User;
import com.marrker.data.interfaces.PostInterface;
import com.marrker.db.manager.DataBaseManager;

public class PostManager implements PostInterface {

	private ContextBoard contextBoard;
	private User user;
	
	public static final String INSERT_POST = "INSERT INTO `pavankon_marrker`.`posts`(`postid`,`posttypeid`,`post`,`vlocation`, `ilocation` ,`contextboardid`,`userid`) "
			+ " VALUES(UUID(),?,?,?,?,?,?)";
	public static final String UPDATE_POST = "UPDATE `pavankon_marrker`.`posts` "
			+ "SET `posttypeid` = ?,`post` = ?,"
			+ "`v_i_location` = ?,`contextboardid` = ?,"
			+ "`userid` = ?"
			+ "WHERE `postid` = ?";
	
	public static final String DO_REPOST = "INSERT INTO `pavankon_marrker`.`posts`(`postid`,`posttypeid`,`post`,`vlocation`, `ilocation` ,`contextboardid`,`userid`) "
			+ " VALUES(UUID(),?,?,?,?,?,?)";
	
	public static final String DELETE_POST = "DELETE FROM `pavankon_marrker`.`posts` WHERE `postid` = ?";
	
	public static final String GET_POST_TYPES = "SELECT * FROM `pavankon_marrker`.`posttype`";
	
	public static final String GET_BOARD_POST= "SELECT * FROM `pavankon_marrker`.`posts` where contextboardid = ? ORDER BY TIMESTAMP DESC";
	
	public static final String GET_CURRENT_POST= "SELECT * FROM `pavankon_marrker`.`posts` where postid = ? ORDER BY TIMESTAMP DESC";
	
	public static final String GET_CURRENT_POST_OWNER = "select  PostId from `pavankon_marrker`.`posts` where userid = ? "
			+ "and PostId = ?";
	
	public static final String GET_USER_RECENTLY_UPDATED_CONTEXTBOARD_POSTS = "SELECT DISTINCT P.* FROM `pavankon_marrker`.`contextboards` CB "
			+ "INNER JOIN `pavankon_marrker`.`posts` P ON P.ContextBoardId = CB.ContextBoardId "
			+ "Where CB.ContextBoardId IN(SELECT contextboardid FROM `pavankon_marrker`.`contextboards` "
			+ "WHERE `contextboardid` IN (select  contextboardid from user_board_xref where userid = ?)) "
			+ "ORDER BY P.Timestamp DESC";

	public PostManager(ContextBoard contextBoard){
		this.setContextBoard(contextBoard);
	}

	public PostManager() {
		// TODO Auto-generated constructor stub
	}
	
	public PostManager(User user){
		this.user = user;
	}

	public PostManager(ContextBoard contextBoard, User user) {
		// TODO Auto-generated constructor stub
		this.user = user;
		this.setContextBoard(contextBoard);
	}

	@Override
	public boolean createNewPost(Post post) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(PostManager.INSERT_POST);
		PostType postType = new PostType(post.getPostType());
		System.out.println(post.getPostType());
		System.out.println(postType.getPostTypeByName().getPostTypeId());
		pst.setString(1, postType.getPostTypeByName().getPostTypeId());
		pst.setString(2, post.getPost());
		pst.setString(3, post.getvLocation());
		pst.setString(4, post.getiLocation());
		pst.setString(5, post.getContextBoardId());
		pst.setString(6, this.user.getUserId());
		dataBaseManager.getDatabaseOprationResult(pst);
		dataBaseManager.stopDatabaseOperation();
		return true;
	}
	
	@Override
	public boolean createNewPost(Post post, boolean isOpen) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(PostManager.INSERT_POST);
		PostType postType = new PostType(post.getPostType());
		System.out.println(post.getPostType());
		System.out.println(postType.getPostTypeByName().getPostTypeId());
		pst.setString(1, postType.getPostTypeByName().getPostTypeId());
		pst.setString(2, post.getPost());
		pst.setString(3, post.getvLocation());
		pst.setString(4, post.getiLocation());
		pst.setString(5, post.getContextBoardId());
		pst.setString(6, this.user.getUserId());
		dataBaseManager.getDatabaseOprationResult(pst);
		dataBaseManager.stopDatabaseOperation();
		doRepost(post);
		return true;
	}

	@Override
	public boolean updatePost(Post post) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(PostManager.UPDATE_POST);
		PostType postType = new PostType(post.getPostType());
		pst.setString(1, postType.getPostTypeByName().getPostTypeId());
		pst.setString(2, post.getPost());
		pst.setString(3, post.getvLocation());
		pst.setString(4, post.getiLocation());
		pst.setString(5, post.getContextBoardId());
		pst.setString(6, this.user.getUserId());
		pst.setString(7, post.getPostId());
		dataBaseManager.getDatabaseOprationResult(pst);
		dataBaseManager.stopDatabaseOperation();
		return true;
	}

	@Override
	public boolean deletePost(Post post) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(PostManager.DELETE_POST);
		pst.setString(1, post.getPostId());
		dataBaseManager.getDatabaseOprationResult(pst);
		dataBaseManager.stopDatabaseOperation();
		return true;
	}

	@Override
	public ArrayList<Post> getContextBoardPosts() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Post> posts = new ArrayList<Post>();
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(PostManager.GET_BOARD_POST);
		pst.setString(1, this.contextBoard.getBoardId());
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, PostManager.GET_BOARD_POST);
		User user = null;
		ContextBoardManager contextManager;
		while(rs.next()){
			user = new User(rs.getString(5));
			contextManager = new ContextBoardManager(user);
			user.setDefaultContextId(contextManager.getDefaultContextBoard().getBoardId());
			posts.add(new Post(rs.getString(1), rs.getString(2), rs.getString(3), 
					new ContextBoard(rs.getString(4)), user, rs.getTimestamp(6), rs.getString(7)
					, rs.getString(8), getCurrentPost(rs.getString(1)).getIsOwner()));
		}
		return posts;
	}
	
	@Override
	public ArrayList<Post> getUserContextBoardPosts() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Post> posts = new ArrayList<Post>();
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(PostManager.GET_USER_RECENTLY_UPDATED_CONTEXTBOARD_POSTS);
		pst.setString(1, this.user.getUserId());
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, PostManager.GET_USER_RECENTLY_UPDATED_CONTEXTBOARD_POSTS);
		User user = null;
		ContextBoard contextBoard = null;
		ContextBoardManager contextManager = new ContextBoardManager(this.user);
		UserManager userManager = new UserManager();
		while(rs.next()){
			user = new User(rs.getString(5));
			contextBoard = new ContextBoard(rs.getString(4));
			contextBoard = contextManager.getCurrentContextBoard(contextBoard.getBoardId());
			//contextManager.setUser(user);
			user = userManager.getCurrentUser(user.getUserId());
			user.setDefaultContextId(contextManager.getDefaultContextBoard(user.getUserId()).getBoardId());
			posts.add(new Post(rs.getString(1), rs.getString(2), rs.getString(3), 
					contextBoard, user, rs.getTimestamp(6), rs.getString(7)
					, rs.getString(8), getCurrentPost(rs.getString(1)).getIsOwner()));
		}
		return posts;
	}

	@Override
	public ArrayList<PostType> getPostTypes() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<PostType> postTypes = new ArrayList<PostType>();
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(PostManager.GET_POST_TYPES);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, PostManager.GET_POST_TYPES);
		while(rs.next()){
			postTypes.add(new PostType(rs.getString(1), rs.getString(2), rs.getTimestamp(3)));
		}
		return postTypes;
	}
	
	@Override
	public Post getCurrentPost(String postId) throws SQLException {
		// TODO Auto-generated method stub
		Post post = new Post();
		PostType postType = new PostType();
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(PostManager.GET_CURRENT_POST);
		pst.setString(1, postId);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, PostManager.GET_CURRENT_POST);
		while(rs.next()){
			postType.setPostTypeId(rs.getString(2));
			post = new Post(rs.getString(1), postType.getPostTypeDetails().getPostType(), rs.getString(3), 
					new ContextBoard(rs.getString(4)), new User(rs.getString(5)), rs.getTimestamp(6), rs.getString(7)
					, rs.getString(8));
		}
		post.setIsOwner(isOwner(postId));
		return post;
	}

	public ContextBoard getContextBoard() {
		return contextBoard;
	}

	public void setContextBoard(ContextBoard contextBoard) {
		this.contextBoard = contextBoard;
	}

	@Override
	public boolean doRepost(Post post) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(PostManager.DO_REPOST);
		UserManager userManager = new UserManager(this.user);
		PostType postType = new PostType(post.getPostType());
		System.out.println(post.getPostType());
		System.out.println(postType.getPostTypeByName().getPostTypeId());
		pst.setString(1, postType.getPostTypeByName().getPostTypeId());
		pst.setString(2, post.getPost());
		pst.setString(3, post.getvLocation());
		pst.setString(4, post.getiLocation());
		pst.setString(5, userManager.getDefaultContextBoard().getBoardId());
		pst.setString(6, this.user.getUserId());
		dataBaseManager.getDatabaseOprationResult(pst);
		dataBaseManager.stopDatabaseOperation();
		return true;
	}
	
	private boolean isOwner(String pstid) throws SQLException {
		// TODO Auto-generated method stub
		boolean isOwner = false;
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(PostManager.GET_CURRENT_POST_OWNER);
		pst.setString(1, this.user.getUserId());
		pst.setString(2, pstid);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, PostManager.GET_CURRENT_POST_OWNER);
		while(rs.next()){
			isOwner = true;
		}
		dataBaseManager.stopDatabaseOperation();
		return isOwner;
	}

	
}
