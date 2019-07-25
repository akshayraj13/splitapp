package com.splitmybill.model;

public class Friend {

	private int userid;
	private int friendid;
	private int billtopay;
	private String user_email;

	public Friend() {

	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getUserid() {
		return userid;
	}

	public int getFriendid() {
		return friendid;
	}

	public int getBilltopay() {
		return billtopay;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}

	public void setBilltopay(int billtopay) {
		this.billtopay = billtopay;
	}

}
