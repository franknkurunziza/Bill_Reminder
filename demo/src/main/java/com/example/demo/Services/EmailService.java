package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	// call on our JavaMailSend class to send the email
	@Autowired
	private JavaMailSender mailSender;
	// creating a method to set emailTo, the body as message, subject the reason of the email
	public void sendSimpleMail(String to,String body, String subject) {
		// calling on our SimpleMailMessage for simple messages
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("javatestmailsender@gmail.com");
		message.setTo(to);
		message.setText(body);
		message.setSubject(subject);
		
		mailSender.send(message);
		System.out.println("Email send ..");
	}
}
