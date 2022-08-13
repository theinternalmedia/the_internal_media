package com.tim.dto;

import java.io.Serializable;

import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.tim.data.TimConstants;

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

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -7573806538377205333L;

	@Getter
	private String status = TimConstants.OK_STATUS;
	@Getter
	private String message = TimConstants.OK_MESSAGE;
	@Getter
	@Setter
	private Object data = null;


	public ResponseDto(String message) {
		super();
		this.status = TimConstants.NOT_OK_STATUS;
		if (StringUtils.isEmpty(this.message)) {
			this.message = TimConstants.NOT_OK_MESSAGE;
		} else {
			this.message = message;
		}
	}

	public ResponseDto(Object data) {
		super();
		if (ObjectUtils.isEmpty(data)) {
			this.status = TimConstants.NOT_OK_STATUS;
			this.message = TimConstants.NOT_OK_MESSAGE;
		} else {
			this.data = data;
		}
	}
}
