package com.tim.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public abstract class User extends BaseEntity {

	@Getter(value = AccessLevel.NONE)
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

	@Column(columnDefinition = "boolean default false")
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

	private String remark;

}
