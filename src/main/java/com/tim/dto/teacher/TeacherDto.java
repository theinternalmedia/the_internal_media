package com.tim.dto.teacher;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.data.ValidationInput;
import com.tim.dto.BaseDto;
import com.tim.dto.role.RoleDto;

public class TeacherDto extends BaseDto {

	/**
	 * thinhnguyen
	 */

	private static final long serialVersionUID = 3200306173932990958L;

	private boolean isHeadOfFaculty = false;

	private boolean isManager = false;
	
	@NotBlank(message = ValidationInput.Teacher.ID_NOTBLANK)
	@Size(max = 20, message = ValidationInput.Teacher.ID_MAXSIZE)
	private String userId;

	@NotBlank
	@Size(max = 50)
	private String name;

	@NotBlank(message = "{email.notblank}")
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 100)
	private String password;

	private boolean gender = true;

	@Size(max = 100)
	private String address;

	@Size(max = 10)
	private String phone;

	private LocalDate dob;

	@Size(max = 100)
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

	public boolean getGender() {
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

	public boolean isHeadOfFaculty() {
		return isHeadOfFaculty;
	}

	public void setHeadOfFaculty(boolean headOfFaculty) {
		isHeadOfFaculty = headOfFaculty;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean manager) {
		isManager = manager;
	}
}
