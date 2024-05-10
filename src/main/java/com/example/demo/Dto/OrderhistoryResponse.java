package com.example.demo.dto;

import lombok.Data;

@Data
public class OrderhistoryResponse {
	private  long customerId;
	private  int quantity;
	private  int totalPrice;
	private  String foodname;
	private  int foodprice;
	
}
