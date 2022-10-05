package com.tim.dto.marks;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomMarks {

	private Float finalMarks;

	private Boolean pass;

	private String semester;
	
	private String name;

	public CustomMarks(Float finalMarks, Boolean pass, String semester, String name) {
		super();
		this.finalMarks = finalMarks;
		this.pass = pass;
		this.semester = semester;
		this.name = name;
	}
	
}
