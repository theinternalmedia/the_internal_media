package com.tim.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "scores")
public class Scores {

	@Id
	@GeneratedValue
	private long id;
	
	@Size(min = 0, max = 10)
	private float finalScores;
	
	@Min(value = 0)
	private int times;
	
	private boolean pass = true;
	
	private String note;
}
