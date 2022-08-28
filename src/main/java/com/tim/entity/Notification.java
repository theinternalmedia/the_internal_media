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

import lombok.EqualsAndHashCode;

/**
 * 
 * @appName the_internal_media
 *
 */
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "notifications")
public class Notification extends NewsAndNotify {

	private static final long serialVersionUID = -3329374106026587815L;

	@Column(nullable = false, columnDefinition = "integer default 0")
	private int type = TimConstants.NotificationType.TO_ALL;
	
	@OneToMany(mappedBy = "notification")
	private Set<NotificationStudent> notificationStudents = new HashSet<NotificationStudent>();
	
	@OneToMany(mappedBy = "notification")
	private Set<NotificationTeacher> notificationTeachers = new HashSet<NotificationTeacher>();
	
	@ManyToOne
	@JoinColumn(name = "notificationGroup_id")
	private NotificationGroup notificationGroup;
	
	public Set<NotificationStudent> getNotificationStudents() {
		return notificationStudents;
	}

	public void setNotificationStudents(Set<NotificationStudent> notificationStudents) {
		this.notificationStudents = notificationStudents;
	}

	public Set<NotificationTeacher> getNotificationTeachers() {
		return notificationTeachers;
	}

	public void setNotificationTeachers(Set<NotificationTeacher> notificationTeachers) {
		this.notificationTeachers = notificationTeachers;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public NotificationGroup getNotificationGroup() {
		return notificationGroup;
	}

	public void setNotificationGroup(NotificationGroup notificationGroup) {
		this.notificationGroup = notificationGroup;
	}
}
