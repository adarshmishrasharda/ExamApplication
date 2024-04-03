package com.exam.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.helper.UserFoundException;
import com.exam.models.ForgotPasswordRequest;
import com.exam.models.ForgotPasswordResponse;
import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.service.ForgotPasswordService;
import com.exam.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	//creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception
	{
		//encoding password with bcryptpassword encoder
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<UserRole> setrole=new HashSet<>();
		Role role=new Role();
		role.setRoleId(47L);
		role.setRoleName("NRMAL");
		
		UserRole userRole=new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		setrole.add(userRole);
		return this.userService.createUser(user, setrole);
	}
	
	//get user by username
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username)
	{
		return this.userService.getUser(username);
		
	}
	
	//delet user by id

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userID)
	{
		this.userService.deleteUser(userID);
	}
	
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> exceptionHandler(UserFoundException ex){
		return null;
		
	}

    @PostMapping("/forgot-password")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) {
    	
    	
    	System.out.print("going to generate password");
    	String message=forgotPasswordService.sendOTP(request);
    	
    	ForgotPasswordResponse response=new ForgotPasswordResponse(message);
    	
    	return ResponseEntity.ok(response);
    	
    }	
    
    @PostMapping("/verify-otp")
    public ResponseEntity<ForgotPasswordResponse> verifyOtp(@RequestBody ForgotPasswordRequest request) {
    	
    	
    	System.out.print("going to verify password");
    	String message=forgotPasswordService.verifyOTP(request);
    	ForgotPasswordResponse response=new ForgotPasswordResponse(message);
    	return ResponseEntity.ok(response);
    }
    
    
    @PostMapping("/change-password")
    public ResponseEntity<ForgotPasswordResponse> changePass(@RequestBody ForgotPasswordRequest request)
    
    {
    	System.out.println(request);
    	request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
    	System.out.print(request);
    	String message=forgotPasswordService.changePassword(request);
    	ForgotPasswordResponse response=new ForgotPasswordResponse(message);
    			
    	
    	return ResponseEntity.ok(response);
    	
    }

}
