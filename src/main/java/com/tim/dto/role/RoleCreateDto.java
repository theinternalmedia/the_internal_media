package com.tim.dto.role;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;

import lombok.Data;


@Data
public class RoleCreateDto {

	@NotBlank
	@Size(max = 50, min = 4)
	@Code
	private String code;
	
	@NotBlank
	@Size(max = 100)
	private String name;
	
	private Set<String> permissions;
}
