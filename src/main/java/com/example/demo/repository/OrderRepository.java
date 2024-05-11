package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.OrderhistoryResponse;
import com.example.demo.entity.Item;
import com.example.demo.entity.MyUser;
import com.example.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByCustomer(MyUser customer);
	
}
