package com.splitmybill.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class EmailService {

	@Value("${email.host:}")
	private String host;

	@Value("${email.user:}")
	private String user;

	@Value("${email.pass:}")
	private String pass;

	Logger log = LoggerFactory.getLogger(EmailService.class);

	public void sendEmail(String sender_name, String receiver_user_email, String subject, String messageText) {

		try {

			boolean sessionDebug = false;

			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");

			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);

			InternetAddress[] address = { new InternetAddress(receiver_user_email) };
			msg.setRecipients(Message.RecipientType.TO, address);

			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(messageText);

			Transport transport = mailSession.getTransport("smtp");

			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("message send successfully");
			return;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("Unable to send Mail from " + sender_name + " to " + receiver_user_email);
			log.error(ex.getMessage(), ex);
		}
	}
}
