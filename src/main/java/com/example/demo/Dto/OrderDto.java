package com.example.demo.Dto;

import java.util.List;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Order;

import lombok.Data;

@Data
public class OrderDto {
	private Order order;
	private List<Cart> carts;
}
