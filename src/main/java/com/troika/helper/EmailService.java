package com.troika.helper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private static final Logger LOGGER = Logger.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender javaMailService;
	
	@Autowired
	private MailHelper mailHelper;

	public void sendEmail(String subject, String messageContent, String[] toEmail, String[] ccEmail,
			String[] bccEmail) {

		LOGGER.debug(" ::::::::::::::::::::::::::::::::::");
		LOGGER.debug("toEmail: ");
		printStringArray(toEmail);
		LOGGER.debug(" ccEmail: ");
		printStringArray(ccEmail);
		LOGGER.debug(" bccEmail: ");
		printStringArray(bccEmail);
		LOGGER.debug(" Subject ::::  " + subject.toUpperCase());
		LOGGER.debug("Contents :::::  " + messageContent.toUpperCase());

		LOGGER.debug(" ::::::::::::::::::::::::::::::::::");

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(toEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(messageContent);
		javaMailService.send(mailMessage);
		
//		try {
//			mailHelper.sendHTML(toEmail, ccEmail, bccEmail, subject, messageContent);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	private void printStringArray(String[] toEmail) {

		if (toEmail != null) {
			for (String emailId : toEmail) {

				LOGGER.debug(emailId);
			}
		}
	}

}
