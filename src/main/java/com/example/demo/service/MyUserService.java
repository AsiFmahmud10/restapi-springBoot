package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.JwtReq;
import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.CustomerReq;
import com.example.demo.entity.MyUser;
import com.example.demo.exception.UserAlradyExistedException;



public interface MyUserService {

	List<MyUser> findAll();

	MyUser save(MyUser customer);

	MyUser getByid(long id);

	void delete(long id);

	MyUser update(long id, MyUser myuser);

	MyUser findByUsername(String name);

	String registerCustomer(CustomerReq customer);

	String authenticateCustomer(JwtReq jwtreq);

	void handleAdminSetup() throws UserAlradyExistedException;

	

}
