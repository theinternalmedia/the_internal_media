package com.tim.dto.marks;

import java.time.LocalDate;

import lombok.Data;


@Data
public class CustomMarksInClass{

	private String name;
	
	private String userId;
	
	private LocalDate dob;
	
	private Float finalMarks;
	
	private int times;

	public CustomMarksInClass(String name, String userId, LocalDate dob, Float finalMarks, int times) {
		super();
		this.name = name;
		this.userId = userId;
		this.dob = dob;
		this.finalMarks = finalMarks;
		this.times = times;
	}

}
