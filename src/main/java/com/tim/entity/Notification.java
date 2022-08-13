package com.tim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tim.data.TimConstants;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @appName the_internal_media
 *
 */
@Entity
@Table(name = "notifications")
@Getter
@Setter
public class Notification extends NewsAndNotify {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -3329374106026587815L;

	@Column(nullable = false, columnDefinition = "integer default 1")
	private int type = TimConstants.NotificationType.TO_ALL;
	
	@OneToMany(mappedBy = "notification")
	private Set<NotificationStudent> notificationStudents = new HashSet<NotificationStudent>();
	
	@OneToMany(mappedBy = "notification")
	private Set<NotificationTeacher> notificationTeachers = new HashSet<NotificationTeacher>();
	
	@ManyToOne
	@JoinColumn(name = "notificationGroup_id")
	private NotificationGroup notificationGroup;

}
