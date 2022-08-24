package com.tim.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 
 * @appName the_internal_media
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
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
	
	private String thumbnail;
	
	private String slug;
}
