package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Data
@Entity(name = "orders")
public class Order {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private long id;
 
 
 @ManyToOne()
 @JsonManagedReference
 private  MyUser customer;
 
 @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
 private  List<Item> items;
}
