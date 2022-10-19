package com.tim.dto;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class FeedbackDto extends BaseDto {
	private String userId;
	private String message;
	private String name;
	private String contactNumber;
	private String email;
}
