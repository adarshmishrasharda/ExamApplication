package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.models.ForgotPasswordRequest;
import com.exam.models.User;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordRequest, String>{
	
	
	public ForgotPasswordRequest findByEmail(String email);


}
