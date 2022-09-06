package com.tim.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.EqualsAndHashCode;

/**
 * 
 * @appName the_internal_media
 *
 */
@EqualsAndHashCode(callSuper = true, of = {"student", "notification"})
@Entity
@Table(name = "notification_student", uniqueConstraints = 
	@UniqueConstraint(columnNames = { "student_id", "notification_id" }))
public class NotificationStudent extends BaseEntity {

	private static final long serialVersionUID = -4042639355028646141L;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "notification_id")
	private Notification notification;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}
}
