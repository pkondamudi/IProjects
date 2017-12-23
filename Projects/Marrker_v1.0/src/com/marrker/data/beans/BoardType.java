package com.marrker.data.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.marrker.db.manager.DataBaseManager;

public class BoardType {

	private String boardTypeId;
	private String contextBoardType;
	private Timestamp timestamp;
	
	public static final String GET_BOARDTYPE = "SELECT * FROM `pavankon_marrker`.`contextboardtypes` "
			+ "WHERE `boardTypeId` = ?";
	
	public static final String GET_BOARDTYPE_BY_NAME = "SELECT * FROM `pavankon_marrker`.`contextboardtypes` "
			+ "WHERE `boardType` = ?";
	
	public static final String GET_BOARDTYPE_BY_ID = "SELECT * FROM `pavankon_marrker`.`contextboardtypes` "
			+ "WHERE `boardTypeId` = ?";
	
	public static final String GET_BOARDTYPES = "SELECT * FROM `pavankon_marrker`.`contextboardtypes` ";
	
	public BoardType(String boardTypeId, String contextBoardType, Timestamp timestamp) {
		// TODO Auto-generated constructor stub
		this.boardTypeId = boardTypeId;
		this.contextBoardType = contextBoardType;
		this.timestamp = timestamp;
	}
	public BoardType(String boardTypeId) {
		// TODO Auto-generated constructor stub
		this.boardTypeId = boardTypeId;
	}
	public BoardType(BoardType boardType) {
		// TODO Auto-generated constructor stub
		this.contextBoardType = boardType.getContextBoardType();
	}
	
	public BoardType() {
		// TODO Auto-generated constructor stub
	}
	public String getBoardTypeId() {
		return boardTypeId;
	}
	public void setBoardTypeId(String boardTypeId) {
		this.boardTypeId = boardTypeId;
	}
	public String getContextBoardType() {
		return contextBoardType;
	}
	public void setContextBoardType(String contextBoardType) {
		this.contextBoardType = contextBoardType;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public BoardType getBoardDetails() throws SQLException{
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(BoardType.GET_BOARDTYPE);
		pst.setString(1, this.boardTypeId);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, BoardType.GET_BOARDTYPE);
		while(rs.next()){
			this.contextBoardType = rs.getString(2);
			this.timestamp = rs.getTimestamp(3);
		}
		dataBaseManager.stopDatabaseOperation();
		return this;
	}
	
	public BoardType getBoardByName() throws SQLException{
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(BoardType.GET_BOARDTYPE_BY_NAME);
		pst.setString(1, this.contextBoardType);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, BoardType.GET_BOARDTYPE_BY_NAME);
		while(rs.next()){
			this.boardTypeId = rs.getString(1);
			this.timestamp = rs.getTimestamp(3);
		}
		dataBaseManager.stopDatabaseOperation();
		return this;
	}
	
	public BoardType getBoardById() throws SQLException{
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(BoardType.GET_BOARDTYPE_BY_ID);
		pst.setString(1, this.boardTypeId);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, BoardType.GET_BOARDTYPE_BY_ID);
		while(rs.next()){
			this.contextBoardType = rs.getString(2);
			this.timestamp = rs.getTimestamp(3);
		}
		System.out.println("getBoardById: "+this.contextBoardType);
		dataBaseManager.stopDatabaseOperation();
		return this;
	}
	
	public ArrayList<BoardType> getContextBoardTypes() throws SQLException {
		// TODO Auto-generated method stub

		ArrayList<BoardType> boardTypes = new ArrayList<BoardType>();
		DataBaseManager dataBaseManager = new DataBaseManager();
		PreparedStatement pst = dataBaseManager.startDatabaseOperation(BoardType.GET_BOARDTYPES);
		ResultSet rs = dataBaseManager.getDatabaseOprationResult(pst, BoardType.GET_BOARDTYPES);
		while(rs.next()){
			boardTypes.add(new BoardType(rs.getString(1), rs.getString(2), rs.getTimestamp(3)));
		}
		
		return boardTypes;
	}
	
}
