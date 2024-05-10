package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Cart;
import com.example.demo.entity.MyUser;
import com.example.demo.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ItemDetailsResponse {
	
	
	private final List<Order> orderList;
	
	

}
