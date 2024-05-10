package com.example.demo.dto;

import lombok.Data;

@Data
public class OrderReq {
	private int quantity = 0;
	private long foodId;
}
