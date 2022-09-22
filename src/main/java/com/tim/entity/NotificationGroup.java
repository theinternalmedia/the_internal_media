package com.tim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;

import lombok.EqualsAndHashCode;

/**
 * 
 * @appName the_internal_media
 *
 */
@EqualsAndHashCode(callSuper = true, of = "code")
@Entity
@Table(name = "notification_group")
public class NotificationGroup extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6768709466183693166L;

	@Column(unique = true, nullable = false, length = 50)
	@Size(max = 50)
	@Code
	private String code;

	@Column(unique = true, nullable = false, length = 100)
	@Size(max = 100)
	private String name;
	
	@OneToMany(mappedBy = "notificationGroup")
	private Set<Notification> notifications = new HashSet<Notification>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
