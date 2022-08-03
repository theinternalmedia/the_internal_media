package com.tim.service.excel;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim.data.ETimMessages;
import com.tim.data.FieldType;
import com.tim.data.TimConstants;
import com.tim.dto.excel.ExcelField;
import com.tim.exception.ExcelException;

@Component
public class ExcelHelper {
	private static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	private static final String SUFFIX_CONFIX_FILE = "_config.json";

	private final static SimpleDateFormat dtf = new SimpleDateFormat(TimConstants.USER_DOB_FORMAT);

	/**
	 * @author minhtuanitk43
	 * @param Multipart file in Excel Type
	 * @param className is name of Object
	 * @return List<ExcelField[]> is list Object In ExcelField
	 */
	public List<ExcelField[]> getListExcelFieldArray(final MultipartFile file, String className) {
		List<ExcelField[]> excelFieldArrayList = new ArrayList<ExcelField[]>();

		// 1.Get workBook from file Excel
		Workbook workBook = readExcel(file);

		// 2.Get Sheet from workBook(1)
		Sheet sheet = workBook.getSheetAt(0);

		// 3.Get List ExcelField Template from Json Config File
		List<ExcelField> excelFieldTemplateList = getExcelFieldsTemplateFromConfigFile(className);

		// 4.Total Row of sheet(2)
		int totalRows = sheet.getLastRowNum();

		// 5.Loop list Row in sheet(2) to get ExcelField Arr
		for (int i = 1; i < totalRows; i++) {
			Row row = sheet.getRow(i);
			ExcelField[] excelFieldArr = new ExcelField[excelFieldTemplateList.size()];
			int k = 0;
			for (ExcelField excelFieldTemplate : excelFieldTemplateList) {
				int cellIndex = excelFieldTemplate.getExcelIndex();
				String cellType = excelFieldTemplate.getExcelColType();
				Cell cell = row.getCell(cellIndex);

				ExcelField excelField = new ExcelField();
				excelField.setExcelColType(excelFieldTemplate.getExcelColType());
				excelField.setExcelHeader(excelFieldTemplate.getExcelHeader());
				excelField.setExcelIndex(excelFieldTemplate.getExcelIndex());
				excelField.setPojoAttribute(excelFieldTemplate.getPojoAttribute());
				try {
					if (FieldType.STRING.getValue().equalsIgnoreCase(cellType)) {
						excelField.setExcelValue(cell.getStringCellValue());
					} else if (FieldType.DOUBLE.getValue().equalsIgnoreCase(cellType)
							|| FieldType.INTEGER.getValue().equalsIgnoreCase(cellType)) {
						excelField.setExcelValue(String.valueOf(cell.getNumericCellValue()));
					} else if (FieldType.BOOLEAN.getValue().equalsIgnoreCase(cellType)) {
						excelField.setExcelValue(String.valueOf(cell.getBooleanCellValue()));
					} else if (DateUtil.isCellDateFormatted(cell)) {
						excelField.setExcelValue(String.valueOf(dtf.format(cell.getDateCellValue())));
					}
				} catch (IllegalStateException e) {
					
					e.printStackTrace();
					System.out.println(new ObjectMapper().convertValue(excelField, String.class));
				}
				excelFieldArr[k++] = excelField;
			}
			excelFieldArrayList.add(excelFieldArr);
		}
		return excelFieldArrayList;
	}

	private List<ExcelField> getExcelFieldsTemplateFromConfigFile(String className) {
		List<ExcelField> jsonMap = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String jsonConfig = new String(Files
					.readAllBytes(Paths.get(ClassLoader.getSystemResource(className + SUFFIX_CONFIX_FILE).toURI())));

			jsonMap = objectMapper.readValue(jsonConfig, new TypeReference<List<ExcelField>>() {
			});
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			throw new ExcelException(ETimMessages.INTERNAL_SYSTEM_ERROR);
		}
		return jsonMap;
	}

	private Workbook readExcel(final MultipartFile file) {
		InputStream excelFile = isExcelFormat(file);
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(excelFile);
			return wb;
		} catch (EncryptedDocumentException e1) {
			e1.printStackTrace();
			throw new ExcelException(ETimMessages.INVALID_EXCEL_FILE);
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new ExcelException(ETimMessages.INTERNAL_SYSTEM_ERROR);
		}

	}

	private InputStream isExcelFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			throw new ExcelException(ETimMessages.INVALID_EXCEL_FILE);
		}
		try {
			return file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExcelException(ETimMessages.INVALID_EXCEL_FILE);
		}
	}
}
