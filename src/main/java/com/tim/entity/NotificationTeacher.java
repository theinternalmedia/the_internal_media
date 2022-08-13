package com.tim.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 
 * @appName the_internal_media
 *
 */
@Entity
@Table(name = "notification_teacher", uniqueConstraints = 
	@UniqueConstraint(columnNames = { "teacher_id", "notification_id" }))
@Getter
@Setter
public class NotificationTeacher extends BaseEntity {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -6004676980630477079L;


	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@ManyToOne
	@JoinColumn(name = "notification_id")
	private Notification notification;

}
