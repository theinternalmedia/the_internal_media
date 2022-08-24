package com.tim.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 
 * @appName the_internal_media
 *
 */
@MappedSuperclass
public abstract class User extends BaseEntity {

	private static final long serialVersionUID = -4780316215584757765L;

	@NotBlank
	@Column(nullable = false, unique = true, length = 20)
	@Size(max = 20)
	private String userId;

	@NotBlank
	@Column(nullable = false)
	@Size(max = 50)
	private String name;

	@NotBlank
	@Column(nullable = true, unique = true)
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Column(nullable = false)
	@Size(max = 100)
	private String password;

	@Column(columnDefinition = "boolean default true")
	private boolean gender = true;

	@Column
	@Size(max = 100)
	private String address;

	@Column
	@Size(max = 10)
	private String phone;

	@Column
	private LocalDate dob;

	@Column
	@Size(max = 100)
	private String avatar;

	@Size(max = 100)
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
