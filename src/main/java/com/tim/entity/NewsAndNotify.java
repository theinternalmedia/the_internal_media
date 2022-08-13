package com.tim.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 
 * @appName the_internal_media
 *
 */
@MappedSuperclass
@Getter
@Setter
public abstract class NewsAndNotify extends BaseEntity{

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 8440585126556203337L;

	private String thumbnail;

	@Column(nullable = false, length = 150)
	@NotBlank
	@Size(max = 150)
	private String title;

	@Column(nullable = false)
	@NotBlank
	@Size(max = 255)
	private String shortDescription;

	@Column(nullable = false, columnDefinition = "text")
	@NotBlank
	private String content;

}
