package com.wehaul.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class EMailUtils {
	private static final Logger log = LoggerFactory.getLogger(EMailUtils.class);
	@Autowired
	private JavaMailSender sender;

	@RequestMapping("/sendMail")
	public void sendMail(String sendTo, String messageBody, String subjectLine) {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(sendTo);
			helper.setText(messageBody);
			helper.setSubject(subjectLine);
			sender.send(message);
			log.info(" Email sent to:: " + sendTo);
		} catch (MessagingException e) {
			log.info("Exception occured while sending mail" + e);

		}
	}
}
