package com.splitmybill.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.splitmybill.dao.CardDAO;
import com.splitmybill.model.Card;

@Service
public class CardBo {

	@Autowired
	CardDAO cardDao;

	Logger log = LoggerFactory.getLogger(CardBo.class);

	public ResponseEntity<?> addCard(Card card) {
		if (cardDao.checkCardFromVisaAPI(card)) {
			try {
				cardDao.addCard(card);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Something went wrong" + e.getMessage(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			log.info("Card is Authentic...card is added!");
			return new ResponseEntity<Card>(card, HttpStatus.CREATED);
		} else {
			log.error("Invalid Credentials...card is not added!");
			return new ResponseEntity<String>("Invalid Credentials", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<String> checkCardExistInDatabase(String user_email) {
		log.info("checkCardExist(): Checking card exists before login");
		try {
			if (cardDao.checkCardExist(user_email)) {
				log.info("Card exist in database, user exist with given email!");
				return new ResponseEntity<String>("true", HttpStatus.OK);
			} else {
				log.info("Card does not exist, user does not exist with given email!");
				return new ResponseEntity<String>("false", HttpStatus.OK);
			}

		} catch (Exception e) {
			log.error("Exception: checkCardExist() " + e.getMessage());
			return new ResponseEntity<String>("false", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getCardByEmail(String emailId) {
		try {
			return new ResponseEntity<List<Card>>(cardDao.getCardByEmail(emailId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
