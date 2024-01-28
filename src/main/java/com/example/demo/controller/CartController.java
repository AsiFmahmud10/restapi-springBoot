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

import com.example.demo.entity.Cart;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Food;
import com.example.demo.service.CartService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.FoodService;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {

	@Autowired
	CartService cartService;
	@Autowired
	CustomerService customerService;
	@Autowired
	FoodService foodService;

	@GetMapping()
	public List<Cart> get() {
		return cartService.findAll();
	}

	@PostMapping("/customer/{customerId}/food/{foodId}")
	public ResponseEntity<Cart> save(@RequestBody Cart cart, @PathVariable long customerId, @PathVariable long foodId) {
		Customer customer = customerService.getByid(customerId);
	    Food food = foodService.getByid(foodId);
	    cart.setFood(food);
	    cart.setCustomer(customer);
	    cart.setQuantity(0);
		
		return new ResponseEntity<Cart>(cartService.save(cart), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public Cart getByid(@PathVariable("id") long id) {
		
		return cartService.getByid(id);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") long id) {
		cartService.delete(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Cart> update(@PathVariable("id") long id, @RequestBody Cart cart) {

		return new ResponseEntity<Cart>(cartService.update(id, cart), HttpStatus.OK);
	}

}
