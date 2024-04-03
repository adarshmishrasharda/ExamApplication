package com.exam.service;

import org.springframework.stereotype.Service;

import com.exam.models.ForgotPasswordRequest;


@Service
public interface ForgotPasswordService {
	
	public String sendOTP(ForgotPasswordRequest request);
	
	public String verifyOTP(ForgotPasswordRequest request);    
    public String generateOTP() ;
    
    public String changePassword(ForgotPasswordRequest request);
    



}
