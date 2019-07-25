package com.splitmybill.dao;

import java.util.List;

import com.splitmybill.model.Card;

public interface CardDAO {

	public void addCard(Card card) throws Exception;

	public List<Card> getCardByEmail(String email) throws Exception;;

	public boolean checkCardFromVisaAPI(Card card);

	public void updateBalance(long cardnumber, int balance);

	public int getBalance(long cardnumber);

	public boolean checkCardForPaymentValidation(long cardnumber, int cvv);

	public List<Long> getCardNumberByEmailId(String friend_email);

	public boolean checkCardExist(String user_email) throws Exception;

}
