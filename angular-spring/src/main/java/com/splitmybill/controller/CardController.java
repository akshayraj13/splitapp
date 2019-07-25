package com.splitmybill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitmybill.bo.CardBo;
import com.splitmybill.model.Card;

@CrossOrigin(origins = "*")
@RestController

public class CardController {

	@Autowired
	CardBo cardBo;

	Logger log = LoggerFactory.getLogger(CardController.class);

	/* Before login check if card exists */
	@RequestMapping("checkcardexist/{email:.+}")
	public ResponseEntity<?> checkCardExist(@PathVariable String email) {
		ResponseEntity<String> resp = cardBo.checkCardExistInDatabase(email);
		return resp;

	}

	/* Add Card */
	@PostMapping("/card")
	public ResponseEntity<?> addCard(@RequestBody Card card) {
		ResponseEntity<?> resp = cardBo.addCard(card);
		return resp;
	}

	/* Get card details to view card */
	@GetMapping("card/{email:.+}")
	public ResponseEntity<?> getCardByEmail(@PathVariable String email) {
		ResponseEntity<?> resp = cardBo.getCardByEmail(email);
		return resp;
	}

}