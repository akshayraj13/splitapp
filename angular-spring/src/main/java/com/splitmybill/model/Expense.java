package com.splitmybill.model;

import java.util.List;

public class Expense {

	String user_email;
	List<String> friend_email = null;
	int expense;

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public List<String> getFriend_email() {
		return friend_email;
	}

	public void setFriend_email(List<String> friend_email) {
		this.friend_email = friend_email;
	}

	public int getExpense() {
		return expense;
	}

	public void setExpense(int expense) {
		this.expense = expense;
	}

}
