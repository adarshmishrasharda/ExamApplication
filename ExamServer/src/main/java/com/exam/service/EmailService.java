package com.exam.service;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
	
	
    public void sendOTPEmail(String to, String otp) throws MessagingException;


}
