package com.tim.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * PagingResponseDto
 * 
 * @appName the_internal_media
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
public class PagingResponseDto {

	@Getter(value = AccessLevel.NONE)
	private static final long serialVersionUID = -534561158613722144L;

	private long totalItem;
	private long totalPage;
	private int page;
	private int size;
	private Object data = null;

}
