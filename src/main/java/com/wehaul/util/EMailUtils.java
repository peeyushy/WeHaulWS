package com.wehaul.util;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class EMailUtils {
	private static final Logger log = LoggerFactory.getLogger(EMailUtils.class);
	@Autowired
	private JavaMailSenderImpl sender;

	@Value("${spring.mail.username}")
	private String EMAIL_USERNAME;

	@Value("${fromemaildisplayname}")
	private String FROMEMAIL_DISPLAYNAME;

	@RequestMapping("/sendMail")
	public void sendMail(String sendTo, String messageBody, String subjectLine) {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress(EMAIL_USERNAME, FROMEMAIL_DISPLAYNAME));
			helper.setTo(sendTo);
			helper.setText(messageBody);
			helper.setSubject(subjectLine);
			sender.send(message);
			log.info(" Email sent to:: " + sendTo);
		} catch (MessagingException e1) {
			log.info("MessagingException occured while sending mail::" + e1);
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			log.info("UnsupportedEncodingException occured while sending mail::" + e);
			e.printStackTrace();
		}
	}
}