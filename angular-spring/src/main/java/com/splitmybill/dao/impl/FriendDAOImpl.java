package com.splitmybill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.splitmybill.dao.FriendDAO;
import com.splitmybill.db.util.DatabaseHelper;
import com.splitmybill.model.User;

@Component
public class FriendDAOImpl implements FriendDAO {

	private static final String SELECT_FRIEND_DETAILS = "select user_fname,user_lname,user_email from usertbl where userid in (Select friendid from friendtbl where userid=(select userid from usertbl where user_email=?));";
	private static final String SELECT_USER_BY_EMAIL = "select userid,user_fname,user_lname,user_email from usertbl where user_email=?";
	private static final String INSERT_FRIEND_TO_FRIENDTBL = "insert into friendtbl(userid,friendid,billtopay,user_email,friend_email)values(?,?,?,?,?)";
	private static final String UPDATE_FRIENDID = "UPDATE friendtbl SET friendid = ? WHERE friend_email= ?";
	private static final String GET_FRIENDLIST_BY_EMAIL = "select userid from friendtbl where friend_email=?";
	private static final String UPDATE_USERID_OF_FRIENDTBL = "UPDATE friendtbl SET userid = ? WHERE userid= 0 and friendid= ? and user_email=?";
	private static final String SELECT_BILLTOPAY = "select billtopay from friendtbl where user_email=? and friend_email=?";
	private static final String UPDATE_BILLTOPAY = "UPDATE friendtbl SET billtopay = ? WHERE user_email= ? and friend_email= ?";

	Logger log = LoggerFactory.getLogger(FriendDAOImpl.class);

	@Autowired
	DatabaseHelper helper;

	@Override
	public List<User> getFriendDetail(String userEmail) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		User user = null;
		List<User> tempUser = null;
		log.info("Inside get friend details by userid method");

		try {
			con = helper.getConnection();
			pstmt = con.prepareStatement(SELECT_FRIEND_DETAILS);
			pstmt.setString(1, userEmail);

			rs = pstmt.executeQuery();

			tempUser = new ArrayList<User>();

			while (rs != null && rs.next()) {
				user = new User();
				user.setUser_fname(rs.getString("user_fname"));
				user.setUser_lname(rs.getString("user_lname"));
				user.setUser_email(rs.getString("user_email"));

				tempUser.add(user);

			}

			return tempUser;
		}

		catch (Exception ex) {
			log.error("Exeception: getfrienddetail(): " + ex.getMessage());
			throw new Exception("Something went wrong");

		} finally {

			helper.closeResource(pstmt, con);

		}
	}

	@Override
	public User getUserByEmailId(String userEmail) throws Exception {

		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = helper.getConnection();
			pstmt = con.prepareStatement(SELECT_USER_BY_EMAIL);
			pstmt.setString(1, userEmail);

			rs = pstmt.executeQuery();
			user = new User();
			if (rs.next()) {

				user.setUser_fname(rs.getString("user_fname"));
				user.setUser_lname(rs.getString("user_lname"));
				user.setUser_email(rs.getString("user_email"));
				user.setUserid(rs.getInt("userid"));

			} else {
				user.setUserid(0);

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception: getUserByEmailId() " + e.getMessage());
			throw new Exception("Something went wrong.");
		} finally {
			helper.closeResource(pstmt, con);
		}
		return user;

	}

	@Override
	public void addFriend(int userId, int friendId, String userEmail, String friendEmail) {

		PreparedStatement pstmt = null;
		Connection con = null;

		try {

			con = helper.getConnection();
			pstmt = con.prepareStatement(INSERT_FRIEND_TO_FRIENDTBL);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, friendId);
			pstmt.setInt(3, 0);  //billtopay
			pstmt.setString(4, userEmail);
			pstmt.setString(5, friendEmail);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				log.info("Friend added successfully");
			} else {
				log.info("Friend is not added");
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			helper.closeResource(pstmt, con);
		}

	}

	@Override
	public void updateFriendID(int friendId, String friendEmail) throws Exception {

		PreparedStatement pstmt = null;
		Connection con = null;

		try {

			con = helper.getConnection();
			pstmt = con.prepareStatement(UPDATE_FRIENDID);
			pstmt.setInt(1, friendId);
			pstmt.setString(2, friendEmail);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				log.info("friendid updated successfully in friendtbl");
			}
		} catch (Exception e) {
			log.error("Exception: updateFriendId " + e.getMessage());
			throw new Exception("Something went wrong.");

		} finally {
			helper.closeResource(pstmt, con);
		}

	}

	@Override
	public List<Integer> getUserIDList(String userEmail) {

		List<Integer> tempList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = helper.getConnection();
			pstmt = con.prepareStatement(GET_FRIENDLIST_BY_EMAIL);
			pstmt.setString(1, userEmail);

			rs = pstmt.executeQuery();

			tempList = new ArrayList<Integer>();
			while (rs != null && rs.next()) {

				tempList.add(rs.getInt("userid"));

			}

		}

		catch (Exception ex) {
			log.error("Hello : " + ex.getMessage());

		} finally {

			helper.closeResource(pstmt, con);

		}
		return tempList;
	}

	@Override
	public void updateUserID(int userId, int friendId, String userEmail) {

		PreparedStatement pstmt = null;
		Connection con = null;

		try {

			con = helper.getConnection();
			pstmt = con.prepareStatement(UPDATE_USERID_OF_FRIENDTBL);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, friendId);
			pstmt.setString(3, userEmail);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				log.info("userid updated successfully in friendtbl");
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			helper.closeResource(pstmt, con);
		}

	}

	@Override
	public int getBillToPay(String userEmail, String friendEmail) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = helper.getConnection();
			pstmt = con.prepareStatement(SELECT_BILLTOPAY);
			pstmt.setString(1, userEmail);
			pstmt.setString(2, friendEmail);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				return rs.getInt("billtopay");

			}

		}

		catch (Exception ex) {
			log.error("Exception: getBillTOPay() " + ex.getMessage());
			throw new Exception("Something went wrong.");

		} finally {

			helper.closeResource(pstmt, con);

		}
		return -1;
	}

	@Override
	public boolean updateBillToPay(String userEmail, String friendEmail, int billToPay) throws Exception {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {

			con = helper.getConnection();
			pstmt = con.prepareStatement(UPDATE_BILLTOPAY);
			pstmt.setInt(1, billToPay);
			pstmt.setString(2, userEmail);
			pstmt.setString(3, friendEmail);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				log.info("Bill to pay updated successfully");
				return true;
			}
		} catch (Exception e) {
			log.error("Exception: updateBillToPay() " + e.getMessage());
			throw new Exception("Something went wrong.");

		} finally {
			helper.closeResource(pstmt, con);
		}
		return false;

	}

}
