package com.tim.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.tim.data.TimConstants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto to response to Client through RestController
 * 
 * @appName the_internal_media
 *
 */
@ToString
@Getter
@NoArgsConstructor
public class ResponseDto implements Serializable {

	private static final long serialVersionUID = -7573806538377205333L;

	@Getter
	private String status = TimConstants.OK_STATUS;
	@Getter
	private String message = TimConstants.OK_MESSAGE;
	@Getter
	@Setter
	private Object data = null;

	public ResponseDto(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
		if (!StringUtils.equalsIgnoreCase(message, message)) {
			this.status = TimConstants.UNEXPECTED_STATUS;
		}
	}

	public ResponseDto(Object data) {
		super();
		this.data = data;
	}
}
