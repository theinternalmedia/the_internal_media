package com.tim.dto.news;

import com.tim.annotation.Code;
import com.tim.dto.PageRequestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsPageRequestDto extends PageRequestDto{

	@ApiModelProperty(value = "search in title, content and shortDescription")
	private String searchKey;
	
	@Code
	private String facultyCode;
}
