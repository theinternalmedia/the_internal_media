package com.tim.dto.feedback;

import com.tim.dto.BaseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class FeedbackDto extends BaseDto {

	private static final long serialVersionUID = 103400058596747572L;
	
	private String userId;
	private String message;
	private String name;
	private String contactNumber;
	private String email;
}
