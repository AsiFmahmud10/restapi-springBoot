package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustoemrServiceImplimentation implements CustomerService  {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public List<Customer> findAll() {
		
		return customerRepository.findAll();
	}

	@Override
	public Customer save(Customer customer) {
		
		return customerRepository.save(customer);
	}

	@Override
	public Customer getByid(long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("id", id, "Customer"));
		return customer;
	}

	@Override
	public void delete(long id) {
		customerRepository.deleteById(id);
		
	}

	@Override
	public Customer update(long id, Customer customer) {
		Customer existedCustomer = this.getByid(id);
		
		existedCustomer.setName(customer.getName());
		existedCustomer.setAddress(customer.getAddress());
		existedCustomer.setPhone(customer.getPhone());
		
		
		return this.save(existedCustomer);
		
	}
	

}
