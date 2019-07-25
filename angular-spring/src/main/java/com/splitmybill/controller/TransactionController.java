package com.splitmybill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.splitmybill.bo.TransactionBo;
import com.splitmybill.dao.TransactionDAO;

@CrossOrigin(origins = "*")
@RestController

public class TransactionController {

	@Autowired
	TransactionDAO transactionDAO;

	@Autowired
	TransactionBo transactionBo;

	Logger log = LoggerFactory.getLogger(TransactionController.class);

	@RequestMapping("debittransaction/{user_email:.+}")
	public ResponseEntity<?> getDebitTransaction(@PathVariable("user_email") String userEmail) {

		return transactionBo.getDebitTransaction(userEmail);
	}

	@RequestMapping("credittransaction/{user_email:.+}")
	public ResponseEntity<?> getCreditTransaction(@PathVariable("user_email") String userEmail) {

		return transactionBo.getCreditTransaction(userEmail);
	}

}
