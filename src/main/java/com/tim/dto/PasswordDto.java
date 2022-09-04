package com.tim.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Password;

import lombok.Data;


@Data
public class PasswordDto{

	@NotBlank
	private String oldPassword;
	
	@NotBlank
	@Password
	@Size(min = 6, max = 20)
	private String newPassword;
}
