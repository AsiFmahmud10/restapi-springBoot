package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Order;



public interface OrderService {
	List<Order> findAll();

	Order save(Order order);

	Order getByid(long id);

	void delete(long id);

}
