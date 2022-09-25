package com.tim.dto.marks;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tim.annotation.Code;

import lombok.Data;

@Data
public class MarksCreateDto {

	@Min(0)
	@Max(10)
	private float finalMarks;

	@Size(max = 100)
	private String note;
	
	@NotNull
	private LocalDate date;
	
	private String time;
	
	@NotBlank
	@Code
	private String subjectCode;
	
	@NotBlank
	@Code
	private String studentUserId;
	
	@NotBlank
	@Code
	private String teacherUserId;
}
