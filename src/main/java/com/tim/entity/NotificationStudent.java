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
@Table(name = "notification_student", uniqueConstraints = 
	@UniqueConstraint(columnNames = { "student_id", "notification_id" }))
public class NotificationStudent implements Serializable {

	private static final long serialVersionUID = -4042639355028646141L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "notification_id")
	private Notification notification;
}
