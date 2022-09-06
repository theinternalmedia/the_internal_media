package com.tim.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter1 extends OncePerRequestFilter{
	@Autowired
	JwtUtils1 jwtUtils1;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String jwt = parseJwt(request);
		
		if(jwt != null && jwtUtils1.validateJwtToken(jwt)) {
			String userName = null;
			UserDetails userDetails = null;
			
			setAuthentication(userDetails, request);
			
		}else {
			final String usersUserId = null;
			final String usersUserPassword = null;
			
			if(StringUtils.hasText(usersUserId) && StringUtils.hasText(usersUserPassword)) {
				
				UserDetails userDetails = null;
				if(userDetails.getPassword().equals(usersUserPassword)) {
					setAuthentication(userDetails, request);
				}
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	

	private void setAuthentication(UserDetails userDetails, HttpServletRequest request) {
		
	}



	private String parseJwt(HttpServletRequest request) {
		return null;
	}

}
