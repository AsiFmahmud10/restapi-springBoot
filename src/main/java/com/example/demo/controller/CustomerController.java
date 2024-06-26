package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import com.example.demo.entity.Item;
import com.example.demo.dto.CartReq;
import com.example.demo.dto.OrderReq;
import com.example.demo.dto.OrderhistoryResponse;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Food;
import com.example.demo.entity.MyUser;
import com.example.demo.entity.Order;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.FoodService;
import com.example.demo.service.MyUserService;
import com.example.demo.service.OrderService;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {
	
	
	private final MyUserService myUserService;
	private final FoodService foodService;
	private final OrderService orderService;

	
	
	@PostMapping("/order/submit")
	public Order submitOrder(@RequestBody List<CartReq> cartReqList,Principal principal) {
		MyUser customer = myUserService.findByUsername(principal.getName());
		
		
		return orderService.submitOrder(customer, cartReqList);
	}
	
	@PostMapping("/order/history")
	public List<Order> orderHistory(Principal principal) {
		MyUser customer = myUserService.findByUsername(principal.getName());
		
		return customer.getOrders();
	}
	
    @GetMapping("food/search/{foodname}")
	public List<Food> searchFood(@PathVariable String foodname) {
       
    	return  foodService.findByNameLike(foodname);
	}
    
    @GetMapping("/food")
   	public List<Food> getAllfood() {
          
       	return  foodService.getAll();
   	}
    
    @GetMapping("food/search/{foodname}/price/{start}/{end}")
   	public List<Food> searchFoodByNameAndPrice(@PathVariable String foodname,@PathVariable int start,@PathVariable int end) {
    	 
       	return  foodService.findByNameAndRangOfPrice(foodname, start, end);
   	}
   
	
	
}
