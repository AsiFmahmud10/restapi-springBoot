package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Item;

public interface ItemService {
	List<Item> findAll();

	Item save(Item item);

	Item getByid(long id);

	void delete(long id);

}
