package com.splitmybill.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.splitmybill.dao.TransactionDAO;

@Service
public class TransactionBo {
	@Autowired
	TransactionDAO transactionDAO;

	Logger log = LoggerFactory.getLogger(TransactionBo.class);

	public ResponseEntity<?> getDebitTransaction(String user_email) {
		try {
			return new ResponseEntity<>(transactionDAO.getDebitTransaction(user_email), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error in getting debit transaction.");
			return new ResponseEntity<String>("Unable to fetch Debit Transactions", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getCreditTransaction(String user_email) {

		try {
			return new ResponseEntity<>(transactionDAO.getCreditTransaction(user_email), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error in getting credit transaction.");
			return new ResponseEntity<String>("Unable to fetch Credit Transactions", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public void addTransaction(String user_email, String friend_email, int billtopay) {
		try {
			transactionDAO.addTransaction(user_email, friend_email, billtopay);
		} catch (Exception e) {
			log.error("Error in adding transaction.");
		}

	}

}
