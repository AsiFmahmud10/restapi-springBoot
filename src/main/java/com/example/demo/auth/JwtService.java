package com.example.demo.auth;


import java.security.Key;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {
	final static String SECRET = "13130E695E4B842705A7913CA4A4D2252E1F8C4DCE0EA9CCA291B0AABA2BEF69";
	
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 24))
				.signWith(getSigningKey())
				.compact();
				
		
	}
	
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}
	
	public boolean validToken(String token, UserDetails userDetails)  {
		if(extractUsername(token).equals(userDetails.getUsername()) && !extractExpiration(token).before(new Date()) ) {
			return true;
		}
		return false;
	}
	
	private Claims extractAllClaims(String token) {
		
	return Jwts.parserBuilder()
		.setSigningKey(getSigningKey())
		.build()
		.parseClaimsJws(token)
		.getBody();
		
		
	}

	private Key getSigningKey() {
		// TODO Auto-generated method stub
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
	}
	
	
}
