package com.tim.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "subject")
@Setter
@Getter
public class Subject extends BaseEntity {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -2808568424623733508L;

	@Column(unique = true, nullable = false, length = 20)
	@NotBlank
	@Size(max = 20)
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

}
