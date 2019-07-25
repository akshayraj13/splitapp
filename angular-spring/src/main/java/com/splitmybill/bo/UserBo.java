package com.splitmybill.bo;

import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splitmybill.dao.FriendDAO;
import com.splitmybill.dao.UserDAO;

@Service
public class UserBo {

	@Autowired
	FriendDAO friendDao;

	@Autowired
	UserDAO userDao;

	Logger log = LoggerFactory.getLogger(UserBo.class);

	public String addUser(String user_fname, String user_lname, String user_email) throws Exception {
		Boolean bool = userDao.checkUserExists(user_email);

		if (bool == false) {
			userDao.addUser(user_fname, user_lname, user_email);
			int friendid = friendDao.getUserByEmailId(user_email).getUserid();
			friendDao.updateFriendID(friendid, user_email);
			List<Integer> useridlist = friendDao.getUserIDList(user_email);
			ListIterator<Integer> userid = useridlist.listIterator();

			while (userid.hasNext()) {

				friendDao.updateUserID(friendid, userid.next(), user_email);
			}
		}
		return "User Added Successfully";
	}

}
