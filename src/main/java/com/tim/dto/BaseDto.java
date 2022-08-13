package com.tim.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @appName the_internal_media
 *
 */
public class BaseDto extends TestClass implements Serializable {

	private static final long serialVersionUID = 7702340293141714083L;

	private Long id;
	private LocalDateTime createdDate = LocalDateTime.now();
	private String createdBy;
	private LocalDateTime modifiedDate = LocalDateTime.now();
	private String modifiedBy;
	private Boolean status = true;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
