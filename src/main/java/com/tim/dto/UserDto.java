package com.tim.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Phone;
import com.tim.data.ValidationInput;
import com.tim.dto.role.RoleDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto extends BaseDto {
	private static final long serialVersionUID = -551690367785937349L;

	@NotBlank(message = ValidationInput.User.ID_NOTBLANK)
	@Size(max = 20, message = ValidationInput.User.ID_MAX_SIZE)
	private String userId;

	@NotBlank(message = ValidationInput.User.NAME_NOTBLANK)
	@Size(max = 50, message = ValidationInput.User.NAME_NOTBLANK)
	private String name;

	// @NotBlank(message = "{email.notblank}")
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

	@Phone(message = ValidationInput.User.PHONE_MAX_SIZE)
	private String phone;

	private LocalDate dob;

	@Size(max = 100, message = ValidationInput.User.AVATAR_MAX_SIZE)
	private String avatar;

	private Set<RoleDto> roles = new HashSet<RoleDto>();

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

}
