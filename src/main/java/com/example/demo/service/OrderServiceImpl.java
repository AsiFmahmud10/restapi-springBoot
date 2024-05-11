package com.example.demo.service;

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
		 
		 
		 for( CartReq cartReq : cartReqList ) {
			 Item item = new Item();
			 Food food = foodRepository.findById(cartReq.getFoodId()).orElseThrow(()->new ResourceNotFoundException("id",cartReq.getFoodId() , "Food"));
			 item.setFoodname(food.getName());
			 item.setFoodPrice(food.getPrice());
			 item.setQuantity(cartReq.getQuantity());
			 
			 items.add(item);
			 
		 }
		 
		 order.setItems(items);
		 customer.getOrders().add(order);
		 customerRepository.save(customer);
		 
		 return orderepository.save(order);
	}
	
	
	
	
	
	
	

	@Override
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return orderepository.findAll();
	}
	
	public  List<Order> getOrderHistory (MyUser myUser) {
		
		return null;
		
		
//		List<OrderhistoryResponse> orderhistoryResponseList = new ArrayList();
//		
//		 List <Object[]> objList =   orderepository.findOrderHistory(customerId);
//		 
//		 System.out.println(objList);
//		 
//		 for( Object[] row : objList ) {
//			 OrderhistoryResponse orderhistoryResponse = new OrderhistoryResponse();
//			 
//			 System.out.println("----"+ row[0]+" "+ row[1]+" "+row[2]);
//			 orderhistoryResponse.setCustomerId((Long)row[0]);
//			 orderhistoryResponse.setQuantity((int)row[1]);
//			 orderhistoryResponse.setTotalPrice((int)row[2]);
//			 orderhistoryResponse.set((String)row[3]);
//			 orderhistoryResponse.setPrice((int)row[4]);
//			 
//			 orderhistoryResponseList.add(orderhistoryResponse);
//		 
//		 }
//		 return orderhistoryResponseList;
	}








	@Override
	public List<Order> findByCustomer(MyUser customer) {
		// TODO Auto-generated method stub
		return orderepository.findByCustomer(customer);
	}
 
}
