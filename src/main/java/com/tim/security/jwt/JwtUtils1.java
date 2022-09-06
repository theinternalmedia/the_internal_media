package com.tim.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils1 {

	@Value("${tim.jwtSecret}")
	private String jwtSecret;
	
	@Value("${tim.jwtExpirationMs")
	private String jwtExperationMs;
	
	
	public String generateJwtToken(Authentication authentication) {
		
		return null;
	}
	
	public String getUserNameFromJwtTokent(String token) {
		return token;
	}
	
	public String generateTokenFromUserName(String userName) {
		return userName;
	}
	
	public boolean validateJwtToken(String authToken) {
		return false;
	}
}
