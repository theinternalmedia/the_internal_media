package com.tim.restful;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.ETimMessages;
import com.tim.data.TimApiPath;
import com.tim.entity.RefreshToken;
import com.tim.exception.TimException;
import com.tim.payload.request.LoginRequest;
import com.tim.payload.request.TokenRefreshRequest;
import com.tim.payload.response.JwtResponse;
import com.tim.payload.response.TokenRefreshResponse;
import com.tim.security.jwt.JwtUtils;
import com.tim.security.services.UserDetailsImpl;
import com.tim.service.RefreshTokenService;

/**
 * 
 * @appName the_internal_media
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TimApiPath.TIM_API)
public class AuthResource {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private RefreshTokenService refreshTokenService;

	/**
	 * @author minhtuanitk43
	 * @param loginRequest
	 * @return JwtResponse include accessToken and refreshAccessToken
	 */
	@PostMapping(TimApiPath.Auth.LOGIN)
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generaJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getRoles();
		List<String> permissions = new ArrayList<>();
		for(GrantedAuthority permission : userDetails.getAuthorities()) {
			if (!permission.getAuthority().startsWith("ROLE_")) {
				permissions.add(permission.getAuthority());
			}
		}
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());
		return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), 
				userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), roles, userDetails.getName(), 
				userDetails.getAvatar(), permissions));
	}
	
	/**
	 * @author minhtuanitk43
	 * @param request
	 * @return TokenRefreshResponse include new accessToken
	 */
	@PostMapping(TimApiPath.Auth.REFRESH_TOKEN)
	  public ResponseEntity<TokenRefreshResponse> refreshtoken(TokenRefreshRequest request) {
		final String usersUserId = request.getUsersUserId();
		RefreshToken refreshToken = refreshTokenService
				.findByTokenAndUserId(request.getRefreshToken(), usersUserId).orElse(null);
		if (refreshToken == null) {
			throw new TimException(ETimMessages.INVALID_TOKEN, request.getRefreshToken());
		}
		refreshTokenService.verifyExpiration(refreshToken);
		String token = jwtUtils.generateTokenFromUsername(usersUserId);
	  	return ResponseEntity.ok(new TokenRefreshResponse(token, request.getRefreshToken()));
	  }
}