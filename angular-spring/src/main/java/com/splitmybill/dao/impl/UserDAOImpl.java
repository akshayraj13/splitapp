package com.splitmybill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.splitmybill.dao.UserDAO;
import com.splitmybill.db.util.DatabaseHelper;
import com.splitmybill.model.User;

@Component
public class UserDAOImpl implements UserDAO {

	@Autowired
	DatabaseHelper helper;

	private static final String INSERT_USER_TO_USERTABL = "insert into usertbl(user_email,user_fname,user_lname) values(?,?,?)";
	private static final String SELECT_USER_BY_EMAIL = "select user_email from usertbl where user_email=?";

	Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

	@Override
	public void addUser(String user_fname, String user_lname, String user_email) throws Exception {

		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = helper.getConnection();
			pstmt = con.prepareStatement(INSERT_USER_TO_USERTABL);
			pstmt.setString(1, user_email);
			pstmt.setString(2, user_fname);
			pstmt.setString(3, user_lname);
			int result = pstmt.executeUpdate();

			if (result > 0) {
				log.info("User added successfully");
			}
		} catch (SQLException e) {
			log.error("SQLException: addUser " + e.getMessage());
			throw new SQLException("Something went wrong.");

		} catch (Exception e) {
			log.error("Exception: addUser " + e.getMessage());
			throw new Exception("Something went wrong.");

		} finally {
			helper.closeResource(pstmt, con);
		}

	}

	@Override
	public Boolean checkUserExists(String user_email) {
		log.info("checkUserExists(): checking user exists in database");
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = helper.getConnection();
			pstmt = con.prepareStatement(SELECT_USER_BY_EMAIL);
			pstmt.setString(1, user_email);

			rs = pstmt.executeQuery();
			user = new User();
			if (rs.next()) {
				log.info("checkUserExists(): User Exists");
				return true;
				
			}
		} catch (Exception e) {
			user.setUserid(0);
			e.printStackTrace();
		} finally {
			helper.closeResource(pstmt, con);
		}
		log.info("checkUserExists(): User does not Exist");
		return false;

	}

}
