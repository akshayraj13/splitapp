package com.splitmybill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.splitmybill.dao.TransactionDAO;
import com.splitmybill.db.util.DatabaseHelper;
import com.splitmybill.model.Transaction;

@Component
public class TransactionDAOImpl implements TransactionDAO {

	@Autowired
	DatabaseHelper helper;

	private static final String INSERT_TRANSACTION = "insert into transactiontbl(user_email,friend_email,billtopay,dateoftransaction) values(?,?,?,?)";
	private static final String SELECT_CREDIT_TRANSACTION = "select dateoftransaction, usertbl.user_fname,billtopay from usertbl,transactiontbl where usertbl.user_email=transactiontbl.user_email and friend_email=?";
	private static final String SELECT_DEBIT_TRANSACTION = "select dateoftransaction, usertbl.user_fname,billtopay from usertbl,transactiontbl where usertbl.user_email=transactiontbl.friend_email and transactiontbl.user_email=?";

	Logger log = LoggerFactory.getLogger(TransactionDAOImpl.class);

	@Override
	public void addTransaction(String user_email, String friend_email, int billtopay) throws Exception {

		PreparedStatement pstmt = null;
		Connection con = null;
		Calendar calendar = Calendar.getInstance();
		java.sql.Date dateoftransaction = new java.sql.Date(calendar.getTime().getTime());

		try {

			con = helper.getConnection();
			pstmt = con.prepareStatement(INSERT_TRANSACTION);
			pstmt.setString(1, user_email);
			pstmt.setString(2, friend_email);
			pstmt.setInt(3, billtopay);
			pstmt.setDate(4, dateoftransaction);
			int result = pstmt.executeUpdate();

			if (result > 0) {
				log.info("Transaction added successfully");
			}
		} catch (SQLException ex) {
			log.error("SQLException: addTransaction(): " + ex.getMessage());
			throw ex;
		} catch (Exception ex) {
			log.error("Exception: addTransaction(): " + ex.getMessage());
			throw ex;
		} finally {
			helper.closeResource(pstmt, con);
		}

	}

	@Override
	public List<Transaction> getCreditTransaction(String user_email) throws Exception {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		Transaction transaction = null;
		List<Transaction> tempTransaction = null;
		log.info("Inside get transaction by email method");

		try {

			con = helper.getConnection();
			pstmt = con.prepareStatement(SELECT_CREDIT_TRANSACTION);
			pstmt.setString(1, user_email);

			rs = pstmt.executeQuery();

			tempTransaction = new ArrayList<Transaction>();

			while (rs != null && rs.next()) {
				transaction = new Transaction();

				transaction.setDateoftransaction(rs.getDate("dateoftransaction"));
				transaction.setUser_email(rs.getString("usertbl.user_fname"));
				transaction.setBilltopay(rs.getInt("billtopay"));

				tempTransaction.add(transaction);
			}
			return tempTransaction;
		}

		catch (SQLException ex) {
			log.error("SQLException: getCreditTransaction(): " + ex.getMessage());
			throw ex;
		} catch (Exception ex) {
			log.error("Exception: getCreditTransaction(): " + ex.getMessage());
			throw ex;
		} finally {

			helper.closeResource(pstmt, con);
		}
	}

	@Override
	public List<Transaction> getDebitTransaction(String user_email) throws Exception {

		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		Transaction transaction = null;
		List<Transaction> tempTransaction = null;
		log.info("Inside get transaction by email method");

		try {

			con = helper.getConnection();
			pstmt = con.prepareStatement(SELECT_DEBIT_TRANSACTION);
			pstmt.setString(1, user_email);

			rs = pstmt.executeQuery();

			tempTransaction = new ArrayList<Transaction>();

			while (rs != null && rs.next()) {
				transaction = new Transaction();

				transaction.setDateoftransaction(rs.getDate("dateoftransaction"));
				transaction.setUser_email(rs.getString("usertbl.user_fname"));
				transaction.setBilltopay(rs.getInt("billtopay"));

				tempTransaction.add(transaction);

			}

			return tempTransaction;

		}

		catch (SQLException ex) {
			log.error("SQLException: getDebitTransaction(): " + ex.getMessage());
			throw ex;
		} catch (Exception ex) {
			log.error("Exception: getDebitTransaction(): " + ex.getMessage());
			throw ex;
		} finally {

			helper.closeResource(pstmt, con);

		}
	}

}
