package com.tim.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "subject")
public class Subject extends BaseEntity {

	private static final long serialVersionUID = -2808568424623733508L;

	@Column(unique = true, nullable = false, length = 20)
	@NotBlank
	@Size(max = 20)
	@Code
	private String code;

	@Column(unique = true, nullable = false, length = 20)
	@NotBlank
	@Size(max = 50)
	private String name;

	@Column(nullable = false)
	@Min(value = 1)
	private int numberOfCredits = 1;

	@Column(nullable = false, columnDefinition = "boolean default true")
	private boolean mandatory = true;

	@Column(nullable = false)
	@Size(min = 0, max = 10)
	private float passMarks;

	@OneToMany(mappedBy = "subject")
	private Set<EducationProgramSubject> educationProgramSubjects = new HashSet<>();

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

	public int getNumberOfCredits() {
		return numberOfCredits;
	}

	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public float getPassMarks() {
		return passMarks;
	}

	public void setPassMarks(float passMarks) {
		this.passMarks = passMarks;
	}
}
