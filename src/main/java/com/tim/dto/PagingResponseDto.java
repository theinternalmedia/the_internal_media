package com.tim.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PagingResponseDto
 * 
 * @appName the_internal_media
 *
 * @param <T>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagingResponseDto<T extends BaseDto> extends ResponseDto<T> {

	private static final long serialVersionUID = -534561158613722144L;

	private long totalItem;
	private long totalPage;
	private long page;
	private long limit;
}
