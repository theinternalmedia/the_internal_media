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
@Table(name = "notification_student", uniqueConstraints = 
	@UniqueConstraint(columnNames = { "student_id", "notification_id" }))
@Getter
@Setter
public class NotificationStudent extends BaseEntity{

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -4042639355028646141L;


	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "notification_id")
	private Notification notification;

}
