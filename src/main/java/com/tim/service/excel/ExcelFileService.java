package com.tim.service.excel;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelFileService {
	<T> List<?> getListObjectFromExcelFile(MultipartFile file, Class<T> clazz);
	<T> String writeListObjectToExcel(String fileName, List<T> data);
}
