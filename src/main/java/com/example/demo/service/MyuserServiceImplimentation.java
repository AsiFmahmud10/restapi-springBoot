package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;

import com.example.demo.entity.MyUser;
import com.example.demo.repository.CustomerRepository;

@Service
public class MyuserServiceImplimentation implements MyUserService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<MyUser> findAll() {

		return customerRepository.findAll();
	}

	@Override
	public MyUser save(MyUser customer) {

		return customerRepository.save(customer);
	}

	@Override
	public MyUser getByid(long id) {
		MyUser customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("id", id, "Customer"));
		return customer;
	}

	@Override
	public void delete(long id) {
		customerRepository.deleteById(id);

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
		return customerRepository.findByUsername(name).orElseThrow(()-> new UsernameNotFoundException(name));
	}

}
