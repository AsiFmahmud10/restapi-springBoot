package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.MyUser;
import com.example.demo.service.MyUserService;



@Component
public class CommandLiner implements CommandLineRunner {
	@Autowired
	private MyUserService myUserService;
	
	@Override
	public void run(String... args) throws Exception {
		
		myUserService.handleAdminSetup();
		
	}

}
