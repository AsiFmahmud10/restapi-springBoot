package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Customer;

public interface CustomerService {

	List<Customer> findAll();

	Customer save(Customer customer);

	Customer getByid(long id);

	void delete(long id);

	Customer update(long id, Customer customer);

	

}
