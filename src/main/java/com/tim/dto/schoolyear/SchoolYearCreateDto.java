package com.tim.dto.schoolyear;

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
public class SchoolYearCreateDto {

	@Size(min =3, max = 50)
	@NotBlank
	@Code
	@ApiModelProperty(value = ApiModelPropertyValue.CODE)
	private String code;

	@Size(max = 50)
	@NotBlank
	private String name;
}
