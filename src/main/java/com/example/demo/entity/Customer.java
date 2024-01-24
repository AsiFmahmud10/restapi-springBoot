package com.example.demo.entity;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Data
@Entity
public class Customer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String address;
	private String phone;
	
	@OneToOne
	private Cart cart; 
	
	@OneToMany(mappedBy = "customer")                                  
	private List<Order> orders; 
	
}
