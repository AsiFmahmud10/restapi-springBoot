package com.example.demo.dto;

import com.example.demo.entity.MyUser;

import lombok.Data;

@Data
public class JwtResponse  {
	public final String token;
}
