package com.openmrs.javamail;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailAttachmentsSender {


	/**
	 * @author Palpandi
	 * Send an email with attachments to the specified recipients.
	 *
	 * @param host The SMTP server host.
	 * @param port The SMTP server port.
	 * @param userName The username for SMTP server authentication.
	 * @param password The password for SMTP server authentication.
	 * @param toAddress An array of email addresses of the recipients.
	 * @param subject The subject of the email.
	 * @param message The email message.
	 * @param attachFiles An array of file paths to be attached to the email.
	 * @throws AddressException If there's an issue with the email addresses.
	 * @throws MessagingException If there's an issue with email messaging.
	 * @implNote This method sends an email with attachments to the specified recipients. It uses the provided SMTP server
	 * settings for configuration. The email's subject, message, and attachments are specified in the method parameters.
	 */
	public static void sendEmailWithAttachments(String host, String port, final String userName, final String password,
			String[] toAddress, String subject, String message, String... attachFiles)
			throws AddressException, MessagingException {
		// sets SMTP server properties

		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);
 
		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));

		InternetAddress[] addressTo = new InternetAddress[toAddress.length];
		for (int i = 0; i < toAddress.length; i++)
			addressTo[i] = new InternetAddress(toAddress[i]);
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		/*
		 * InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		 * msg.setRecipients(Message.RecipientType.TO, toAddresses);
		 */
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// adds attachments
		if (attachFiles != null && attachFiles.length > 0) {
			for (String filePath : attachFiles) {
				MimeBodyPart attachPart = new MimeBodyPart();

				try {
					attachPart.attachFile(filePath);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);
			}
		}

		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);

	}



}
