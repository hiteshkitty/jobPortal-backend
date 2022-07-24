package com.troika.helper;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class MailHelper {
	private final String SMTP_HOST = "outbound.cisco.com";

	private final Session session;

	private final Logger log = Logger.getLogger(MailHelper.class);

	public MailHelper() {
		// Configure the default CISCO mail SMTP server
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", this.SMTP_HOST);
		// Get the default Session object for sending etc
		this.session = Session.getDefaultInstance(properties);
	}

	public void sendHTML(String[] emailto, String[] emailcc, String[] emailbcc, String subject, String html) throws Exception {
		this.sendHTML(emailto, emailcc, emailbcc, subject, html, null);
	}

	public void sendHTML(String[] emailto, String[] emailcc, String[] emailbcc, String subject, String html, File[] attachments) throws Exception {
		String from = "";
		String domainname = "vanilla";
		String hostname = "localhost";

		if (System.getProperty("com.sun.aas.instanceRoot") != null) {
			domainname = new File(System.getProperty("com.sun.aas.instanceRoot")).getName().toLowerCase(Locale.ENGLISH);
		}

		try {
			hostname = InetAddress.getLocalHost().getCanonicalHostName().toLowerCase(Locale.ENGLISH);
		} catch (UnknownHostException ex) {
			this.log.error("Error in getting hostname -> " + ex.getMessage());
		}

		// Force a prepend of a domain if there is no "*.<STRING>.com" at the end of the hostname
		if (!hostname.matches(".+\\.\\w+\\.com")) {
			hostname = hostname + ".cisco.com";
		}
		from = domainname + "-noreply@" + hostname;

		MimeMessage message = new MimeMessage(this.session);

		this.log.info("Sending HTML email from [" + from + "] to [" + emailto + "] / cc [" + emailcc + "] -> '" + subject + "'");

		try {
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			for (String to : emailto) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}

			for (String cc : emailcc) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			}

			for (String bcc : emailbcc) {
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
			}

			// Set Subject: header field
			message.setSubject(subject);

			if (attachments == null) {
				// Send the actual HTML message, as big as you like - no attachments
				message.setContent(html, "text/html");
			} else {
				Multipart multipartmail = new MimeMultipart();

				// We're sending an attachment or two... using MIME
				// Add the message itself
				MimeBodyPart messagepart = new MimeBodyPart();
				messagepart.setText(html);
				messagepart.setHeader("Content-Type", "text/html");
				multipartmail.addBodyPart(messagepart);

				// Iterate through the files
				for (File attachment : attachments) {
					MimeBodyPart attachmentfilepart = new MimeBodyPart();

					FileDataSource filedatasource = new FileDataSource(attachment);

					attachmentfilepart.setDataHandler(new DataHandler(filedatasource));
					attachmentfilepart.setFileName(filedatasource.getName());

					multipartmail.addBodyPart(attachmentfilepart);
				}

				message.setContent(multipartmail);
			}

			// Send message
			Transport.send(message);
		} catch (Exception ex) {
			// throw new UtilsException(ServicesException.GROUP.INTERNAL, "Unable to dispatch email", ex);
			throw ex;
		}

	}

	public void sendText(String[] emailto, String[] emailcc, String[] emailbcc, String subject, String text) throws Exception {
		this.sendText(emailto, emailcc, emailbcc, subject, text, null);
	}

	public void sendText(String[] emailto, String[] emailcc, String[] emailbcc, String subject, String text, File[] attachments) throws Exception {
		String from = "";
		String domainname = "vanilla";
		String hostname = "localhost";

		MimeMessage message = new MimeMessage(this.session);

		if (System.getProperty("com.sun.aas.instanceRoot") != null) {
			domainname = new File(System.getProperty("com.sun.aas.instanceRoot")).getName().toLowerCase(Locale.ENGLISH);
		}

		try {
			hostname = InetAddress.getLocalHost().getCanonicalHostName().toLowerCase(Locale.ENGLISH);
		} catch (UnknownHostException ex) {
			this.log.error("Error in getting hostname -> " + ex.getMessage());
		}

		from = domainname + "-noreply@" + hostname;

		this.log.info("Sending TEXT email from [" + from + "] to [" + emailto + " ] /" + emailcc + "," + "] -> '" + subject + "'");

		try {
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			for (String to : emailto) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}

			for (String cc : emailcc) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			}

			for (String bcc : emailbcc) {
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
			}

			// Set Subject: header field
			message.setSubject(subject);

			if (attachments == null) {
				// Now set the actual message
				message.setText(text);
			} else {
				Multipart multipartmail = new MimeMultipart();

				// We're sending an attachment or two... using MIME
				// Add the message itself
				MimeBodyPart messagepart = new MimeBodyPart();
				messagepart.setText(text);
				messagepart.setHeader("Content-Type", "text/plain");
				multipartmail.addBodyPart(messagepart);

				// Iterate through the files
				for (File attachment : attachments) {
					MimeBodyPart attachmentfilepart = new MimeBodyPart();

					FileDataSource filedatasource = new FileDataSource(attachment);

					attachmentfilepart.setDataHandler(new DataHandler(filedatasource));
					attachmentfilepart.setFileName(filedatasource.getName());

					multipartmail.addBodyPart(attachmentfilepart);
				}

				message.setContent(multipartmail);
			}

			// Send message
			Transport.send(message);
		} catch (MessagingException ex) {
			throw ex;
		}
	}
}
