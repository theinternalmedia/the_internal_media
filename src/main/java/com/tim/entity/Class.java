package com.tim.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "class")
public class Class extends BaseEntity {

	private static final long serialVersionUID = 4599295413576661341L;

	@Column(nullable = false, length = 50)
	@Size(max = 50)
	private String name;

	@Column(unique = true, nullable = false, length = 20)
	@Size(max = 20)
	private String code;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher adviser;

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

	public Teacher getAdviser() {
		return adviser;
	}

	public void setAdviser(Teacher adviser) {
		this.adviser = adviser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
