package com.tim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @appName the_internal_media
 *
 */
@EqualsAndHashCode(callSuper = true, of = "code")
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {

	private static final long serialVersionUID = 7850391100035043803L;

	@Column(unique = true, nullable = false, length = 50)
	@Size(max = 50)
	@Code
	private String code;

	@Column(unique = true, nullable = false, length = 100)
	@Size(max = 100)
	private String name;

	@ManyToMany(mappedBy = "roles")
	private Set<Teacher> teachers = new HashSet<>();

	@ManyToMany(mappedBy = "roles")
	private Set<Student> students = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "role_permission", 
		joinColumns = @JoinColumn(name = "role_id"), 
		inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private Set<Permission> permissions = new HashSet<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@PreRemove
	public void preRemove() {
		for (Student s : students) {
			s.getRoles().remove(this);
		}
		for (Teacher t : teachers) {
			t.getRoles().remove(this);
		}
		for (Permission p : permissions) {
			p.getRoles().remove(this);
		}
	}
}
