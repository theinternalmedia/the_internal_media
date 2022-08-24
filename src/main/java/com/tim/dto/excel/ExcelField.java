package com.tim.dto.excel;

import lombok.Data;

@Data
public class ExcelField {

	private String excelHeader;
	private int excelIndex;
	private String excelColType;
	private String excelValue;
	private String pojoAttribute;
	private String cellAddress;

}
