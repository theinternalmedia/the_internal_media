package com.tim.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;

/**
 * 
 * @appName the_internal_media
 *
 */
@MappedSuperclass
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public abstract class NewsAndNotify extends BaseEntity{

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
	
	private String slug;
	
	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
