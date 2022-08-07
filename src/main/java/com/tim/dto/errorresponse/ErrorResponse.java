package com.tim.dto.errorresponse;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
	private String message;
	private List<String> errDetails = new ArrayList<String>();
	
}
