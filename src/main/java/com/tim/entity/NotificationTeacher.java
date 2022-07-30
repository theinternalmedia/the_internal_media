package com.tim.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @appName the_internal_media
 *
 */
@Entity
@Table(name = "notification_teacher", uniqueConstraints = 
	@UniqueConstraint(columnNames = { "teacher_id", "notification_id" }))
public class NotificationTeacher implements Serializable {

	private static final long serialVersionUID = -6004676980630477079L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@ManyToOne
	@JoinColumn(name = "notification_id")
	private Notification notification;
}
