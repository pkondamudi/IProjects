package com.marrker.data.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.marrker.db.manager.DataBaseManager;

public class PostType {

	private String postTypeId;
	private String postType;
	private Timestamp timestamp;
	
	public static final String GET_POSTTYPE = "SELECT * FROM `pavankon_marrker`.`posttype` "
			+ "WHERE `postTypeId` = ?";
	
	public static final String GET_POSTTYPE_BY_NAME = "SELECT * FROM `pavankon_marrker`.`posttype` "
			+ "WHERE `postType` = ?";
	
	public static final String GET_POSTTYPES = "SELECT * FROM `pavankon_marrker`.`posttype` ";
	
	public PostType(String postTypeId, String postType, Timestamp timestamp) {
		// TODO Auto-generated constructor stub
		this.postTypeId = postTypeId;
		this.postType = postType;
		this.timestamp = timestamp;
	}

	public PostType(String postType) {
		// TODO Auto-generated constructor stub
		this.postType = postType;
	}

	public PostType() {
		// TODO Auto-generated constructor stub
	}

	public String getPostTypeId() {
		return postTypeId;
	}
	
	public void setPostTypeId(String postTypeId) {
		this.postTypeId = postTypeId;
	}
	
	public String getPostType() {
		return postType;
	}
	
	public void setPostType(String postType) {
		this.postType = postType;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public PostType getPostTypeDetails() throws SQLException{
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(PostType.GET_POSTTYPE);
		pst.setString(1, this.postTypeId);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, PostType.GET_POSTTYPE);
		while(rs.next()){
			this.postType = rs.getString(2);
			this.timestamp = rs.getTimestamp(3);
		}
		dataBaseManager.stopDatabaseOperation();
		return this;
	}
	
	public PostType getPostTypeByName() throws SQLException{
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(PostType.GET_POSTTYPE_BY_NAME);
		pst.setString(1, this.postType);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, PostType.GET_POSTTYPE_BY_NAME);
		while(rs.next()){
			this.postTypeId = rs.getString(1);
			this.timestamp = rs.getTimestamp(3);
		}
		dataBaseManager.stopDatabaseOperation();
		return this;
	}
	
	public ArrayList<PostType> getContextBoardTypes() throws SQLException {
		// TODO Auto-generated method stub

		ArrayList<PostType> postTypes = new ArrayList<PostType>();
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(PostType.GET_POSTTYPES);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, PostType.GET_POSTTYPES);
		while(rs.next()){
			postTypes.add(new PostType(rs.getString(1), rs.getString(2), rs.getTimestamp(3)));
		}
		
		return postTypes;
	}
	
}
