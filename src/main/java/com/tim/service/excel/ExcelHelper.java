package com.tim.service.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.dto.excel.ExcelField;
import com.tim.dto.student.StudentDto;
import com.tim.dto.teacher.TeacherDto;
import com.tim.exception.TimException;
import com.tim.exception.ValidateException;
import com.tim.utils.MessageUtils;
import com.tim.utils.Utility;

/**
 * ExcelHelper
 * 
 * @appName the_internal_media
 *
 */
@Component
public class ExcelHelper {
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(ExcelHelper.class);

	private static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	private static final String SUFFIX_INPUT_CONFIG_FILE = "_ImportConfig.json";

	private final static SimpleDateFormat dtf = new SimpleDateFormat(TimConstants.USER_DOB_FORMAT);

	private final static String SHEET_NAME = "DanhSach";

	private final static String EXTENSION = ".xlsx";

	private final static String ROOT_FOLDER = "excel";

	private final static String SEPARATOR = File.separator;

	private final static String GET = "get";
	
	/**
	 * @author minhtuanitk43
	 * @param Multipart file in Excel Type
	 * @param className is name of Object
	 * @return List<ExcelField[]> is list Object In ExcelField
	 */
	public <T> List<ExcelField[]> readFromExcel(final MultipartFile file, Class<T> clazz) {

		List<ExcelField[]> excelFieldArrayList = new ArrayList<ExcelField[]>();

		List<String> cellErrs = new ArrayList<String>();
		
		// Get Object Name
		final String className = clazz.getSimpleName();

		// 1.Get workBook from file Excel
		Workbook workBook = readExcel(file);

		// 2.Get Sheet from workBook(1)
		Sheet sheet = workBook.getSheetAt(0);

		// 3.Get List ExcelField Template from Json Config File
		List<ExcelField> excelFieldTemplateList = Utility.getObjectFromJsonFile(
				className + SUFFIX_INPUT_CONFIG_FILE, 
				new TypeReference<List<ExcelField>>() {
				});

		// 4.Total Row of sheet(2)
		int totalRows = sheet.getLastRowNum();

		// 5.Loop list Row in sheet(2) to get ExcelField Arr
		for (int i = 1; i <= totalRows; i++) {
			// 5.1 Get Row
			Row row = sheet.getRow(i);
			ExcelField[] excelFieldArr = new ExcelField[excelFieldTemplateList.size()];
			int index = 0;
			// 5.2 Loop excelFieldTemplateList(3)
			for (ExcelField excelFieldTemp : excelFieldTemplateList) {
				int cellIndex = excelFieldTemp.getExcelIndex();
				String cellType = excelFieldTemp.getExcelColType();

				// 5.3 Get Cell
				Cell cell = row.getCell(cellIndex);
				
				// If Cell's Value is null
//				if(cell == null) {
//					String colString = CellReference.convertNumToColString(cellIndex);
//					cellErrs.add(MessageUtils.get(ETimMessages.NULL_CELL_VALUE, colString + (i + 1)) );
//					continue;
//				}

				// 5.4 Create excelField
				ExcelField excelField = new ExcelField();
				excelField.setExcelColType(excelFieldTemp.getExcelColType());
				excelField.setExcelHeader(excelFieldTemp.getExcelHeader());
				excelField.setExcelIndex(excelFieldTemp.getExcelIndex());
				excelField.setPojoAttribute(excelFieldTemp.getPojoAttribute());
				
				
				// If Cell's Value is null
				if(cell == null) {
					String colString = CellReference.convertNumToColString(cellIndex);
					excelField.setCellAddress(colString + (i + 1));
					excelField.setExcelValue(null);
					excelFieldArr[index++] = excelField;
					continue;
				}else {
					excelField.setCellAddress(cell.getAddress().toString());
				}

				// 5.5 get value from Cell(5.3) and set to excelField(5.4)
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
					case TimConstants.FieldType.FLOAT:
					case TimConstants.FieldType.INTEGER:
						excelField.setExcelValue(String.valueOf(cell.getNumericCellValue()));
						break;
					case TimConstants.FieldType.BOOLEAN:
						if (StringUtils.containsAnyIgnoreCase(cell.getStringCellValue(), 
								TimConstants.Gender.MALE_STR, TimConstants.YES)) {
							excelField.setExcelValue(TimConstants.TRUE_STR);
						} else if (StringUtils.containsAnyIgnoreCase(cell.getStringCellValue(), 
								TimConstants.Gender.FEMALE_STR, TimConstants.NO)) {
							excelField.setExcelValue(TimConstants.FALSE_STR);
						} else {
							excelField.setExcelValue(String.valueOf(cell.getBooleanCellValue()));
						}
						break;
					case TimConstants.FieldType.LOCAL_DATE:
						excelField.setExcelValue(String.valueOf(dtf.format(cell.getDateCellValue())));
						break;
					default:
						break;
					}
				} catch (IllegalStateException e) {
					logger.error(e.getMessage(), e);
					cellErrs.add(MessageUtils.get(ETimMessages.INVALID_CELL_VALUE, cell.getAddress().toString()));
				} catch (NullPointerException e) {
					logger.error(e.getMessage(), e);
					excelField.setExcelValue(null);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					throw new TimException(ETimMessages.INTERNAL_SYSTEM_ERROR);
				}
				excelFieldArr[index++] = excelField;
			}
			excelFieldArrayList.add(excelFieldArr);
		}
		if (cellErrs.size() > 0) {
			throw new ValidateException(ETimMessages.INVALID_EXCEL_VALUE, cellErrs, 
					Utility.getActualObjectName(className));
		}
		return excelFieldArrayList;
	}

	/**
	 * Write list Object to Excel file
	 * 
	 * @author minhtuanitk43, thinh edit (2. CREATE FILE)
	 * @param <T>      Object
	 * @param fileName fileName of excel file
	 * @param data     List Object
	 */
	public <T> String writeToExcel(String fileName, List<T> data) {
		// 1.Get Class of T
		Class<? extends Object> clazz = data.get(0).getClass();
		OutputStream fos = null;
		XSSFWorkbook workbook = null;
		Cell cell;
		String fileDirectory = null; 
		try {
			// 2. CREATE FILE
			// 2.1 Create RootFolder if not exists
			// RootFolder will put in computer desktop
			String userHomeFolder = System.getProperty("user.home");
			File desktopFolder = new File(userHomeFolder, "Desktop");
			//because desktopFolder always exists, so needn't make directory

			// 2.3 Create Folder Excel file
			String directoryName = desktopFolder + SEPARATOR + ROOT_FOLDER;
			File directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
			}

			// 2.4 Create File
			fileDirectory = directoryName
					+ SEPARATOR
					+ SHEET_NAME 
					+ fileName 
					+ EXTENSION; 
			//delete file if already exist
			try {
	            Files.deleteIfExists(Paths.get(fileDirectory));
	        }
	        catch (NoSuchFileException e) {
	            System.out.println("Do nothing"); // "No such file/directory exists"
	        }
			File file = new File(fileDirectory);

			// 3.CREATE EXCEL FILE
			// 3.1 Create Workbook, Sheet
			workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet(SHEET_NAME);
			int rowCount = 0;
			int columnCount = 1;

			// 3.2 Get HeaderFields and ObjectFields
			final Map<String, String> mapFieldNames = Utility.getObjectFromJsonFile(
					TimConstants.FIELDNAMES_EXPORTCONFIG_NAME_FILE,
					new TypeReference<Map<String, Map<String, String>>>() {
					}).get(clazz.getSimpleName());

			// 3.3 Create Header Row
			Row row = sheet.createRow(rowCount++);
			// Write LineNumberHeader to Excel
			cell = row.createCell(0);
			cell.setCellValue(TimConstants.LINE_NUMBER);
			// Write HeaderFields to Excel
			for (String headerKey : mapFieldNames.keySet()) {
				cell = row.createCell(columnCount++);
				cell.setCellValue(mapFieldNames.get(headerKey));
			}
			int num = 1;
			for (T t : data) {
				// 3.4 Write list data to Excel
				// Create row
				row = sheet.createRow(rowCount++);
				columnCount = 1;
				for (String objectField : mapFieldNames.keySet()) {
					// Write line number
					Cell cellFirst = row.createCell(0);
					cellFirst.setCellValue(num);

					// Create Cell
					cell = row.createCell(columnCount);

					// Get value of T
					Method method = null;
					try {
						method = clazz.getMethod(GET + capitalize(objectField));
					} catch (NoSuchMethodException nme) {
						method = clazz.getMethod(GET + objectField);
					}
					Object value = method.invoke(t, (Object[]) null);

					// Write value to Cell
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
						case TimConstants.FieldType.FLOAT:
							cell.setCellValue((Float) value);
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
			return fileDirectory;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			
			//Maybe throw export failed instead
			return "export failed";
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
			throw new ValidateException(ETimMessages.INVALID_EXCEL_FILE, null);
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new ValidateException(ETimMessages.INTERNAL_SYSTEM_ERROR, null);
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
		if (file == null) {
			throw new ValidateException(ETimMessages.VALIDATION_ERR_MESSAGE, 
					List.of(MessageUtils.get(ETimMessages.OBJECT_IS_NULL)));
		}
		if (!TYPE.equals(file.getContentType())) {
			throw new ValidateException(ETimMessages.VALIDATION_ERR_MESSAGE, 
					List.of(MessageUtils.get(ETimMessages.INVALID_EXCEL_FILE)));
		}
		try {
			return file.getInputStream();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			throw new ValidateException(ETimMessages.INVALID_EXCEL_FILE, null);
		}
	}
}
