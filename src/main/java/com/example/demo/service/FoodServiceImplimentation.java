package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Food;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.FoodRepository;

@Service
public class FoodServiceImplimentation implements FoodService {
	@Autowired
	FoodRepository foodRepository;
	
	@Override
	public List<Food> findAll() {
		
		return foodRepository.findAll();
	}

	@Override
	public Food save(Food food) {
		
		return foodRepository.save(food);
	}

	@Override
	public Food getByid(long id) {
		Food food = foodRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("id",id,"Food"));
		
		return food;
	}

	@Override
	public void delete(long id) {
		foodRepository.deleteById(id);
		
	}

	@Override
	public Food update(long id, Food food) {
		
		Food existedFood = this.getByid(id);

		existedFood.setName(food.getName());
		existedFood.setPrice(food.getPrice());
		existedFood.setAvailability(food.isAvailability());
		
		
		return this.save(existedFood);
		
	}

	@Override
	public List<Food> findByNameLike(String foodname) {
		
		return foodRepository.findByNameLike(foodname);
	}

	@Override
	public List<Food> getAll() {
		
		return foodRepository.findAll();
	}

	@Override
	public List<Food> findByNameAndRangOfPrice(String foodname, int start, int end) {
		
		return foodRepository.findByNameAndRangOfPrice(foodname, start, end);
	}
}
