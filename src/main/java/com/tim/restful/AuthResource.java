package com.tim.restful;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.data.ApiPath;
import com.tim.entity.Permission;
import com.tim.entity.Role;
import com.tim.entity.Student;
import com.tim.entity.Teacher;
import com.tim.payload.request.LoginRequest;
import com.tim.payload.response.JwtResponse;
import com.tim.repository.PermissionRepository;
import com.tim.repository.RoleRepository;
import com.tim.repository.StudentRepository;
import com.tim.repository.TeacherRepository;
import com.tim.security.jwt.JwtUtils;
import com.tim.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiPath.Auth.PREFIX)
public class AuthResource extends AbstractResource {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	PermissionRepository permissionRepository;

	@PostMapping(ApiPath.Auth.LOGIN)
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
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), roles, userDetails.getName(), userDetails.getAvatar(), permissions));
	}
}