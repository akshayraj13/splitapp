package com.splitmybill.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.splitmybill.dao.CardDAO;
import com.splitmybill.db.util.DatabaseHelper;
import com.splitmybill.model.Card;

@Component
public class CardDAOImpl implements CardDAO {

	private static final String INSERT_CARD_TO_CARDTBL = "insert into cardtbl(email,month,year,cvv,cardnumber,cardholdername,cardname) values(?,?,?,?,?,?,?)";
	private static final String SELECT_CARD_BY_EMAIL = "SELECT email,cardtbl.month,cardtbl.year,cardtbl.cvv,cardtbl.cardnumber,cardtbl.cardid,cardtbl.cardholdername,cardname, carddatabasetbl.accbal FROM cardtbl,carddatabasetbl where cardtbl.cardnumber=carddatabasetbl.cardnumber and email = ?";
	private static final String CHECK_CARD_FROM_VISA_API = "SELECT cardnumber FROM carddatabasetbl where  month=? and year=? and cvv=? and cardnumber=? and cardholdername=?";
	private static final String UPDATE_BALANCE = "UPDATE carddatabasetbl SET accbal = ? WHERE cardnumber=?;";
	private static final String CHECK_CARD_EXISTS_IN_DATABASE = "SELECT cardnumber FROM cardtbl where email=?";
	private static final String GET_BALANCE = "SELECT accbal FROM carddatabasetbl where cardnumber=?";
	private static final String CHECK_CARD_FOR_PAYMENT_VALIDATION = "SELECT cardnumber FROM cardtbl where cardnumber=? and cvv=?";
	private static final String SELECT_FRIEND_BY_EMAIL = "select cardnumber from cardtbl where email=?";

	Logger log = LoggerFactory.getLogger(CardDAOImpl.class);

	@Autowired
	DatabaseHelper helper;

	@Override
	public void addCard(Card card) throws Exception {

		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = helper.getConnection();
			pstmt = con.prepareStatement(INSERT_CARD_TO_CARDTBL);
			pstmt.setString(1, card.getEmail());
			pstmt.setString(2, card.getMonth());
			pstmt.setInt(3, card.getYear());
			pstmt.setInt(4, card.getCvv());
			pstmt.setLong(5, card.getCardnumber());
			pstmt.setString(6, card.getCardholdername());
			pstmt.setString(7, card.getCardname());
			int result = pstmt.executeUpdate();

			if (result > 0) {
				log.info("CardDAOImpl(): Card added successfully");
			}
		} catch (SQLException ex) {
			log.error("SQLException: addCard() " + ex.getMessage());
			throw ex;

		} catch (Exception e) {
			log.error("Exception: addCard() " + e.getMessage());
			throw e;

		} finally {
			helper.closeResource(pstmt, con);
		}

	}

	@Override
	public List<Card> getCardByEmail(String email) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		Card card = null;
		List<Card> tempCard = null;
		log.info("Inside get card by email method");

		try {
			con = helper.getConnection();
			pstmt = con.prepareStatement(SELECT_CARD_BY_EMAIL);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			tempCard = new ArrayList<Card>();

			while (rs != null && rs.next()) {
				card = new Card();

				card.setEmail(rs.getString("email"));
				card.setMonth(rs.getString("cardtbl.month"));
				card.setYear(rs.getInt("cardtbl.year"));
				card.setCvv(rs.getInt("cardtbl.cvv"));
				card.setCardnumber(rs.getLong("cardtbl.cardnumber"));
				card.setCardid(rs.getInt("cardtbl.cardid"));
				card.setCardholdername(rs.getString("cardtbl.cardholdername"));
				card.setCardname(rs.getString("cardname"));
				card.setAccbal(rs.getInt("carddatabasetbl.accbal"));
				tempCard.add(card);

			}

			return tempCard;
		} catch (SQLException ex) {
			log.error("SQLException: getCardByEmail() " + ex.getMessage());
			throw ex;

		} catch (Exception ex) {
			log.error("Exception: getCardByEmail() " + ex.getMessage());
			throw new Exception("Something went wrong.");

		} finally {

			helper.closeResource(pstmt, con);

		}

	}

	@Override
	public boolean checkCardFromVisaAPI(Card card) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		log.info("Checking card in Visa API");

		try {
			con = helper.getConnection();
			pstmt = con.prepareStatement(CHECK_CARD_FROM_VISA_API);
			pstmt.setString(1, card.getMonth());
			pstmt.setInt(2, card.getYear());
			pstmt.setInt(3, card.getCvv());
			pstmt.setLong(4, card.getCardnumber());
			pstmt.setString(5, card.getCardholdername());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			} 
		} catch (SQLException sqlex) {
			log.error("SQLException in checkcard() : " + sqlex.getMessage());

		} catch (Exception ex) {
			log.error("Exception in checkcard() : " + ex.getMessage());

		} finally {

			helper.closeResource(pstmt, con);

		}
		return false;
	}

	@Override
	public void updateBalance(long cardnumber, int balance) {

		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = helper.getConnection();
			pstmt = con.prepareStatement(UPDATE_BALANCE);
			pstmt.setInt(1, balance);
			pstmt.setLong(2, cardnumber);
			int result = pstmt.executeUpdate();

			if (result > 0) {
				log.info("Balance updated successfully");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			helper.closeResource(pstmt, con);
		}

	}

	@Override
	public int getBalance(long cardnumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		log.info("Inside getbalance method");

		try {
			con = helper.getConnection();
			pstmt = con.prepareStatement(GET_BALANCE);

			pstmt.setLong(1, cardnumber);

			rs = pstmt.executeQuery();

			if (rs.next())
				return rs.getInt("accbal");

		} catch (Exception ex) {
			log.error("Exception: getBalance " + ex.getMessage());
		} finally {

			helper.closeResource(pstmt, con);

		}
		return -1;
	}

	@Override
	public boolean checkCardForPaymentValidation(long cardnumber, int cvv) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		log.info("Inside check method");

		try {
			con = helper.getConnection();
			pstmt = con.prepareStatement(CHECK_CARD_FOR_PAYMENT_VALIDATION);
			pstmt.setLong(1, cardnumber);
			pstmt.setInt(2, cvv);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			log.error("Hello : " + ex.getMessage());

		} finally {
			helper.closeResource(pstmt, con);
		}
		return false;
	}

	@Override
	public List<Long> getCardNumberByEmailId(String friend_email) {
		List<Long> tempfriendcardnumber = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = helper.getConnection();
			pstmt = con.prepareStatement(SELECT_FRIEND_BY_EMAIL);
			pstmt.setString(1, friend_email);

			rs = pstmt.executeQuery();
			tempfriendcardnumber = new ArrayList<>();
			while (rs != null && rs.next()) {
				long friendcardnumber = rs.getLong("cardnumber");
				tempfriendcardnumber.add(friendcardnumber);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			helper.closeResource(pstmt, con);
		}
		return tempfriendcardnumber;
	}

	@Override
	public boolean checkCardExist(String user_email) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = helper.getConnection();
			pstmt = con.prepareStatement(CHECK_CARD_EXISTS_IN_DATABASE);
			pstmt.setString(1, user_email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				log.info("Card exists!");
				return true;
			} else {
				log.info("Card doesnt exists!");
				return false;
			}
		} catch (SQLException ex) {
			log.error("SQLException: checkCardExist() " + ex.getMessage());
			throw ex;

		} catch (Exception ex) {
			log.error("Exception: checkCardExist() " + ex.getMessage());
			throw new Exception("Something went wrong.");
		} finally {
			helper.closeResource(pstmt, con);
		}
	}

}
