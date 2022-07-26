package com.tim.dto.role;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.dto.BaseDto;
import com.tim.dto.permission.PermissionDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDto extends BaseDto {

    @Getter(value = AccessLevel.NONE)
    private static final long serialVersionUID = 353045816102903559L;

    @NotBlank
    @Size(max = 50, min = 4)
    private String name;

    @NotBlank
    @Size(max = 50, min = 4)
    @Code
    private String code;
    
    private Set<PermissionDto> permissions = new HashSet<PermissionDto>();
}
