package com.tim.restful;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.ETimMessages;
import com.tim.data.TimApiPath;
import com.tim.entity.Permission;
import com.tim.entity.RefreshToken;
import com.tim.entity.Role;
import com.tim.entity.Student;
import com.tim.entity.Teacher;
import com.tim.exception.CustomException;
import com.tim.payload.request.LoginRequest;
import com.tim.payload.request.TokenRefreshRequest;
import com.tim.payload.response.JwtResponse;
import com.tim.payload.response.TokenRefreshResponse;
import com.tim.repository.StudentRepository;
import com.tim.repository.TeacherRepository;
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
	public AuthenticationManager authenticationManager;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TeacherRepository teacherRepository;
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
		Set<Role> setRole = new HashSet<Role>();
		List<String> permissions = new ArrayList<>();

		Student student = studentRepository.findByUserId(userDetails.getUsername()).orElse(null);
		if (student != null) {
			setRole = student.getRoles();
		} else {
			Teacher teacher = teacherRepository.findByUserId(userDetails.getUsername()).orElse(null);
			setRole = teacher.getRoles();
		}
		for (Role role : setRole) {
			for (Permission p : role.getPermissions()) {
				permissions.add(p.getCode().toString());
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
			throw new CustomException(ETimMessages.INVALID_TOKEN, request.getRefreshToken());
		}
		refreshTokenService.verifyExpiration(refreshToken);
		String token = jwtUtils.generateTokenFromUsername(usersUserId);
	  	return ResponseEntity.ok(new TokenRefreshResponse(token, request.getRefreshToken()));
	  }
}