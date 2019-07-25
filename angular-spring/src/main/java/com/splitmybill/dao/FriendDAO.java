package com.splitmybill.dao;

import java.util.List;

import com.splitmybill.model.User;

public interface FriendDAO {

	public List<User> getFriendDetail(String user_email) throws Exception;

	public User getUserByEmailId(String user_email) throws Exception;

	public void addFriend(int senderid, int receiverid, String email, String receiver_user_email);

	public void updateFriendID(int friendid, String friend_email) throws Exception;

	public List<Integer> getUserIDList(String user_email);

	public void updateUserID(int userid, int friendid, String user_email);

	public int getBillToPay(String user_email, String friend_email) throws Exception;

	public boolean updateBillToPay(String user_email, String friend_email, int billtopay) throws Exception;

}
