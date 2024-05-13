package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.auth.JwtService;
import com.example.demo.dto.CustomerReq;
import com.example.demo.dto.JwtReq;
import com.example.demo.entity.MyUser;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UserAlradyExistedException;
import com.example.demo.repository.CustomerRepository;

import lombok.Value;


@Service
public class MyuserServiceImplimentation implements MyUserService {

	@Autowired
	CustomerRepository myUserRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtService jwtService;
	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public List<MyUser> findAll() {

		return myUserRepository.findAll();
	}

	@Override
	public MyUser save(MyUser customer) {

		return myUserRepository.save(customer);
	}

	@Override
	public MyUser getByid(long id) {
		MyUser customer = myUserRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("id", id, "Customer"));
		return customer;
	}

	@Override
	public void delete(long id) {
		myUserRepository.deleteById(id);

	}

	@Override
	public MyUser update(long id, MyUser customer) {
		MyUser existedCustomer = this.getByid(id);

		existedCustomer.setUsername(customer.getUsername());
		existedCustomer.setAddress(customer.getAddress());
		existedCustomer.setPhone(customer.getPhone());

		return this.save(existedCustomer);

	}

	@Override
	public MyUser findByUsername(String name) {
		// TODO Auto-generated method stub
		return myUserRepository.findByUsername(name).orElseThrow(()-> new UsernameNotFoundException(name));
	}

	@Override
	public String registerCustomer(CustomerReq customerReq) throws UserAlradyExistedException {
		
		
			if (myUserRepository.findByUsername(customerReq.getUsername()).isEmpty()) {
				MyUser customer = new MyUser();
				
				customer.setUsername(customerReq.getUsername());
				customer.setPassword(passwordEncoder.encode(customerReq.getPassword()));
				customer.setAddress(customerReq.getAddress());
				customer.setPhone(customerReq.getPhone());
				customer.setRole("user");
				
				myUserRepository.save(customer);
				String jwtToken = jwtService.generateToken(customer.getUsername());
				
				return jwtToken;
			}else {
				throw new UserAlradyExistedException(customerReq.getUsername());
			}
		
	}

	@Override
	public String authenticateCustomer(JwtReq jwtreq) {
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(jwtreq.getUsername(), jwtreq.getPassword()));
		
		
		if (authentication.isAuthenticated()) {
			
			return  jwtService.generateToken(jwtreq.getUsername()) ;
			
		} else {
			throw new UsernameNotFoundException("Invalid User Request");
		}
		
		
	}
	
	 public void handleAdminSetup() throws UserAlradyExistedException {
			
		 	String adminUsername = "admin";
			String adminPassword = "password";
		 	
		 	
			MyUser admin = new MyUser();
			admin.setUsername(adminUsername);
			admin.setRole("admin");
			admin.setPassword(passwordEncoder.encode( adminPassword ));
			
			
			if( myUserRepository.findByUsername(adminUsername).isEmpty()) {
					myUserRepository.save(admin);
					System.out.println("admin is up");
			 }else {
				 System.out.println("admin is already present ");
			 }
			
					
				
	 }

}
