package com.example.demo.entity;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Carts")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
 	private long id;
	private long totalPrice;
	
	
	@OneToOne
	@JsonBackReference
	private MyUser myuser;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Item> items = new ArrayList<>();

}
