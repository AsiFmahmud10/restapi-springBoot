package com.example.demo.exception;

import lombok.Data;

@Data
public class UserAlradyExistedException extends Exception {
	
	private final  String username;
    private final String message;
    
	public UserAlradyExistedException(String username) {
		super();
		this.username = username;
		this.message = String.format("username %s already existed ", username);
	}
	
	public String getMessage() {
		return  this.message;
	}
	
}
