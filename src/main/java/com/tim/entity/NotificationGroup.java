package com.tim.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 
 * @appName the_internal_media
 *
 */
@Entity
@Table(name = "notification_group")
@Getter
@Setter
public class NotificationGroup extends BaseEntity {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 6768709466183693166L;

	@Column(unique = true, nullable = false, length = 50)
	@Size(max = 50)
	private String code;

	@Column(unique = true, nullable = false, length = 50)
	@Size(max = 50)
	private String name;
	
	@OneToMany(mappedBy = "notificationGroup")
	private Set<Notification> notifications = new HashSet<Notification>();

}
