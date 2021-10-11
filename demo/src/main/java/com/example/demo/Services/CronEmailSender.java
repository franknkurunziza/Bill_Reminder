package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Bill;
import com.example.demo.Models.User;

@Service
public class CronEmailSender {
	@Autowired
	private UserService uServ;
	
	@Autowired
	private EmailService eServ;
	
	@Scheduled(cron="* 30 10 11 10 *")
	public void checkPayment() {
		uServ.checkPayment();
	}
}
