package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CartReq;
import com.example.demo.dto.OrderhistoryResponse;
import com.example.demo.entity.MyUser;
import com.example.demo.entity.Order;

public interface OrderService {

	public Order submitOrder(MyUser customer, List<CartReq> cartReq);

	public List<Order> getAll(); 
	public  List<Order> getOrderHistory (MyUser myUser);
}
