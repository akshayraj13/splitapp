package com.splitmybill.model;

import java.sql.Date;

public class Transaction {

	String user_email;
	String friend_email;
	int billtopay;
	Date dateoftransaction;

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getFriend_email() {
		return friend_email;
	}

	public void setFriend_email(String friend_email) {
		this.friend_email = friend_email;
	}

	public int getBilltopay() {
		return billtopay;
	}

	public void setBilltopay(int billtopay) {
		this.billtopay = billtopay;
	}

	public Date getDateoftransaction() {
		return dateoftransaction;
	}

	public void setDateoftransaction(Date dateoftransaction) {
		this.dateoftransaction = dateoftransaction;
	}

}
