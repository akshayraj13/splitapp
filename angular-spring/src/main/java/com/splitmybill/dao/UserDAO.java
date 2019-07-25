package com.splitmybill.dao;

public interface UserDAO {

	public void addUser(String user_fname, String user_lname, String user_email) throws Exception;

	public Boolean checkUserExists(String user_email);
}
