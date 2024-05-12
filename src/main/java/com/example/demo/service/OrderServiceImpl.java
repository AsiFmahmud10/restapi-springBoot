package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CartReq;
import com.example.demo.dto.OrderhistoryResponse;
import com.example.demo.entity.Food;
import com.example.demo.entity.Item;
import com.example.demo.entity.MyUser;
import com.example.demo.entity.Order;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderepository;
	private final CustomerRepository customerRepository;
	private final FoodRepository foodRepository;
	
	
	@Override
	public Order submitOrder(MyUser customer, List<CartReq> cartReqList) {
		 
		 List<Item> items = new ArrayList<>();
		 
		 Order order = new Order();
		 order.setCustomer(customer);
		 
		 int orderTotalPrice = 0;
		 
		 for( CartReq cartReq : cartReqList ) {
			 Item item = new Item();
			 Food food = foodRepository.findById(cartReq.getFoodId()).orElseThrow(()->new ResourceNotFoundException("id",cartReq.getFoodId() , "Food"));
			 item.setFoodname(food.getName());
			 item.setFoodUnitPrice(food.getPrice());
			 item.setQuantity(cartReq.getQuantity());
			 item.setTotalPrice(food.getPrice() * cartReq.getQuantity());
			 
			 items.add(item);
			 orderTotalPrice = orderTotalPrice + item.getTotalPrice();
		 }
		 
		 order.setItems(items);
		 order.setTotalPrice(orderTotalPrice);
		 order.setTime(LocalDateTime.now());
		 orderTotalPrice= 0;
		 
		 customer.getOrders().add(order);
		 customerRepository.save(customer);
		 
		 return orderepository.save(order);
	}

	@Override
	public List<Order> getAll() {
		
		return orderepository.findAllByOrderByTimeDesc();
	}
	

	@Override
	public List<Order> findByCustomer(MyUser customer) {
		
		return orderepository.findByCustomer(customer);
	}


	@Override
	public Order getByid(long orderId) {
		
		return orderepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("id", orderId, "Order"));
	}

	@Override
	public void update(Order order) {
		orderepository.save(order);
		
	}
 
}
