package com.splitmybill.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitmybill.bo.SettlementBo;
import com.splitmybill.model.Settlement;

@CrossOrigin(origins = "*")
@RestController

public class SettlementController {

	@Autowired
	SettlementBo settlementBo;

	Logger log = LoggerFactory.getLogger(SettlementController.class);

	@RequestMapping("settlement/{user_email:.+}")
	public List<Settlement> getSettlement(@PathVariable("user_email") String userEmail) {
		return settlementBo.getSettlement(userEmail);
	}

	@RequestMapping("sendreminder/{sender_user_email:.+}/{receiver_user_email:.+}")
	public ResponseEntity<?> sendReminder(@PathVariable("sender_user_email") String senderUserEmail,
			@PathVariable("receiver_user_email") String receiverUserEmail) {
		try {
			return new ResponseEntity<String>(settlementBo.sendReminder(senderUserEmail, receiverUserEmail),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
