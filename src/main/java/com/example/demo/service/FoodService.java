package com.example.demo.service;
import java.util.List;
import com.example.demo.entity.Food;

public interface FoodService {
	List<Food> findAll();

	Food save(Food food);

	Food getByid(long id);

	void delete(long id);

	Food update(long id, Food food);

	List<Food> findByNameLike(String foodname);

	List<Food> getAll();

	List<Food> findByNameAndRangOfPrice(String foodname, int start, int end);

	
}
