package com.tim.dto;

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
public class PagingResponseDto extends ResponseDto {

	private static final long serialVersionUID = -534561158613722144L;

	private long totalItem;
	private long totalPage;
	private int page;
	private int size;

	/**
	 * Get data success
	 * @param data
	 */
	public PagingResponseDto(long totalItem, long totalPage, int page, int size, Object data) {
		super(data);
		this.totalItem = totalItem;
		this.totalPage = totalPage;
		this.page = page;
		this.size = size;
	}

	/**
	 * Get data success
	 * @param data
	 */
	public PagingResponseDto(Object data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	/**
	 * If have any problem and cannot get data, response a message to client
	 * @param message
	 */
	public PagingResponseDto(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
