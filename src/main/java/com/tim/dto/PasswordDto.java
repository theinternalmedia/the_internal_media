package com.tim.dto;

import javax.validation.constraints.NotBlank;

import com.tim.annotation.Password;

import lombok.Data;


@Data
public class PasswordDto{

	@NotBlank
	private String oldPassword;
	
	@NotBlank
	@Password
	private String newPassword;
}
