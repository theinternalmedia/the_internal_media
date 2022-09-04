package com.tim.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 
 * @appName the_internal_media
 *
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "educationProgram_subject", uniqueConstraints = 
	@UniqueConstraint(columnNames = {"educationProgram_id", "subject_id"}))
@Data
public class EducationProgramSubject extends BaseEntity{

	@Getter	(value = AccessLevel.NONE)
	private static final long serialVersionUID = 6632393641477103818L;
	
	@NotNull
	private String semester;
	
	private String note;
	
	@ManyToOne
	@JoinColumn(name = "educationProgram_id")
	private EducationProgram educationProgram;
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;
}
