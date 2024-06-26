package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Item;
import com.example.demo.entity.Cart;

import lombok.Data;

@Data
public class OrderDto {
	private Cart cart;
	private List<Item> orders;
}
