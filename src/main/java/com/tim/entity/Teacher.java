package com.tim.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @appName the_internal_media
 *
 */
@Entity
@Table(name = "teacher")
@Getter
@Setter
public class Teacher extends User {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 2186238218422351720L;

	@Column(columnDefinition = "boolean default false")
	private boolean isHeadOfFaculty = false;

	@Column(columnDefinition = "boolean default false")
	private boolean isManager = false;

	@ManyToMany
	@JoinTable(name = "role_teacher", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "adviser")
	private Set<Class> classes = new HashSet<Class>();;
	
	@OneToMany(mappedBy = "teacher")
	private Set<NotificationTeacher> notificationTeachers = new HashSet<NotificationTeacher>();

}
