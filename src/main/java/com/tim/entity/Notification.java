package com.tim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.data.TimConstants;

/**
 * 
 * @appName the_internal_media
 *
 */
@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3329374106026587815L;

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

	@Column(nullable = false, columnDefinition = "integer default 1")
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

	public int getGroup() {
		return type;
	}

	public void setGroup(int group) {
		this.type = group;
	}
}
