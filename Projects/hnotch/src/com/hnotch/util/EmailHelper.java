package com.hnotch.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.hnotch.values.constants.Constants;

public class EmailHelper {

	public boolean sendEmail(String emailAddress, String newPassword) throws MessagingException{
		// Recipient's email ID needs to be mentioned.
	      String to = emailAddress;

	      // Sender's email ID needs to be mentioned
	      String from = Constants.FROM_EMAIL_;
	      final String username = Constants.FROM_EMAIL_;;//change accordingly
	      final String password = "\"fighter\"";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "25");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
		});

	      try {
	            // Create a default MimeMessage object.
	            Message message = new MimeMessage(session);

	   	   // Set From: header field of the header.
		   message.setFrom(new InternetAddress(from));

		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,
	              InternetAddress.parse(to));

		   // Set Subject: header field
		   message.setSubject(Constants.EMAIL_PASSWORD_RESET_SUBJECT);

		   // Send the actual HTML message, as big as you like
		   message.setContent(
	              "<h1>We have received a password reset request.</h1><br> Your password has been reset using below passcode. Please singin using this and change the password.<br>"+ newPassword,
	             "text/html");

		   // Send message
		   Transport.send(message);

		   System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
		   e.printStackTrace();
		   throw new RuntimeException(e);
	      }
		
		return true;
	}
}
