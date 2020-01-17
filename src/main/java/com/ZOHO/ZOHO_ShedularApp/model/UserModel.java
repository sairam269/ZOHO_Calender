package com.ZOHO.ZOHO_ShedularApp.model;


public class UserModel {
	int UserID;
	String UserName;
	public UserModel() {
		UserID = 0;
		UserName = "";
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	@Override
	public String toString() {
		return "User [UserID=" + UserID + ", UserName=" + UserName +  "]";
	}
	
}
