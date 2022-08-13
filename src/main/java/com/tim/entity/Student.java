package com.tim.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * 
 * @appName the_internal_media
 *
 */
@Entity
@Table(name = "student")
@Getter
@Setter
public class Student extends User {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 3541331733217756229L;

	@ManyToMany
	@JoinTable(name = "role_student", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "student")
	private Set<NotificationStudent> notificationStudents = new HashSet<NotificationStudent>();

	@ManyToOne
	@JoinColumn(name = "class_id")
	private Class aClass;

}
