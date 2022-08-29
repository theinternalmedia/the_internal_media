package com.tim.dto.permission;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.dto.BaseDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionDto extends BaseDto{

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -5816877896223951783L;

	@NotBlank
    @Size(max = 50, min = 4)
    private String name;

    @NotBlank
    @Size(max = 50, min = 4)
    @Code
    private String code;
}
