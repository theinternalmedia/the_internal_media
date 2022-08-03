package com.tim.security.services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.tim.dto.BaseDto;
import com.tim.entity.Role;

/**
 * 
 * @appName the_internal_media
 *
 */
public class  UserModel extends BaseDto{

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
	
	private Set<Role> roles = new HashSet<Role>();

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
