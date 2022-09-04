package com.tim.dto.marks;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.tim.dto.BaseDto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 
 * @appName the_internal_media
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MarksDto extends BaseDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -2099206156759347473L;

	@Size(min = 0, max = 10)
	private float finalMarks;

	@Min(value = 1)
	private int times = 1;

	private boolean pass = false;

	@Size(max = 50)
	private String note;
	
	private LocalDate date;
	
	private String time;

}
