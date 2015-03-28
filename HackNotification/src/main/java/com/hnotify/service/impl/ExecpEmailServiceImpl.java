package com.hnotify.service.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hnotify.service.ExecpEmailService;

@Service
public class ExecpEmailServiceImpl implements ExecpEmailService{
	
	Logger slf4jLoggerWS = LoggerFactory.getLogger("ws");
	Logger slf4jLoggerCMS = LoggerFactory.getLogger("cms");
	
	@Value("${EMAIL_HOST}") private String EMAIL_HOST;
	@Value("${EMAIL_PORT}") private String EMAIL_PORT;
	@Value("${EMAIL_FROM}") private String EMAIL_FROM;
	@Value("${EMAIL_TO}") private String EMAIL_TO;
	@Value("${EMAIL_USER}") private String EMAIL_USER;
	@Value("${EMAIL_PASSWORD}") private String EMAIL_PASSWORD;
	@Value("${EXCEPTION_EMAIL_SWITCH}") private String EXCEPTION_EMAIL_SWITCH;
	
	
	
	@Override
	public void printAndSendExecption(String className, Exception e,String type) {
		String exception = className+error(e);
		if(type.equalsIgnoreCase("WS")){
			slf4jLoggerWS.error(exception);
		}
		else{
			slf4jLoggerCMS.error(exception);
		}
		if(EXCEPTION_EMAIL_SWITCH.equalsIgnoreCase("ON")){
			sendMail(exception);
		}
	}
	
	private String error(Exception e)
    {
     StringBuffer bf = new StringBuffer();
     bf.append(e.toString());
     for(int i=0; i<e.getStackTrace().length; i++)
     {
      bf.append("\n").append(e.getStackTrace()[i].toString());      
     }
     bf.append("\n").append(new Date());
     return ":: ERROR::" + bf.toString();
    }
	
	private void sendMail(String exception) {

		Properties props = new Properties();
		props.put("mail.smtp.host", EMAIL_HOST);
		props.put("mail.smtp.socketFactory.port", EMAIL_PORT);

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", EMAIL_PORT);
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_USER, EMAIL_PASSWORD);
			}
		});
		try {
			slf4jLoggerCMS.error(this.getClass().getName()+":: SENDING MAIL EXECPTION::");
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(EMAIL_FROM));
			String[] emails = EMAIL_TO.split(",");
			for (String email : emails) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			}
			
//			message.addRecipient(RecipientType.CC, new InternetAddress(objEmail.getEmail_cc()));
			
			message.setSubject("EXECPTION - CMS");

			message.setHeader("X-Priority", "1");

			BodyPart messageBodyPart1 = new MimeBodyPart();

			messageBodyPart1.setContent(exception, "text/html");
			/*MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			String fileSource = "E:\\HTMedia_DigitalQuotient_Intro.pdf";
			DataSource source = new FileDataSource(fileSource);

			messageBodyPart2.setDataHandler(new DataHandler(source));*/

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart1);

			// multipart.addBodyPart(messageBodyPart2);

			// 6) set the multiplart object to the message object
			message.setContent(multipart);

			// 7) send message

			Transport.send(message); // trnsport.sendMessage(message,
										// message.getAllRecipients());

		} catch (Exception e) {
			slf4jLoggerCMS.error("Email manager :sendEmail: Exception", e);
		}

	}

	@Override
	public void sendMailOfAddSeat(String type,String strException, String cinemacode, long sessionid, long userid) {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", EMAIL_HOST);
		props.put("mail.smtp.socketFactory.port", EMAIL_PORT);

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", EMAIL_PORT);
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_USER, EMAIL_PASSWORD);
			}
		});
		try {
			slf4jLoggerCMS.error(this.getClass().getName()+":: SENDING MAIL EXECPTION::");
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(EMAIL_FROM));
			String[] emails = EMAIL_TO.split(",");
			for (String email : emails) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			}
			
//			message.addRecipient(RecipientType.CC, new InternetAddress(objEmail.getEmail_cc()));
			
			message.setSubject("EXECPTION - CMS");

			message.setHeader("X-Priority", "1");

			BodyPart messageBodyPart1 = new MimeBodyPart();

			messageBodyPart1.setContent(getContent(type,strException,cinemacode,sessionid,userid), "text/html");
			/*MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			String fileSource = "E:\\HTMedia_DigitalQuotient_Intro.pdf";
			DataSource source = new FileDataSource(fileSource);

			messageBodyPart2.setDataHandler(new DataHandler(source));*/

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart1);

			// multipart.addBodyPart(messageBodyPart2);

			// 6) set the multiplart object to the message object
			message.setContent(multipart);

			// 7) send message

			Transport.send(message); // trnsport.sendMessage(message,
										// message.getAllRecipients());

		} catch (Exception e) {
			slf4jLoggerCMS.error("Email manager :sendEmail: Exception", e);
		}

	}

	private String getContent(String type,String strException, String cinemacode, long sessionid, long userid) {
		return "Type is:: " + type + "<br>" +
				"Execption is:: " + strException + "<br>" +
						"cinemacode is:: " + cinemacode + "<br>" + 
						"session ID is:: " + sessionid + "<br>" +
						"User Id is:: " + userid + "<br>";
	}
	

}
