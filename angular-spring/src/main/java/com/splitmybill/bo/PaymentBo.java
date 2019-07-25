package com.splitmybill.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splitmybill.dao.CardDAO;
import com.splitmybill.dao.FriendDAO;
import com.splitmybill.model.PaymentDetail;

@Service
public class PaymentBo {

	@Autowired
	FriendDAO friendDao;
	@Autowired
	TransactionBo transactionBo;
	@Autowired
	CardDAO cardDao;
	Logger log = LoggerFactory.getLogger(PaymentBo.class);

	public String doPayment(PaymentDetail paymentdetail) {

		long cardnumber = paymentdetail.getCardnumber();
		int cvv = paymentdetail.getCvv();
		String friend_email = paymentdetail.getFriend_email();
		int billtopay = paymentdetail.getBilltopay();
		String user_email = paymentdetail.getUser_email();
		Boolean card_status = cardDao.checkCardForPaymentValidation(cardnumber, cvv);

		if (!card_status) {
			return "Incorrect CVV";
		}

		List<Long> friendcardnumber = cardDao.getCardNumberByEmailId(friend_email);
		updateBalance(cardnumber, billtopay, friendcardnumber);

		try {
			updateBillToPay(friend_email, user_email);
		} catch (Exception e) {
			log.error("Exception: dopayment(): Unable to update BillToPay--" + e.getMessage());
		}

		transactionBo.addTransaction(user_email, friend_email, billtopay);
		return "Transaction was successfull";
	}

	private void updateBalance(long cardnumber, int billtopay, List<Long> friendcardnumber) {
		updateUserBalance(cardnumber, billtopay);
		updateFriendBalance(billtopay, friendcardnumber);
	}

	private void updateUserBalance(long cardnumber, int billtopay) {
		int userbalance = cardDao.getBalance(cardnumber);
		userbalance = userbalance - billtopay;
		cardDao.updateBalance(cardnumber, userbalance);
	}

	private void updateFriendBalance(int billtopay, List<Long> friendcardnumber) {
		int friendbalance = cardDao.getBalance(friendcardnumber.get(0));
		friendbalance = friendbalance + billtopay;
		cardDao.updateBalance(friendcardnumber.get(0), friendbalance);
	}

	private void updateBillToPay(String friend_email, String user_email) {

		try {
			friendDao.updateBillToPay(user_email, friend_email, 0);
			friendDao.updateBillToPay(friend_email, user_email, 0);
		} catch (Exception e) {
			log.error("Exception: updateBillToPay(): Unable to update BillToPay--" + e.getMessage());
		}
	}
}
