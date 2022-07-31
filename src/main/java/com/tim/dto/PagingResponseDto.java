package com.tim.dto;

public class PagingResponseDto<T extends BaseDto> extends ResponseDto<T> {

	private static final long serialVersionUID = -534561158613722144L;

	private long totalItem;
	private long totalPage;
	private long page;
	private long limit;

	public long getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(long totalItem) {
		this.totalItem = totalItem;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}
}
