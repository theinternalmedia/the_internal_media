package com.tim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 
 * @appName the_internal_media
 *
 */
@Entity
@Table(name = "faculty")
public class Faculty extends BaseEntity {

	private static final long serialVersionUID = -1231129795415461931L;

	@Column(unique = true, nullable = false, length = 50)
	@Size(max = 50)
	private String code;

	@Column(unique = true, nullable = false, length = 50)
	@Size(max = 50)
	private String name;

	@OneToMany(mappedBy = "schoolYear")
	private Set<EducationProgram> educationPrograms = new HashSet<>();

	@OneToMany(mappedBy = "faculty")
	private Set<Classz> classes = new HashSet<Classz>();

	@OneToOne
	private Teacher headOfFaculty;

	@OneToMany(mappedBy = "faculty")
	private Set<Teacher> teachers = new HashSet<>();
	
	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Teacher getHeadOfFaculty() {
		return headOfFaculty;
	}

	public void setHeadOfFaculty(Teacher headOfFaculty) {
		this.headOfFaculty = headOfFaculty;
	}

	public Set<Classz> getClasses() {
		return classes;
	}

	public void setClasses(Set<Classz> classes) {
		this.classes = classes;
	}

	public Set<EducationProgram> getEducationPrograms() {
		return educationPrograms;
	}

	public void setEducationPrograms(Set<EducationProgram> educationPrograms) {
		this.educationPrograms = educationPrograms;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
