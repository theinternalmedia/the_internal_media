package com.tim.service.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tim.data.ETimMessages;
import com.tim.data.FieldType;
import com.tim.data.TimConstants;
import com.tim.dto.excel.ExcelField;
import com.tim.exception.ExcelException;
import com.tim.exception.GlobalExceptionHandler;

@Component
public class ExcelHelper {
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

	private static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	private static final String SUFFIX_INPUT_CONFIG_FILE = "_inputConfig.json";

	private static final String SUFFIX_OUTPUT_CONFIG_FILE = "_outputConfig.json";

	private final static SimpleDateFormat dtf = new SimpleDateFormat(TimConstants.USER_DOB_FORMAT);
	private final static SimpleDateFormat dtf_from_object = new SimpleDateFormat("yyyy-MM-dd");

	private final static String EXCEL_EXPORT_NAME = "DanhSach";

	/**
	 * @author minhtuanitk43
	 * @param Multipart file in Excel Type
	 * @param className is name of Object
	 * @return List<ExcelField[]> is list Object In ExcelField
	 */
	public List<ExcelField[]> getListExcelFieldArrFromExcelFile(final MultipartFile file, String className) {

		List<ExcelField[]> excelFieldArrayList = new ArrayList<ExcelField[]>();

		// 1.Get workBook from file Excel
		Workbook workBook = readExcel(file);

		// 2.Get Sheet from workBook(1)
		Sheet sheet = workBook.getSheetAt(0);

		// 3.Get List ExcelField Template from Json Config File
		List<ExcelField> excelFieldTemplateList = getExcelFieldsTemplateFromConfigFile(className,
				SUFFIX_INPUT_CONFIG_FILE);

		// 4.Total Row of sheet(2)
		int totalRows = sheet.getLastRowNum();

		// 5.Loop list Row in sheet(2) to get ExcelField Arr
		for (int i = 1; i < totalRows; i++) {
			Row row = sheet.getRow(i);
			ExcelField[] excelFieldArr = new ExcelField[excelFieldTemplateList.size() - 1];
			for (int j = 1; j < excelFieldTemplateList.size(); j++) {
				int cellIndex = excelFieldTemplateList.get(j).getExcelIndex();
				String cellType = excelFieldTemplateList.get(j).getExcelColType();
				Cell cell = row.getCell(cellIndex);

				ExcelField excelField = new ExcelField();
				excelField.setExcelColType(excelFieldTemplateList.get(j).getExcelColType());
				excelField.setExcelHeader(excelFieldTemplateList.get(j).getExcelHeader());
				excelField.setExcelIndex(excelFieldTemplateList.get(j).getExcelIndex());
				excelField.setPojoAttribute(excelFieldTemplateList.get(j).getPojoAttribute());
				try {
					if (FieldType.STRING.getValue().equalsIgnoreCase(cellType)) {
						try {
							excelField.setExcelValue(cell.getStringCellValue());
						} catch (IllegalStateException e) {
							excelField.setExcelValue(String.valueOf(cell.getNumericCellValue()));
						}
					} else if (FieldType.DOUBLE.getValue().equalsIgnoreCase(cellType)
							|| FieldType.INTEGER.getValue().equalsIgnoreCase(cellType)) {
						excelField.setExcelValue(String.valueOf(cell.getNumericCellValue()));
					} else if (FieldType.BOOLEAN.getValue().equalsIgnoreCase(cellType)) {
						excelField.setExcelValue(String.valueOf(cell.getBooleanCellValue()));
					} else if (DateUtil.isCellDateFormatted(cell)) {
						excelField.setExcelValue(String.valueOf(dtf.format(cell.getDateCellValue())));
					}
				} catch (IllegalStateException e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}
				excelFieldArr[j - 1] = excelField;
			}
			excelFieldArrayList.add(excelFieldArr);
		}
		return excelFieldArrayList;
	}

	public List<ExcelField[]> getListExcelFieldArrFromListObject(List<Object> list) {
		return null;
	}
	public <T> void writeToExcel1(String fileName, List<T> data) {
		OutputStream fos = null;
		XSSFWorkbook workbook = null;
		try {
			File file = new File(fileName);
			workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet();
			List<String> fieldNames = getFieldNamesForClass(data.get(0).getClass());
			int rowCount = 0;
			int columnCount = 0;
			Row row = sheet.createRow(rowCount++);
			for (String fieldName : fieldNames) {
				Cell cell = row.createCell(columnCount++);
				cell.setCellValue(fieldName);
			}
			Class<? extends Object> classz = data.get(0).getClass();
			for (T t : data) {
				row = sheet.createRow(rowCount++);
				columnCount = 0;
				for (String fieldName : fieldNames) {
					Cell cell = row.createCell(columnCount);
					Method method = null;
					try {
						method = classz.getMethod("get" + capitalize(fieldName));
					} catch (NoSuchMethodException nme) {
						method = classz.getMethod("get" + fieldName);
					}
					Object value = method.invoke(t, (Object[]) null);
					if (value != null) {
						if (value instanceof String) {
							cell.setCellValue((String) value);
						} else if (value instanceof Long) {
							cell.setCellValue((Long) value);
						} else if (value instanceof Integer) {
							cell.setCellValue((Integer) value);
						} else if (value instanceof Double) {
							cell.setCellValue((Double) value);
						}
					}
					columnCount++;
				}
			}
			fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
			}
			try {
				if (workbook != null) {
					workbook.close();
				}
			} catch (IOException e) {
			}
		}
	}

	public <T> void writeToExcel(String fileName, List<T> data) {
		Class<? extends Object> clazz = data.get(0).getClass();
		OutputStream fos = null;
		XSSFWorkbook workbook = null;
		try {
			List<ExcelField> excelFieldTemplateList = getExcelFieldsTemplateFromConfigFile(clazz.getSimpleName(),
					SUFFIX_OUTPUT_CONFIG_FILE);
			File file = new File(fileName);
			workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet(EXCEL_EXPORT_NAME);
			int rowCount = 0;
			int columnCount = 0;
			Row row = sheet.createRow(rowCount++);

			excelFieldTemplateList = excelFieldTemplateList.stream()
					.sorted(Comparator.comparing(ExcelField::getExcelIndex)).collect(Collectors.toList());

			for (ExcelField excelField : excelFieldTemplateList) {
				Cell cell = row.createCell(columnCount++);
				cell.setCellValue(excelField.getExcelHeader());
			}
			excelFieldTemplateList.remove(0);
			int stt = 1;
			for (T t : data) {
				row = sheet.createRow(rowCount++);
				columnCount = 1;
				for (ExcelField excelField : excelFieldTemplateList) {
					
					Cell cellFirst = row.createCell(0);
					cellFirst.setCellValue(stt);
					
					Cell cell = row.createCell(columnCount);
					Method method = null;
					try {
						method = clazz.getMethod("get" + capitalize(excelField.getPojoAttribute()));
					} catch (NoSuchMethodException nme) {
						method = clazz.getMethod("get" + excelField.getPojoAttribute());
					}
					Object value = method.invoke(t, (Object[]) null);
					if (value != null) {
						if (value instanceof String) {
							cell.setCellValue((String) value);
						} else if (value instanceof Long) {
							cell.setCellValue((Long) value);
						} else if (value instanceof Integer) {
							cell.setCellValue((Integer) value);
						} else if (value instanceof Double) {
							cell.setCellValue((Double) value);
						} else if (value instanceof LocalDate) {
							cell.setCellValue(dtf_from_object.parse(value.toString()).toString());
						}
					}
					columnCount++;
				}
				stt++;
			}
			fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
			}
			try {
				if (workbook != null) {
					workbook.close();
				}
			} catch (IOException e) {
			}
		}
	}

	// retrieve field names from a POJO class
	private List<String> getFieldNamesForClass(Class<?> clazz) throws Exception {
		List<String> fieldNames = new ArrayList<String>();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fieldNames.add(fields[i].getName());
		}
		return fieldNames;
	}

	// capitalize the first letter of the field name for retriving value of the
	// field later
	private static String capitalize(String s) {
		if (s.length() == 0)
			return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	//

	private List<ExcelField> getExcelFieldsTemplateFromConfigFile(String className, String suffixConfigFile) {
		List<ExcelField> jsonMap = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String jsonConfig = new String(
					Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(className + suffixConfigFile).toURI())));

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
