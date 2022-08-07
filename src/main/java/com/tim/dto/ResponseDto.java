package com.tim.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tim.data.TimConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto to response to Client through RestController
 * 
 * @appName the_internal_media
 *
 * @param <T> Dto is must extends BaseDto
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T extends BaseDto> implements Serializable{
	
	private static final long serialVersionUID = -7573806538377205333L;
	
	private String status = TimConstants.OK_STATUS;
	private String message = TimConstants.OK_MESSAGE;
	private List<T> listData = new ArrayList<T>();

}
