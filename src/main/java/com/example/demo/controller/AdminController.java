package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.JwtService;
import com.example.demo.dto.ItemDetailsResponse;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Food;
import com.example.demo.entity.MyUser;
import com.example.demo.entity.Order;
import com.example.demo.service.CartService;
import com.example.demo.service.FoodService;
import com.example.demo.service.MyUserService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ItemService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

	final private FoodService foodService;
	final private CartService cartService;
	final private OrderService orderService;

	@GetMapping("/item")
	public ItemDetailsResponse getOrderDetails(Principal principal) {
		List<Order> orderList = orderService.getAll();

		return new ItemDetailsResponse(orderList);
	}

	@GetMapping("/food")
	public List<Food> getAllFood() {
		return foodService.findAll();
	}

	@PostMapping("/food")
	public ResponseEntity<Food> saveFood(@RequestBody Food food) {

		return new ResponseEntity<Food>(foodService.save(food), HttpStatus.CREATED);
	}

	

	@PutMapping("food/update/{id}")
	public ResponseEntity<Food> updateFood(@PathVariable("id") long id, @RequestBody Food food) {

		return new ResponseEntity<Food>(foodService.update(id, food), HttpStatus.OK);
	}

}
