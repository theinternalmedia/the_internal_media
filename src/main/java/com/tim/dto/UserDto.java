package com.tim.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.data.ValidationInput;
import com.tim.dto.role.RoleDto;

public class UserDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -551690367785937349L;

	@NotBlank(message = ValidationInput.User.ID_NOTBLANK)
	@Size(max = 20, message = ValidationInput.User.ID_MAX_SIZE)
	private String userId;

	@NotBlank(message = ValidationInput.User.NAME_NOTBLANK)
	@Size(max = 50, message = ValidationInput.User.NAME_NOTBLANK)
	private String name;

	//@NotBlank(message = "{email.notblank}")
	@NotBlank(message = ValidationInput.User.EMAIL_NOTBLANK)
	@Size(max = 50, message = ValidationInput.User.EMAIL_MAX_SIZE)
	@Email(message = ValidationInput.User.EMAIL_SYNTAX)
	private String email;


	@NotBlank(message = ValidationInput.User.PASSWORD_MAX_SIZE)
	@Size(max = 100, message = ValidationInput.User.PASSWORD_MAX_SIZE)
	private String password;

	private boolean gender = true;

	@Size(max = 100, message = ValidationInput.User.ADDRESS_MAX_SIZE)
	private String address;

	@Size(max = 10, message = ValidationInput.User.PHONE_MAX_SIZE)
	private String phone;

	private LocalDate dob;

	@Size(max = 100, message = ValidationInput.User.AVATAR_MAX_SIZE)
	private String avatar;

	private Set<RoleDto> roles = new HashSet<RoleDto>();

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

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
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

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

}
