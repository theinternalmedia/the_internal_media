package com.tim.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.annotation.Phone;
import com.tim.dto.role.RoleDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -551690367785937349L;


	@NotBlank
	@Size(max = 20, min = 5)
	@Code
	private String userId;

	@NotBlank
	@Size(max = 50, min = 4)
	private String name;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(max = 20, min = 6)
	private String password;

	private boolean gender = true;

	@Size(max = 100)
	private String address;

	@Phone
	private String phone;

	private LocalDate dob;

	private String avatar;

	private String remark;

	private Set<RoleDto> roles = new HashSet<>();
}
