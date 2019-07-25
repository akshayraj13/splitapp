package com.splitmybill.model;

public class PaymentDetail {

	String user_email;
	long cardnumber;
	int cvv;
	String friend_email;
	int billtopay;

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public long getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(long cardnumber) {
		this.cardnumber = cardnumber;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
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

}
