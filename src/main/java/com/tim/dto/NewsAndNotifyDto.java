package com.tim.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.ToString;

/**
 * 
 * @appName the_internal_media
 *
 */
@ToString
@Getter
public class NewsAndNotifyDto extends BaseDto{
	
	private static final long serialVersionUID = -9097269347613338779L;

	@NotBlank
	@Size(max = 150)
	private String title;

	@NotBlank
	@Size(max = 255)
	private String shortDescription;

	@NotBlank
	private String content;
}
