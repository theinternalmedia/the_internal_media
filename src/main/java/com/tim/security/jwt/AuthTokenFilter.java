package com.tim.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tim.security.services.UserDetailsServiceImpl;

/**
 * 
 * @appName the_internal_media
 *
 */
public class AuthTokenFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	private static final String USERS_USER_NAME = "userId";
	private static final String USERS_PASSWORD = "password";

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			final String jwt = parseJwt(request);
			// Has jwtToken
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				String username = jwtUtils.getUsernameFromJwtToken(jwt);
				UserDetails userDetail = userDetailsService.loadUserByUsername(username);
				// setAuthentication
				setAuthentication(userDetail, request);
			// Has'nt jwtToken
			} else {
				// Get userId and password from header
				final String usersUserId = request.getHeader(USERS_USER_NAME);
				final String usersUserPassword = request.getHeader(USERS_PASSWORD);
				
				// Present userId and password in header
				if (StringUtils.hasText(usersUserPassword) && StringUtils.hasText(usersUserId)) {
					logger.info("Authenticate user:  {}, apiPath: {}", usersUserId, request.getRequestURI());
					UserDetails userDetail = userDetailsService.loadUserByUsername(usersUserId);
					if (userDetail.getPassword().equals(usersUserPassword)) {
						// setAuthentication
						setAuthentication(userDetail, request);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}

		filterChain.doFilter(request, response);

	}

	/**
	 * SetAuthentication
	 * 
	 * @author minhtuanitk43
	 * @param userDetail
	 * @param request
	 */
	private void setAuthentication(UserDetails userDetail, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(
						userDetail, 
						null,
						userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		logger.info("Authenticate successfully user:  {}, apiPath: {}", userDetail.getUsername(),
				request.getRequestURI());

	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}

}
