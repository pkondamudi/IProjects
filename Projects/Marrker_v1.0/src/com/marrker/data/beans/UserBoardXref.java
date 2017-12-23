package com.marrker.data.beans;

import java.sql.Timestamp;

public class UserBoardXref {

	private String userBoardXrefId;
	private User user;
	private ContextBoard board;
	private FollowType followType;
	private Timestamp timestamp;
	private Timestamp lastVistit;
	
	public String getUserBoardXrefId() {
		return userBoardXrefId;
	}
	
	public void setUserBoardXrefId(String userBoardXrefId) {
		this.userBoardXrefId = userBoardXrefId;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public ContextBoard getBoard() {
		return board;
	}
	
	public void setBoard(ContextBoard board) {
		this.board = board;
	}
	
	public FollowType getFollowType() {
		return followType;
	}
	
	public void setFollowType(FollowType followType) {
		this.followType = followType;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public Timestamp getLastVistit() {
		return lastVistit;
	}
	
	public void setLastVistit(Timestamp lastVistit) {
		this.lastVistit = lastVistit;
	}
	
	
}
