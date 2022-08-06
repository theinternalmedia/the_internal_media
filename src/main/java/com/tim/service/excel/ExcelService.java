package com.tim.service.excel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.dto.excel.ExcelField;
import com.tim.exception.ExcelException;
import com.tim.exception.GlobalExceptionHandler;

@Service
public class ExcelService implements ExcelFileService {
	
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Autowired
	private ExcelHelper excelHelper;

	final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern(TimConstants.USER_DOB_FORMAT);

	@Override
	public <T extends Object> List<T> getListObjectFromExcelFile(final MultipartFile excelFile, Class<T> clazz) {
		List<T> list = new ArrayList<>();
		List<ExcelField[]> excelFields = excelHelper.readFromExcel(excelFile, clazz.getSimpleName());
		for (ExcelField[] exf : excelFields) {
			T t = null;
			try {
				t = clazz.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
				throw new ExcelException(ETimMessages.INTERNAL_SYSTEM_ERROR);
			}
			Class<? extends Object> classz = t.getClass();

			for (int i = 0; i < exf.length; i++) {

				for (Field field : classz.getDeclaredFields()) {
					field.setAccessible(true);

					if (exf[i].getPojoAttribute().equalsIgnoreCase(field.getName())) {

						try {
							switch (exf[i].getExcelColType()) {
							case TimConstants.FieldType.STRING:
								field.set(t, exf[i].getExcelValue());
								break;
							case TimConstants.FieldType.DOUBLE:
								field.set(t, Double.valueOf(exf[i].getExcelValue()));
								break;
							case TimConstants.FieldType.INTEGER:
								field.set(t, Integer.valueOf(exf[i].getExcelValue()).intValue());
								break;
							case TimConstants.FieldType.BOOLEAN:
								field.set(t, Boolean.valueOf(exf[i].getExcelValue()));
								break;
							case TimConstants.FieldType.LOCAL_DATE:
								field.set(t, LocalDate.parse(exf[i].getExcelValue(), dtf));
								break;
							default:
								break;
							}
						} catch (IllegalArgumentException | IllegalAccessException e) {
							logger.error(e.getMessage(), e);
							e.printStackTrace();
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
							e.printStackTrace();
						}
						break;
					}
				}
			}
			list.add(t);
		}
		return list;
	}
	@Override
	public <T> void writeToExcel(String fileName, List<T> data) {
		excelHelper.writeToExcel(fileName, data);
	}

}
