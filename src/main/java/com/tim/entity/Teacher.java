package com.tim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @appName the_internal_media
 *
 */
@Entity
@Table(name = "teacher")
public class Teacher extends User {

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
	
	@ManyToOne
	private Faculty faculty;
	
	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Set<NotificationTeacher> getNotificationTeachers() {
		return notificationTeachers;
	}

	public void setNotificationTeachers(Set<NotificationTeacher> notificationTeachers) {
		this.notificationTeachers = notificationTeachers;
	}

	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

	public boolean isHeadOfFaculty() {
		return isHeadOfFaculty;
	}

	public void setHeadOfFaculty(boolean isHeadOfFaculty) {
		this.isHeadOfFaculty = isHeadOfFaculty;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
