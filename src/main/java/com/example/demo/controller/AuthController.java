package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.JwtService;
import com.example.demo.dto.JwtReq;
import com.example.demo.dto.JwtResponse;
import com.example.demo.entity.MyUser;
import com.example.demo.service.MyUserService;

import lombok.AllArgsConstructor;




@AllArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
	
	final AuthenticationManager authenticationManager;
	final JwtService jwtService;
	final PasswordEncoder passwordEncoder;
    final MyUserService myUserService; 

	

	@PostMapping("/users")
	public ResponseEntity<JwtResponse> register(@RequestBody MyUser user) {
        
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		MyUser newUser = myUserService.save(user);
		String jwtToken = jwtService.generateToken(user.getUsername());
				
		return new ResponseEntity<JwtResponse>(new JwtResponse(jwtToken), HttpStatus.CREATED);
	}

	@PostMapping("/authenticate")
	public String authenticate(@RequestBody JwtReq jwtreq) {
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(jwtreq.getUsername(), jwtreq.getPassword()));
		
		
		if (authentication.isAuthenticated()) {
			
			return jwtService.generateToken(jwtreq.getUsername());
		} else {
			throw new UsernameNotFoundException("Invalid User Request");
		}

	}
	
	
}
