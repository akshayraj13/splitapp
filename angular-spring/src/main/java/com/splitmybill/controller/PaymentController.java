package com.splitmybill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitmybill.bo.PaymentBo;
import com.splitmybill.model.PaymentDetail;

@CrossOrigin(origins = "*")
@RestController

public class PaymentController {
	@Autowired
	PaymentBo paymentBo;
	Logger log = LoggerFactory.getLogger(PaymentController.class);

	@PostMapping("/payment")
	public ResponseEntity<String> doPayment(@RequestBody PaymentDetail paymentDetail) {
		try {
			return new ResponseEntity<String>(paymentBo.doPayment(paymentDetail), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
