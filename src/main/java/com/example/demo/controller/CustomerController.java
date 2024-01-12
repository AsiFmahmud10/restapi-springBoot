package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping()
	public List<Customer> get() {
		return customerService.findAll(); 
	}
	
	@PostMapping()
	public ResponseEntity<Customer> save(@RequestBody Customer customer) {
		return new ResponseEntity<Customer>( customerService.save(customer),HttpStatus.CREATED);  
	}

	@GetMapping("/{id}")
	public Customer getByid(@PathVariable("id") long id) {
		System.out.print(id);
		return customerService.getByid(id);
	}

	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable("id") long id) {
		customerService.delete(id);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Customer> update(@PathVariable("id") long id,@RequestBody Customer customer) {
		

		
		return new ResponseEntity<Customer>(customerService.update(id, customer) ,HttpStatus.OK);
	}

}
