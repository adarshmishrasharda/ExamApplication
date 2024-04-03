package com.exam.helper;

public class UserFoundException extends Exception {
	
	
	public UserFoundException()
	{
		super("User with given username already present !! Please try with other ");
		
	}
	
	public UserFoundException(String msg) {
		super(msg);
	}

}
