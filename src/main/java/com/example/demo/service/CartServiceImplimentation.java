package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Item;

import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CustomerRepository;

@Service
public class CartServiceImplimentation implements CartService {
	@Autowired
	CartRepository cartRepository;
	
	@Override
	public List<Cart> getAll() {
		
		return cartRepository.findAll();
	}

	@Override
	public Cart save(Cart cart) {
		
		return cartRepository.save(cart);
	}

	@Override
	public Cart getByid(long id) {
		Cart cart = cartRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("id", id, "cart"));
		return cart;
	}

	@Override
	public void delete(long id) {
		cartRepository.deleteById(id);
		
	}

	@Override
	public Cart update(long id, Item order) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Cart update(long id, Cart cart) {
//		Order existedCart = this.getByid(id);
////		
////		existedCustomer.setName(customer.getName());
////		existedCustomer.setAddress(customer.getAddress());
////		existedCustomer.setPhone(customer.getPhone());
//		
//		
//		return this.save(existedCart);
		
//	}



}
