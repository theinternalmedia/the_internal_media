package com.tim.dto.news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.data.TimConstants.ApiModelPropertyValue;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class NewsCreateDto implements Serializable{
	
	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -484888514160737597L;

	@NotBlank
	@Size(max = 150)
	@ApiModelProperty(value = ApiModelPropertyValue.MAX_LENGTH_150, required = true)
	private String title;

	@NotBlank
	@Size(max = 255)
	@ApiModelProperty(value = ApiModelPropertyValue.MAX_LENGTH_255, required = true)
	private String shortDescription;

	@NotBlank
	private String content;

	private List<String> facultyCodes = new ArrayList<>();
}
