package com.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.exam.service.EmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImple  implements EmailService{

	@Autowired
	private JavaMailSender emailSender;

	public void sendOTPEmail(String to, String otp) throws MessagingException {
		
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		helper.setSubject("Password Reset OTP");
		helper.setText("Your OTP for password reset is: " + otp);
		emailSender.send(message);
	}

}
