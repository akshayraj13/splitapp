package com.splitmybill.dao;

import java.util.List;
import com.splitmybill.model.Transaction;

public interface TransactionDAO {

	public void addTransaction(String user_email, String friend_email, int billtopay) throws Exception;

	public List<Transaction> getCreditTransaction(String user_email) throws Exception;

	public List<Transaction> getDebitTransaction(String user_email) throws Exception;

}
