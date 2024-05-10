package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.MyUser;



public interface MyUserService {

	List<MyUser> findAll();

	MyUser save(MyUser customer);

	MyUser getByid(long id);

	void delete(long id);

	MyUser update(long id, MyUser myuser);

	MyUser findByUsername(String name);

	

}
