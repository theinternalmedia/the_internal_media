package com.tim.dto.role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.dto.BaseDto;

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
    private String code;
}
