package com.tim.dto.faculty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;
import com.tim.data.TimConstants.ApiModelPropertyValue;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @appName the_internal_media
 *
 */
@Data
@EqualsAndHashCode
public class FacultyCreateDto {

	@Size(max = 50, min = 3)
	@NotBlank
	@Code
	@ApiModelProperty(value = ApiModelPropertyValue.CODE)
	private String code;

	@Size(max = 100)
	@NotBlank
	private String name;
	
	@Code
	private String headOfFacultyUserId;
}
