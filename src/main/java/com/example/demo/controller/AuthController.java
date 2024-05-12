package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.JwtService;
import com.example.demo.dto.JwtReq;
import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.CustomerReq;
import com.example.demo.service.MyUserService;

import lombok.AllArgsConstructor;




@AllArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
	
	private final MyUserService myUserService; 

	

	@PostMapping("/reg")
	public ResponseEntity<JwtResponse> register(@RequestBody CustomerReq customerReq) {
        
		return new ResponseEntity<JwtResponse>(new JwtResponse(myUserService.registerCustomer(customerReq)) , HttpStatus.CREATED);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtReq jwtreq) {
		
	
		return new ResponseEntity<JwtResponse>(new JwtResponse(myUserService.authenticateCustomer(jwtreq)) , HttpStatus.CREATED);

	}
	
	
}
