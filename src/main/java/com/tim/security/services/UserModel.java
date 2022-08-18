package com.tim.security.services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.tim.dto.BaseDto;
import com.tim.entity.Role;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @appName the_internal_media
 *
 */
@Getter
@Setter
@ToString
public class  UserModel extends BaseDto{

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 6600808339524713688L;

	private String userId;

	private String name;

	private String email;

	private String password;

	private Boolean gender = true;
	
	private String address;
	
	private String phone;
	
	private LocalDate dob;
	
	private String avatar;
	
	private boolean isTeacher;
	
	private Set<Role> roles = new HashSet<Role>();

}
