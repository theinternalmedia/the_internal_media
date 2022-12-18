package com.tim.dto.marks;

import java.time.LocalDate;

public interface InterfaceBaseMarksDto {
	
	String getName();
	String getUserId();
	LocalDate getDob();
	Float getFinalMarks();
	Integer getTimes();
}
