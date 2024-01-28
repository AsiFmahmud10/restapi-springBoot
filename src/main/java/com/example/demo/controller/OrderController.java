package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.OrderDto;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Food;
import com.example.demo.entity.Order;
import com.example.demo.service.CustomerService;
import com.example.demo.service.FoodService;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("api/v1/order/")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping()
	public List<Order> get() {
		return orderService.findAll(); 
	}
	
	@PostMapping("customer/{customerId}/food")
	public ResponseEntity<Order> save(@RequestBody OrderDto orderDto,@PathVariable("customerId") long customerId) {
		Customer customer = customerService.getByid(customerId);
		Order order = new Order();
		order.setCarts(orderDto.getCarts());
		order.setCustomer(customer);
		
		return new ResponseEntity<Order>( orderService.save(order),HttpStatus.CREATED);  
	}

	@GetMapping("/{id}")
	public Order getByid(@PathVariable("id") long id) {
		System.out.print(id);
		return orderService.getByid(id);
	}

	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable("id") long id) {
		orderService.delete(id);
	}
	

}
