package com.marrker.data.beans;

import java.sql.SQLException;
import java.sql.Timestamp;

public class ContextBoard {

	private String boardId;
	private String boardName;
	private String boardDescription;
	private Timestamp timestamp;
	private String boardType;
	private String inheritedContextBoard;
	private String userId;
	private int isDefault;
	private BoardType inheritedContextBoardType;
	private boolean isUserFollowing;
	private boolean isOwner;
	private String coverImageLocation;
	private int totalNumberOfFollowers;
	private int totalNumberOfPosts;
	private BoardType boardTypeObj;
	private User user;
	
	
	public ContextBoard(String BoardId, String BoardName, String BoardDescription, Timestamp Timestamp, 
			BoardType boardType, BoardType inheritedContextBoard,
			User userCreatedby, int isDefault, String converImageLocation) {
		// TODO Auto-generated constructor stub
		this.boardId = BoardId;
		this.boardName = BoardName;
		this.boardDescription = BoardDescription;
		this.timestamp = Timestamp;
		this.boardTypeObj = boardType;
		this.setInheritedContextBoardType(inheritedContextBoard);
		this.user = userCreatedby;
		this.isDefault = isDefault;
		this.coverImageLocation = converImageLocation;
	}

	public ContextBoard(String BoardId) throws SQLException {
		// TODO Auto-generated constructor stub
		this.boardId = BoardId;
	}

	public ContextBoard() {
		// TODO Auto-generated constructor stub
	}

	public ContextBoard(String BoardId, String BoardName, String BoardDescription) {
		// TODO Auto-generated constructor stub
		this.boardId = BoardId;
		this.boardName = BoardName;
		this.boardDescription = BoardDescription;
	}
	
	public String getCoverImageLocation() {
		return coverImageLocation;
	}

	public void setCoverImageLocation(String coverImageLocation) {
		this.coverImageLocation = coverImageLocation;
	}
	
	public int getTotalNumberOfFollowers() {
		return totalNumberOfFollowers;
	}

	public void setTotalNumberOfFollowers(int totalNumberOfFollowers) {
		this.totalNumberOfFollowers = totalNumberOfFollowers;
	}

	public int getTotalNumberOfPosts() {
		return totalNumberOfPosts;
	}

	public void setTotalNumberOfPosts(int totalNumberOfPosts) {
		this.totalNumberOfPosts = totalNumberOfPosts;
	}

	public boolean getIsOwner() {
		return isOwner;
	}

	public void setBoardType(String boardType){
		this.boardType = boardType;
	}
	public void setIsOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	public boolean getIsUserFollowing() {
		return isUserFollowing;
	}

	public void setIsUserFollowing(boolean isUserFollowing) {
		this.isUserFollowing = isUserFollowing;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getBoardDescription() {
		return boardDescription;
	}

	public void setBoardDescription(String boardDescription) {
		this.boardDescription = boardDescription;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getBoardType() {
		return boardType;
	}


	public String getInheritedContextBoard() {
		return inheritedContextBoard;
	}

	public void setInheritedContextBoard(String inheritedContextBoard) {
		this.inheritedContextBoard = inheritedContextBoard;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int isDefault() {
		return isDefault;
	}
	
	public void setDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public BoardType getInheritedContextBoardType() {
		return inheritedContextBoardType;
	}

	public void setInheritedContextBoardType(BoardType inheritedContextBoardType) {
		this.inheritedContextBoardType = inheritedContextBoardType;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BoardType getBoardTypeObj() {
		return boardTypeObj;
	}

	public void setBoardTypeObj(BoardType boardTypeObj) {
		this.boardTypeObj = boardTypeObj;
	}


	
}
