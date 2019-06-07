package com.wehaul.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailUtilsTests {
	
	@Autowired
	EMailUtils email;

	@Test
	public  void sendEmail() {
		email.sendMail("peeyush1282@gmail.com", "test email a", "test subject aa");
	}
	
}
