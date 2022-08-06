package com.tim.service.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
import com.tim.data.TimConstants;
import com.tim.dto.excel.ExcelField;
import com.tim.dto.student.StudentDto;
import com.tim.dto.teacher.TeacherDto;
import com.tim.exception.ExcelException;
import com.tim.exception.GlobalExceptionHandler;

/**
 * ExcelHelper
 * 
 * @appName the_internal_media
 *
 */
@Component
public class ExcelHelper {
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

	private static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	private static final String SUFFIX_INPUT_CONFIG_FILE = "_ImportConfig.json";

	private final static SimpleDateFormat dtf = new SimpleDateFormat(TimConstants.USER_DOB_FORMAT);

	private final static String SHEET_NAME = "DanhSach";

	private final static String EXTENSION = ".xlsx";

	private final static String ROOT_FOLDER = "excel";

	private final static String SEPARATOR = "/";

	private final static String GET = "get";

	/**
	 * @author minhtuanitk43
	 * @param Multipart file in Excel Type
	 * @param className is name of Object
	 * @return List<ExcelField[]> is list Object In ExcelField
	 */
	public List<ExcelField[]> readFromExcel(final MultipartFile file, String className) {

		List<ExcelField[]> excelFieldArrayList = new ArrayList<ExcelField[]>();

		// 1.Get workBook from file Excel
		Workbook workBook = readExcel(file);

		// 2.Get Sheet from workBook(1)
		Sheet sheet = workBook.getSheetAt(0);

		// 3.Get List ExcelField Template from Json Config File
		List<ExcelField> excelFieldTemplateList = getExcelFieldsFromConfigFile(className);

		// 4.Total Row of sheet(2)
		int totalRows = sheet.getLastRowNum();

		// 5.Loop list Row in sheet(2) to get ExcelField Arr
		for (int i = 1; i < totalRows; i++) {
			Row row = sheet.getRow(i);
			ExcelField[] excelFieldArr = new ExcelField[excelFieldTemplateList.size()];
			int index = 0;
			for (ExcelField excelFieldTemp : excelFieldTemplateList) {
				int cellIndex = excelFieldTemp.getExcelIndex();
				String cellType = excelFieldTemp.getExcelColType();
				Cell cell = row.getCell(cellIndex);

				ExcelField excelField = new ExcelField();
				excelField.setExcelColType(excelFieldTemp.getExcelColType());
				excelField.setExcelHeader(excelFieldTemp.getExcelHeader());
				excelField.setExcelIndex(excelFieldTemp.getExcelIndex());
				excelField.setPojoAttribute(excelFieldTemp.getPojoAttribute());
				try {
					switch (cellType) {
					case TimConstants.FieldType.STRING:
						try {
							excelField.setExcelValue(cell.getStringCellValue());
						} catch (IllegalStateException e) {
							excelField.setExcelValue(String.valueOf(cell.getNumericCellValue()));
						}
						break;
					case TimConstants.FieldType.DOUBLE:
					case TimConstants.FieldType.INTEGER:
						excelField.setExcelValue(String.valueOf(cell.getNumericCellValue()));
						break;
					case TimConstants.FieldType.BOOLEAN:
						if (cell.getStringCellValue().equalsIgnoreCase(TimConstants.Gender.MALE_STR)) {
							excelField.setExcelValue(TimConstants.TRUE_STR);
						} else {
							excelField.setExcelValue(TimConstants.FALSE_STR);
						}
						break;
					case TimConstants.FieldType.LOCAL_DATE:
						if (DateUtil.isCellDateFormatted(cell)) {
							excelField.setExcelValue(String.valueOf(dtf.format(cell.getDateCellValue())));
						}
						break;
					default:
						break;
					}
				} catch (IllegalStateException e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
				excelFieldArr[index++] = excelField;
			}
			excelFieldArrayList.add(excelFieldArr);
		}
		return excelFieldArrayList;
	}

	/**
	 * Write list Object to Excel file
	 * 
	 * @author minhtuanitk43
	 * @param <T>      Object
	 * @param fileName fileName of excel file
	 * @param data     List Object
	 */
	public <T> void writeToExcel(String fileName, List<T> data) {
		// Get Class of T
		Class<? extends Object> clazz = data.get(0).getClass();
		OutputStream fos = null;
		XSSFWorkbook workbook = null;
		Cell cell;
		try {
			// Create RootFolder if not exists
			File rootFolder = new File(ROOT_FOLDER);
			if (!rootFolder.exists()) {
				rootFolder.mkdir();
			}

			// Create Folder Excel file
			String directoryName = ROOT_FOLDER + SEPARATOR + LocalDate.now().toString();
			File directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
			}

			// Create File
			File file = new File(
					directoryName + SEPARATOR + LocalTime.now().toString().substring(0, 8).replace(":", "-") + "_"
							+ SHEET_NAME + fileName + EXTENSION);

			// CREATE EXCEL FILE
			workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet(SHEET_NAME);
			int rowCount = 0;
			int columnCount = 1;
			Row row = sheet.createRow(rowCount++);

			// Get HeaderFields and ObjectFields
			String[] headerFields = TimConstants.HeaderFields.TEACHER;
			String[] objectFields = TimConstants.ObjectFields.TEACHER;

			// Write LineNumberHeader to Excel
			cell = row.createCell(0);
			cell.setCellValue(TimConstants.HeaderFields.LINE_NUMBER);

			// Write HeaderFields to Excel
			for (String headerField : headerFields) {
				cell = row.createCell(columnCount++);
				cell.setCellValue(headerField);
			}
			int num = 1;
			for (T t : data) {
				// Create Row
				row = sheet.createRow(rowCount++);
				columnCount = 1;
				for (String objectField : objectFields) {
					// Set line number
					Cell cellFirst = row.createCell(0);
					cellFirst.setCellValue(num);

					// Create Cell
					cell = row.createCell(columnCount);

					// Get Value of T
					Method method = null;
					try {
						method = clazz.getMethod(GET + capitalize(objectField));
					} catch (NoSuchMethodException nme) {
						method = clazz.getMethod(GET + objectField);
					}
					Object value = method.invoke(t, (Object[]) null);

					// Set Value to Cell
					if (value != null) {
						switch (value.getClass().getSimpleName()) {
						case TimConstants.FieldType.STRING:
							cell.setCellValue((String) value);
							break;
						case TimConstants.FieldType.LONG:
							cell.setCellValue((Long) value);
							break;
						case TimConstants.FieldType.INTEGER:
							cell.setCellValue((Integer) value);
							break;
						case TimConstants.FieldType.DOUBLE:
							cell.setCellValue((Double) value);
							break;
						case TimConstants.FieldType.LOCAL_DATE:
							DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(TimConstants.USER_DOB_FORMAT);
							cell.setCellValue(((LocalDate) value).format(formatter1).toString());
							break;
						case TimConstants.FieldType.LOCAL_DATE_TIME:
							DateTimeFormatter formatter2 = DateTimeFormatter
									.ofPattern(TimConstants.LOCAL_DATE_TIME_FORMAT);
							cell.setCellValue(((LocalDateTime) value).format(formatter2).toString());
							break;
						case TimConstants.FieldType.BOOLEAN:
							if (t instanceof TeacherDto || t instanceof StudentDto) {
								Boolean valueBl = (Boolean) value;
								cell.setCellValue(
										valueBl ? TimConstants.Gender.MALE_STR : TimConstants.Gender.FEMALE_STR);
							} else {
								cell.setCellValue(value.toString());
							}
							break;
						default:
							break;
						}
					}
					columnCount++;
				}
				num++;
			}
			fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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

	/**
	 * Get Capitalize First Character FieldName
	 * 
	 * @author minhtuanitk43
	 * @param s
	 * @return
	 */
	private static String capitalize(String s) {
		if (s.length() == 0)
			return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * Get list ExcelField from config file
	 * 
	 * @author minhtuanitk43
	 * @param className
	 * @return
	 */
	private List<ExcelField> getExcelFieldsFromConfigFile(String className) {
		List<ExcelField> jsonMap = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String jsonConfig = new String(Files.readAllBytes(
					Paths.get(ClassLoader.getSystemResource(className + SUFFIX_INPUT_CONFIG_FILE).toURI())));

			jsonMap = objectMapper.readValue(jsonConfig, new TypeReference<List<ExcelField>>() {
			});
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			throw new ExcelException(ETimMessages.INTERNAL_SYSTEM_ERROR);
		}
		return jsonMap;
	}

	/**
	 * Get Workbook From Excel file
	 * 
	 * @author minhtuanitk43
	 * @param file
	 * @return
	 */
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

	/**
	 * Check is Excel file
	 * 
	 * @author minhtuanitk43
	 * @param file
	 * @return
	 */
	private InputStream isExcelFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			throw new ExcelException(ETimMessages.INVALID_EXCEL_FILE);
		}
		try {
			return file.getInputStream();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			throw new ExcelException(ETimMessages.INVALID_EXCEL_FILE);
		}
	}
}
