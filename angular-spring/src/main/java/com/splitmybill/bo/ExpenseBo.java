package com.splitmybill.bo;

import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.splitmybill.dao.FriendDAO;
import com.splitmybill.model.Expense;

@Service

public class ExpenseBo {

	@Autowired
	FriendDAO friendDao;

	Logger log = LoggerFactory.getLogger(ExpenseBo.class);

	public ResponseEntity<String> addExpense(Expense newexpense) {

		String userEmail = newexpense.getUser_email();
		List<String> friendEmailList = newexpense.getFriend_email();
		int expense = newexpense.getExpense();
		if (expense < 0 || expense == 0)
			return new ResponseEntity<String>("Expense cannot be negative", HttpStatus.BAD_REQUEST);
		int size = friendEmailList.size();
		expense = expense / (size + 1);
		ListIterator<String> friendEmail = friendEmailList.listIterator();

		while (friendEmail.hasNext()) {
			try {
				updateExpense(userEmail, expense, friendEmail);

			} catch (Exception e) {
				log.error("Exception: updateExpense() Unable to update expense. " + e.getMessage());
				return new ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

			}
		}
		return new ResponseEntity<String>("Expense updated", HttpStatus.OK);

	}

	private void updateExpense(String userEmail, int expense, ListIterator<String> friendEmail) throws Exception {
		String email = friendEmail.next();
		int billtopay;
		billtopay = friendDao.getBillToPay(email, userEmail);
		billtopay = billtopay + expense;
		friendDao.updateBillToPay(email, userEmail, billtopay);

	}
}
