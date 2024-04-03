package com.exam.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.models.ForgotPasswordRequest;
import com.exam.models.User;
import com.exam.repo.ForgotPasswordRepository;
import com.exam.repo.UserRepository;
import com.exam.service.EmailService;
import com.exam.service.ForgotPasswordService;

@Service
public class ForgotPasswordServiceImple implements ForgotPasswordService {
	
    private static final int OTP_LENGTH = 6;
    
    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;
    
    @Autowired
    private UserRepository userRepository;
    

    
    @Autowired
    private EmailService emailService;

	@Override
	public String sendOTP(ForgotPasswordRequest request) {
		
		String otp=generateOTP();
		
		try {
			User user=userRepository.findByEmail(request.getEmail());
			
			if(user!=null)
			{
				emailService.sendOTPEmail(request.getEmail(), otp);

				request.setOtp(otp);
				request.setExpiryDateTime(LocalDateTime.now().plusMinutes(5));
				request.setValid(false);
			
				
				forgotPasswordRepository.save(request);
				return "OTP send successfully. Please Check your mail";
				
			}
			else
			{
				return "Failed to send OTP. Please try again";
				
			}
		
			//System.out.println("user is"+user.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
			return "Failed to send OTP. Please try again";
		}
		
		
		
		
	}
	
	
	public String verifyOTP(ForgotPasswordRequest request)
	{
		
		ForgotPasswordRequest request2formdb=forgotPasswordRepository.findByEmail(request.getEmail());
		
		System.out.println("request body"+request);
		System.out.println("request2formdb"+request2formdb);
		
		if(request.getEmail().equals(request2formdb.getEmail()) && request.getOtp().equals(request2formdb.getOtp())
				&& LocalDateTime.now().isBefore(request2formdb.getExpiryDateTime()))
		{
			request.setValid(true);
			request.setOtp(null);
			
			forgotPasswordRepository.save(request);
			System.out.println("request body"+request);
			
			
			return"Otp Verified Successfully";
			
		}
		
		else {
			return "Please Enter valid OTP. ";
			
			
		}
		
		
		
	}
	
	@Override
	public String changePassword(ForgotPasswordRequest request)
	{
		ForgotPasswordRequest request2= forgotPasswordRepository.findByEmail(request.getEmail());
		
		if(request2.isValid() && request.getEmail().equals(request2.getEmail()))
		{
			forgotPasswordRepository.delete(request);
			
			User user=userRepository.findByEmail(request.getEmail());
			
			user.setPassword(request.getPassword());
			
			userRepository.save(user);
			
			return "Password Changed Successfuly";
			
			
		}
		else
		{
			return "Error in changing Password"; 
		}
		
		
		
	}
	
	@Override
	public String generateOTP() {
		
		Random random =new Random();
		
		StringBuilder otp=new StringBuilder(OTP_LENGTH);
		
		for(int i=0;i<OTP_LENGTH;i++)
		{
			//generate random digit  0 to 9
			otp.append(random.nextInt(10));
			
		}
		return otp.toString();
	}

}
