package com.example.demo.dto;

import lombok.Data;

@Data
public class JwtReq {
	final private String username;
	final private String password;
}
