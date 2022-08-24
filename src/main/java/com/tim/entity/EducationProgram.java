package com.tim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;

/**
 * 
 * @appName the_internal_media
 *
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "educationProgram", uniqueConstraints = 
	@UniqueConstraint(columnNames = { "schoolYear_id", "faculty_id" }))
public class EducationProgram extends BaseEntity {

	private static final long serialVersionUID = -3739672079388550708L;

	@Column(unique = true, nullable = false, length = 50)
	@Size(max = 50)
	private String code;

	@Column(unique = true, nullable = false, length = 50)
	@Size(max = 50)
	private String name;

	@OneToMany(mappedBy = "educationProgram")
	private Set<EducationProgramSubject> educationProgramSubjects = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "schoolYear_id")
	private SchoolYear schoolYear;

	@ManyToOne
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public SchoolYear getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
	}

	public Set<EducationProgramSubject> getEducationProgramSubjects() {
		return educationProgramSubjects;
	}

	public void setEducationProgramSubjects(Set<EducationProgramSubject> educationProgramSubjects) {
		this.educationProgramSubjects = educationProgramSubjects;
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
