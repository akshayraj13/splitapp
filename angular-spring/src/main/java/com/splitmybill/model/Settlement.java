package com.splitmybill.model;

public class Settlement {

	String settlementmsg;
	int billtopay;
	int status;
	String friend_email;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFriend_email() {
		return friend_email;
	}

	public void setFriend_email(String friend_email) {
		this.friend_email = friend_email;
	}

	public String getSettlementmsg() {
		return settlementmsg;
	}

	public void setSettlementmsg(String settlementmsg) {
		this.settlementmsg = settlementmsg;
	}

	public int getBilltopay() {
		return billtopay;
	}

	public void setBilltopay(int billtopay) {
		this.billtopay = billtopay;
	}

}
