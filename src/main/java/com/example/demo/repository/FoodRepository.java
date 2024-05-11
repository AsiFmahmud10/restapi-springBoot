package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
    
	@Query(value = "SELECT * FROM FOOD WHERE name like  %:name%",nativeQuery = true)
	List<Food> findByNameLike(String name);
	
	@Query(value = "SELECT * FROM FOOD WHERE name like  %:name% and price between :start and :end",nativeQuery = true)
	List<Food> findByNameAndRangOfPrice(String name,int start,int end);
	

}
