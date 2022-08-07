package com.tim.dto.excel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExcelField {

	private String excelHeader;
	private int excelIndex;
	private String excelColType;
	private String excelValue;
	private String pojoAttribute;
	private String cellAddress;

}
