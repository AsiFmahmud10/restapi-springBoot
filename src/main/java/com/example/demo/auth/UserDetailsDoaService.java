package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MyUser;
import com.example.demo.repository.CustomerRepository;


@Service
public class UserDetailsDoaService implements UserDetailsService {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser user = customerRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

		UserDetails userDetails = User.builder().username(user.getUsername()).password(user.getPassword())
				.roles(user.getRole()).build();
		
		
		return userDetails;
	}

}
