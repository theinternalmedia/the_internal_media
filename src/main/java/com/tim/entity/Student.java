package com.tim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 
 * @appName the_internal_media
 *
 */
@Entity
@Table(name = "student")
public class Student extends User{

	private static final long serialVersionUID = 3541331733217756229L;
	
	@Column(unique = true)
	@Size(max = 20)
	private String classCode;
	
	@ManyToMany
	@JoinTable(name = "role_student", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Teacher adviser;

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Teacher getAdviser() {
		return adviser;
	}

	public void setAdviser(Teacher adviser) {
		this.adviser = adviser;
	}
	
}
