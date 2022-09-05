package com.tim.dto.educationprogramsubject;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class EducationProgramSubjectUpdateDto implements Serializable{

	/**
	 * 
	 */
	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = 1342629685422604083L;

	@NotNull
	@Min(value = 1)
	private Long id;

	private String note;

	@NotNull
	private String semester;
}
