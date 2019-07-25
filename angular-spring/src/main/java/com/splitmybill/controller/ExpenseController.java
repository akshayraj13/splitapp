package com.splitmybill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitmybill.bo.ExpenseBo;
import com.splitmybill.model.Expense;

@CrossOrigin(origins = "*")
@RestController

public class ExpenseController {

	@Autowired
	ExpenseBo expenseBo;

	Logger log = LoggerFactory.getLogger(ExpenseController.class);

	@PostMapping("/expense")
	public ResponseEntity<String> addExpense(@RequestBody Expense setexpense) {
		ResponseEntity<String> resp = expenseBo.addExpense(setexpense);
		return resp;
	}

}
