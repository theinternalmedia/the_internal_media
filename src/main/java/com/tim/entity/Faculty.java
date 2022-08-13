package com.tim.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * 
 * @appName the_internal_media
 *
 */
@Entity
@Table(name = "faculty")
@Getter
@Setter
public class Faculty extends BaseEntity {

	@Getter(value = AccessLevel.NONE)
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
	private Set<Class> classes = new HashSet<Class>();

	@ManyToMany(mappedBy = "faculties")
	private Set<News> news = new HashSet<>();

	@OneToOne
	private Teacher headOfFaculty;

}
