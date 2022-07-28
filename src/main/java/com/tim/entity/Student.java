package com.tim.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student extends User{

	/**
	 * thinhnguyen
	 */
	private static final long serialVersionUID = 3541331733217756229L;
	
	@ManyToMany
	@JoinTable(name = "role_student", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	private Teacher adviser;


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
