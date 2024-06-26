package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Item;


public interface CartService {
	List<Cart> getAll();

	Cart save(Cart cart);

	Cart getByid(long id);

	void delete(long id);

	Cart update(long id, Item order);
}
