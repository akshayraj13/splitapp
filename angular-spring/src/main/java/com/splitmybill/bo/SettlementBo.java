package com.splitmybill.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splitmybill.dao.FriendDAO;
import com.splitmybill.model.Settlement;
import com.splitmybill.model.User;
import com.splitmybill.service.EmailService;

@Service
public class SettlementBo {
	@Autowired
	FriendDAO friendDao;
	@Autowired
	EmailService emailservice;

	Logger log = LoggerFactory.getLogger(SettlementBo.class);

	public List<Settlement> getSettlement(String user_email) {
		List<User> userlist;
		List<Settlement> tempsettlement = null;
		try {
			userlist = friendDao.getFriendDetail(user_email);
			ListIterator<User> user = userlist.listIterator();
			tempsettlement = new ArrayList<Settlement>();

			while (user.hasNext()) {
				getIndividualSettlement(user_email, user, tempsettlement);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return tempsettlement;
	}

	private void getIndividualSettlement(String user_email, ListIterator<User> user, List<Settlement> tempsettlement)
			throws Exception {
		Settlement settlement;
		String friend_name = user.next().getUser_fname();
		user.previous();
		String friend_email = user.next().getUser_email();
		int billtopayusertofriend = friendDao.getBillToPay(user_email, friend_email);
		int billtopayfriendtouser = friendDao.getBillToPay(friend_email, user_email);
		int billtopay = Math.abs(billtopayusertofriend - billtopayfriendtouser);
		settlement = updateSettlement(friend_name, friend_email, billtopayusertofriend, billtopayfriendtouser,
				billtopay);
		tempsettlement.add(settlement);
	}

	private Settlement updateSettlement(String friend_name, String friend_email, int billtopayusertofriend,
			int billtopayfriendtouser, int billtopay) {
		Settlement settlement;
		settlement = new Settlement();
		settlement.setBilltopay(billtopay);
		settlement.setFriend_email(friend_email);

		if (billtopayusertofriend > billtopayfriendtouser) {
			settlement.setSettlementmsg("You owe " + friend_name + " Rs. " + billtopay);
			settlement.setStatus(1);
		} else {
			settlement.setSettlementmsg(friend_name + " owes you " + "Rs. " + billtopay);
			settlement.setStatus(0);
		}
		return settlement;
	}

	public String sendReminder(String sender_user_email, String receiver_user_email) throws Exception {
		User sender = friendDao.getUserByEmailId(sender_user_email);
		String sender_name = sender.getUser_fname();
		String subject = "SplitMyBill Reminder";
		String messageText = "Hi, You owe " + sender_name + " some amount, please clear your dues.";
		emailservice.sendEmail(sender_name, receiver_user_email, subject, messageText);
		return "Reminder sent";
	}

}
