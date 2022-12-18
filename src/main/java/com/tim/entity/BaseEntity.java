package com.tim.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.EqualsAndHashCode;

/**
 * 
 * @appName the_internal_media
 *
 */
@MappedSuperclass
@EqualsAndHashCode(exclude = {"createdDate", "modifiedDate"})
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -2808568424623733508L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, columnDefinition = "boolean default true")
	private Boolean status = true;

	@Column(name = "created_date", nullable = false, updatable = false)
//	@CreatedDate
	private LocalDateTime createdDate;

	@CreatedBy
	private String createdBy;

	@Column(nullable = false, updatable = true)
//	@LastModifiedDate
	private LocalDateTime modifiedDate;

	@LastModifiedBy
	private String modifiedBy;

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
	
	@PrePersist
	private void setCreatedDate() {
		this.createdDate = LocalDateTime.now(ZoneOffset.ofHours(+7));
		this.modifiedDate = LocalDateTime.now(ZoneOffset.ofHours(+7));
	}
	
	@PreUpdate
	private void setUpdatedDate() {
		this.modifiedDate = LocalDateTime.now(ZoneOffset.ofHours(+7));
	}
}