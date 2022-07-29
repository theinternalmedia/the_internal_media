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
public class Subject {

	@Id
	private long id;
	
	@NotEmpty
	@Column(unique = true)
	private String code;
	
	@NotEmpty
	private String name;
	
	@Min(value = 1)
	private int numberOfCredits;
	
	private boolean mandatory = true;
	
	@Size(min = 0, max = 10)
	private float passScores;


}
