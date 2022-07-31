package com.tim.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tim.data.TimConstants;

public class ResponseDto<T extends BaseDto> implements Serializable{
	
	private static final long serialVersionUID = -7573806538377205333L;
	
	private String code = TimConstants.OK_STATUS;
	private String message = TimConstants.OK_MESSAGE;
	private List<T> listData = new ArrayList<T>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getListData() {
		return listData;
	}

	public void setListData(List<T> listData) {
		this.listData = listData;
	}
}
