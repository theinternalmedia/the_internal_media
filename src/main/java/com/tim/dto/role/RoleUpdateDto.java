package com.tim.dto.role;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RoleUpdateDto extends RoleCreateDto{

	@NotNull
	@Min(value = 1)
	private Long id;
	
}
