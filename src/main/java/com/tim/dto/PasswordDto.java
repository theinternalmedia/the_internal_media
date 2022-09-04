package com.tim.dto;

import javax.validation.constraints.NotBlank;

import com.tim.annotation.Password;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;


@Data
public class PasswordDto{
	/**
	 * 
	 */
	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String oldPassword;
	
	@NotBlank
	@Password
	private String newPassword;
}
