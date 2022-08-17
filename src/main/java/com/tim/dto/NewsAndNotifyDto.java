package com.tim.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @appName the_internal_media
 *
 */
@ToString
@Getter
@Setter
public class NewsAndNotifyDto extends BaseDto{
	
	@Getter(value = AccessLevel.NONE)
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
