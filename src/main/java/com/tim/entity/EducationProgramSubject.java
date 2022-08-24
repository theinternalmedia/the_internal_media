package com.tim.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.EqualsAndHashCode;

/**
 * 
 * @appName the_internal_media
 *
 */
@EqualsAndHashCode
@Entity
@Table(name = "educationProgram_subject", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"educationProgram_id", "subject_id"}))
public class EducationProgramSubject implements Serializable{

	private static final long serialVersionUID = 6632393641477103818L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "educationProgram_id")
	private EducationProgram educationProgram;
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;
}
