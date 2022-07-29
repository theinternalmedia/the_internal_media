package com.tim.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "scores")
public class Marks extends BaseEntity{

	private static final long serialVersionUID = -2808568424623733508L;
	
	@Size(min = 0, max = 10)
	private float finalScores;
	
	@Min(value = 0)
	private int times;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean pass = false;
	
	private String note;
}
