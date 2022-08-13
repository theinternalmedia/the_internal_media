package com.tim.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "class")
@Getter
@Setter
public class Class extends BaseEntity {

	@Getter(AccessLevel.NONE)
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

	@OneToMany(mappedBy = "aClass")
	private Set<Student> students = new HashSet<>();

}
