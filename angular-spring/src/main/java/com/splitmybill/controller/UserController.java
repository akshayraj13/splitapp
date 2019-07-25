package com.splitmybill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitmybill.bo.UserBo;
import com.splitmybill.dao.UserDAO;

@CrossOrigin(origins = "*")
@RestController

public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserDAO userDao;

	@Autowired
	UserBo userBo;

	@RequestMapping("adduser/{user_email:.+}/{user_fname:.+}/{user_lname:.+}")
	public void addUser(@PathVariable("user_email") String userEmail, @PathVariable("user_fname") String userFname,
			@PathVariable("user_lname") String userLname) {

		try {
			userBo.addUser(userFname, userLname, userEmail);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}