package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.entity.Food;
import com.example.demo.entity.Item;
import com.example.demo.entity.Cart;
import com.example.demo.repository.ItemRepository;
@Service
public class ItemServiceImplimentation implements ItemService {
	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public List<Item> findAll() {
		
		return itemRepository.findAll();
	}

	@Override
	public Item save(Item item) {
		
		return itemRepository.save(item);
	}

	@Override
	public Item getByid(long id) {
		Item item = itemRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("id",id,"Food"));
		
		return item;
	}

	@Override
	public void delete(long id) {
		itemRepository.deleteById(id);
		
	}

}
