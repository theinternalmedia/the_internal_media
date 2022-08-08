package com.tim.dto;

import lombok.Builder;
import lombok.Getter;
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
@Builder
public class PagingResponseDto extends ResponseDto {

	private static final long serialVersionUID = -534561158613722144L;

	private long totalItem;
	private long totalPage;
	private long page;
	private long limit;
}
