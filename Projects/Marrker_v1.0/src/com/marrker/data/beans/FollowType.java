package com.marrker.data.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.marrker.db.manager.DataBaseManager;

public class FollowType {

	private String followTypeId;
	private String contextBoardId;
	private String followTypeName;
	private Timestamp timestamp;
	
	
	public static final String GET_FOLLOWTYPE = "SELECT * FROM `pavankon_marrker`.`followtypes` "
			+ "WHERE `followTypeId` = ?";
	
	public static final String GET_FOLLOWTYPE_BY_NAME = "SELECT * FROM `pavankon_marrker`.`followtypes` "
			+ "WHERE `followType` = ?";
	
	public static final String GET_FOLLOWTYPES = "SELECT * FROM `pavankon_marrker`.`followtypes` ";
	
	public FollowType(String followTypeId, String followType, Timestamp timestamp) {
		// TODO Auto-generated constructor stub
		this.followTypeId = followTypeId;
		this.followTypeName = followType;
		this.timestamp = timestamp;
	}

	public FollowType() {
		// TODO Auto-generated constructor stub
	}

	public FollowType(String followType) {
		// TODO Auto-generated constructor stub
		this.followTypeName = followType;
	}

	public String getContextBoardId() {
		return contextBoardId;
	}

	public void setContextBoardId(String contextBoardId) {
		this.contextBoardId = contextBoardId;
	}

	public String getFollowTypeId() {
		return followTypeId;
	}

	public void setFollowTypeId(String followTypeId) {
		this.followTypeId = followTypeId;
	}



	public String getFollowTypeName() {
		return followTypeName;
	}

	public void setFollowTypeName(String followTypeName) {
		this.followTypeName = followTypeName;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public ArrayList<FollowType> getUserFollowTypes() throws SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<FollowType> followTypes = new ArrayList<FollowType>();
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(FollowType.GET_FOLLOWTYPES);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, FollowType.GET_FOLLOWTYPES);
		while(rs.next()){
			followTypes.add(new FollowType(rs.getString(1), rs.getString(2), rs.getTimestamp(3)));
		}
		
		return followTypes;
	}
	
	public FollowType getFollowTypeDetails() throws SQLException{
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(FollowType.GET_FOLLOWTYPE);
		pst.setString(1, this.followTypeId);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, FollowType.GET_FOLLOWTYPE);
		while(rs.next()){
			this.followTypeName = rs.getString(2);
			this.timestamp = rs.getTimestamp(3);
		}
		dataBaseManager.stopDatabaseOperation();
		return this;
	}
	
	public FollowType getFollowTypeByName() throws SQLException{
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(FollowType.GET_FOLLOWTYPE_BY_NAME);
		pst.setString(1, this.followTypeName);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, FollowType.GET_FOLLOWTYPE_BY_NAME);
		while(rs.next()){
			this.followTypeId = rs.getString(1);
			this.timestamp = rs.getTimestamp(3);
		}
		dataBaseManager.stopDatabaseOperation();
		return this;
	}
	
}
