package com.tim.dto;

import java.io.Serializable;

import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.tim.data.TimConstants;

import lombok.Getter;
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
public class ResponseDto implements Serializable {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -7573806538377205333L;

	@Getter
	private String status = TimConstants.OK_STATUS;
	@Getter
	private String message = TimConstants.OK_MESSAGE;
	@Getter
	@Setter
	private Object data = null;

	/**
	 * If have any problem and cannot get data, response a message to client
	 * @param message
	 */
	public ResponseDto(String message) {
		super();
		this.status = TimConstants.NOT_OK_STATUS;
		if (StringUtils.isEmpty(this.message)) {
			this.message = TimConstants.NOT_OK_MESSAGE;
		} else {
			this.message = message;
		}
	}

	/**
	 * Get data success
	 * @param data content response to client
	 */
	public ResponseDto(Object data) {
		super();
		if (ObjectUtils.isEmpty(data)) {
			this.message = TimConstants.DATA_EMPTY_MESSAGE;
		} else {
			this.data = data;
		}
	}

	/**
	 * Execute success return nothing
	 */
	public ResponseDto() {
		super();
	}
}
