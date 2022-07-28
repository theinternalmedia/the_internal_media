package com.tim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher extends User {

	/**
	 * thinhnguyen
	 */
	private static final long serialVersionUID = 2186238218422351720L;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isHeadOfFaculty = false;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isManager = false;
	
	@ManyToMany
	@JoinTable(name = "role_teacher", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	private Set<Student> students = new HashSet<>();


	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
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
