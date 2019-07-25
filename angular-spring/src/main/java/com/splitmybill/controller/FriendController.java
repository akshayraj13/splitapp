package com.splitmybill.controller;

import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitmybill.bo.FriendBo;
import com.splitmybill.dao.TransactionDAO;

@CrossOrigin(origins = "*")
@RestController

public class FriendController {

	@Autowired
	FriendBo friendBo;

	@Autowired
	TransactionDAO transactionDAO;

	Logger log = LoggerFactory.getLogger(FriendController.class);

	@RequestMapping("friend/{user_email:.+}")
	public ResponseEntity<?> getFriendByEmail(@PathVariable("user_email") String userEmail){
		try {
			return friendBo.getFriendByEmail(userEmail);
		} catch (SQLException e) {
			log.error("SQLException: getFriendByEmail() : "+e.getMessage());
			return new ResponseEntity<String>("Unable to fetch friend name", HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
	
	}

	@RequestMapping("sendemail/{sender_user_email:.+}/{receiver_user_email:.+}")
	public ResponseEntity<String> addFriend(@PathVariable("sender_user_email")  String senderUserEmail, @PathVariable("receiver_user_email") String receiverUserEmail)
			throws SQLException {
		try {

			if(receiverUserEmail.compareTo(senderUserEmail)==0)
				return new ResponseEntity<String>("Sender Email and Receiver Email are same", HttpStatus.BAD_REQUEST);

			return new ResponseEntity<String>(friendBo.addFriend(senderUserEmail, receiverUserEmail), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception: addfriend " + e.getMessage());
			return new ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}




	

}
