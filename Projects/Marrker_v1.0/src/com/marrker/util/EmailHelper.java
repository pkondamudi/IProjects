package com.marrker.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.marrker.constant.values.AppConstants;
import com.marrker.data.beans.User;

public class EmailHelper {

	public boolean sendEmail(String emailAddress, String newPassword) throws MessagingException {
		// Recipient's email ID needs to be mentioned.
		String to = emailAddress;

		// Sender's email ID needs to be mentioned
		String from = AppConstants.FROM_EMAIL_;
		final String username = AppConstants.FROM_EMAIL_;
		;// change accordingly
		final String password = "no-replymer";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "mail.Marrker.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(AppConstants.EMAIL_PASSWORD_RESET_SUBJECT);

			// Send the actual HTML message, as big as you like
			message.setContent(
					"<h1>We have received a password reset request.</h1><br> Your password has been reset using below passcode. Please singin using this and change the password.<br><br> <b>Passcode:</b> "
							+ newPassword,
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

	public boolean sendInvitationEmail(String emailAddress, String refCode, User user) throws MessagingException{
		// Recipient's email ID needs to be mentioned.
	      String to = emailAddress;

	      // Sender's email ID needs to be mentioned
	      String from = AppConstants.FROM_EMAIL_;
	      final String username = AppConstants.FROM_EMAIL_;;//change accordingly
	      final String password = "no-replymer";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "mail.Marrker.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      //props.put("mail.smtp.starttls.enable", "true");
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
		   message.setSubject(AppConstants.INVITATION+" from "+user.getFirstname()+" "+user.getLastname());

		   // Send the actual HTML message, as big as you like
		   message.setContent(
	              "<h1>You have an invitation from Marrker on behalf of "+user.getFirstname()+" "+user.getLastname()+".</h1>"
	              + "<br> Below is your invitation link.<br><br> "
	              + "<b>Invitaiton Link:</b> <a href='www.Marrker.com/ref/"+refCode+"'>Click here.</a>",
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
