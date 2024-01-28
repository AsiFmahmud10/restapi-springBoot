package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.entity.Food;
import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
@Service
public class OrederServiceImplimentation implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<Order> findAll() {
		
		return orderRepository.findAll();
	}

	@Override
	public Order save(Order order) {
		
		return orderRepository.save(order);
	}

	@Override
	public Order getByid(long id) {
		Order order = orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("id",id,"Food"));
		
		return order;
	}

	@Override
	public void delete(long id) {
		orderRepository.deleteById(id);
		
	}

}
