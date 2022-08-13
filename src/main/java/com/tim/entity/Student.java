package com.tim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @appName the_internal_media
 *
 */
@Entity
@Table(name = "student")
public class Student extends User {

	private static final long serialVersionUID = 3541331733217756229L;

	@ManyToMany
	@JoinTable(name = "role_student", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "student")
	private Set<NotificationStudent> notificationStudents = new HashSet<NotificationStudent>();

	@ManyToOne
	@JoinColumn(name = "class_id")
	private Class myClass;
	
	public Class getMyClass() {
		return myClass;
	}

	public void setMyClass(Class myClass) {
		this.myClass = myClass;
	}

	public Set<NotificationStudent> getNotificationStudents() {
		return notificationStudents;
	}

	public void setNotificationStudents(Set<NotificationStudent> notificationStudents) {
		this.notificationStudents = notificationStudents;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
