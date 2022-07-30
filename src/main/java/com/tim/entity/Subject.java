package com.tim.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "subject")
public class Subject extends BaseEntity{

	private static final long serialVersionUID = -2808568424623733508L;


	@Column(unique = true, nullable = false)
	private String code;
	
	@NotEmpty
	private String name;
	
	@Min(value = 1)
	private int numberOfCredits;

	@Column(nullable = false, columnDefinition = "boolean default true")
	private boolean mandatory = true;
	
	@Size(min = 0, max = 10)
	private float passScores;


}
