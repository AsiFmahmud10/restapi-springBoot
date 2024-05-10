package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Item;

import lombok.Data;

@Data
public class CartReq {
	private long foodId;
	private int quantity;
}




