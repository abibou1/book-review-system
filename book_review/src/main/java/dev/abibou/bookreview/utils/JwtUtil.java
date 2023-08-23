package dev.abibou.bookreview.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
	
	private String SECRET_KEY = "mysecretkey";
	
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		
		return createToken(claims, username);
	}
	
	public String createToken(Map<String, Object> claims, String subject) {
		long currentTime = System.currentTimeMillis();
		
		return Jwts.builder().setHeaderParam("type", "JWT")
				.setIssuer("Me Inc.")
				.setSubject(subject)
				.setIssuedAt(new Date(currentTime))
				.setExpiration(new Date(currentTime+ 1000 * 60 * 30))
				.addClaims(claims)
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
		
	}

}