package com.splitmybill.bo;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.splitmybill.dao.FriendDAO;
import com.splitmybill.model.User;
import com.splitmybill.service.EmailService;

@Service
public class FriendBo {

	@Autowired
	FriendDAO friendDao;

	@Autowired
	EmailService emailservice;

	Logger log = LoggerFactory.getLogger(FriendBo.class);

	public ResponseEntity<?> getFriendByEmail(String userEmail) throws SQLException {
		try {
			return new ResponseEntity<List<User>>(friendDao.getFriendDetail(userEmail), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Exception: getFirstNameByEmail()" + e.getMessage());
			return new ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public String addFriend(String senderUseEmail, String receiverUserEmail) throws Exception {

		User sender = friendDao.getUserByEmailId(senderUseEmail);
		User receiver = friendDao.getUserByEmailId(receiverUserEmail);
		int senderId = sender.getUserid();
		int receiverid = receiver.getUserid();

		/*
		 * If receiver is not present on Database, his userId in friendtbl is set to 0
		 */
		if (receiver.getUserid() == 0) {
			inviteFriend(senderUseEmail, receiverUserEmail, sender, senderId);
		} else {
			addFriendAlreadyPresentOnDatabase(senderUseEmail, receiverUserEmail, senderId, receiverid);
		}
		return "Friend Request Sent.";
	}

	private void inviteFriend(String senderUserEmail, String receiverUserEmail, User sender, int senderId) {
		String senderName = sender.getUser_fname();
		String subject = "SplitMyBill Invitation";
		String messageText = "Hi, " + senderName + " has invited you to connect at www.splitmybill.com";
		emailservice.sendEmail(senderName, receiverUserEmail, subject, messageText);
		friendDao.addFriend(senderId, 0, senderUserEmail, receiverUserEmail);
		friendDao.addFriend(0, senderId, receiverUserEmail, senderUserEmail);
	}

	private void addFriendAlreadyPresentOnDatabase(String senderUserEmail, String receiverUserEmail, int senderId,
			int receiverId) {
		friendDao.addFriend(senderId, receiverId, senderUserEmail, receiverUserEmail);
		friendDao.addFriend(receiverId, senderId, receiverUserEmail, senderUserEmail);
	}
}
