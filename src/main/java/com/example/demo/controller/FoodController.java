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

import com.example.demo.entity.Food;
import com.example.demo.service.FoodService;

@RestController
@RequestMapping("api/v1/foods")
public class FoodController {
	@Autowired
	FoodService foodService;
	
	@GetMapping()
	public List<Food> get() {
		return foodService.findAll(); 
	}
	
	@PostMapping()
	public ResponseEntity<Food> save(@RequestBody Food food) {
		return new ResponseEntity<Food>( foodService.save(food),HttpStatus.CREATED);  
	}

	@GetMapping("/{id}")
	public Food getByid(@PathVariable("id") long id) {
		System.out.print(id);
		return foodService.getByid(id);
	}

	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable("id") long id) {
		foodService.delete(id);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Food> update(@PathVariable("id") long id,@RequestBody Food food) {
		
		return new ResponseEntity<Food>(foodService.update(id, food) ,HttpStatus.OK);
	}
}
