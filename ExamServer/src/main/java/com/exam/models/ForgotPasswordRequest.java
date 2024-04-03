package com.exam.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ForgotPasswordRequest {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "userId")
//	private Long id;
	
	
	@Id
	@Column
	private String email;
	
	@Column
	private String otp;
	
	
	@Column
    private LocalDateTime expiryDateTime;
	
	@Column
	private boolean valid;
	
	@Column
	private String password;



	public ForgotPasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	



public ForgotPasswordRequest(String email, String otp, LocalDateTime expiryDateTime, boolean valid,
			String password) {
		super();
		this.email = email;
		this.otp = otp;
		this.expiryDateTime = expiryDateTime;
		this.valid = valid;
		this.password = password;
	}







//	public Long getId() {
//		return id;
//	}
//
//
//
//	public void setId(Long id) {
//		this.id = id;
//	}





	public LocalDateTime getExpiryDateTime() {
		return expiryDateTime;
	}



	public String getPassword() {
		return password;
	}







	public void setPassword(String password) {
		this.password = password;
	}







	public void setExpiryDateTime(LocalDateTime expiryDateTime) {
		this.expiryDateTime = expiryDateTime;
	}



	public boolean isValid() {
		return valid;
	}



	public void setValid(boolean valid) {
		this.valid = valid;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}



	@Override
	public String toString() {
		return "ForgotPasswordRequest [email=" + email + ", otp=" + otp + ", expiryDateTime=" + expiryDateTime
				+ ", valid=" + valid + ", password=" + password + "]";
	}


	
	

}
