package com.tim.service.excel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tim.data.ETimMessages;
import com.tim.data.FieldType;
import com.tim.data.TimConstants;
import com.tim.dto.excel.ExcelField;
import com.tim.exception.ExcelException;

@Service
public class ExcelService implements ExcelFileService {
	@Autowired
	private ExcelHelper excelHelper;

	final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern(TimConstants.USER_DOB_FORMAT);

	@Override
	public <T extends Object> List<T> getListObjectFromExcelFile(final MultipartFile excelFile, Class<T> clazz) {
		List<T> list = new ArrayList<>();
		List<ExcelField[]> excelFields = excelHelper.getListExcelFieldArray(excelFile, clazz.getSimpleName());
		for (ExcelField[] evc : excelFields) {
			T t = null;
			try {
				t = clazz.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
				throw new ExcelException(ETimMessages.INTERNAL_SYSTEM_ERROR);
			}
			Class<? extends Object> classz = t.getClass();

			for (int i = 0; i < evc.length; i++) {

				for (Field field : classz.getDeclaredFields()) {
					field.setAccessible(true);

					if (evc[i].getPojoAttribute().equalsIgnoreCase(field.getName())) {

						try {
							if (FieldType.STRING.getValue().equalsIgnoreCase(evc[i].getExcelColType())) {
								field.set(t, evc[i].getExcelValue());
							} else if (FieldType.DOUBLE.getValue().equalsIgnoreCase(evc[i].getExcelColType())) {
								field.set(t, Double.valueOf(evc[i].getExcelValue()));
							} else if (FieldType.INTEGER.getValue().equalsIgnoreCase(evc[i].getExcelColType())) {
								field.set(t, Double.valueOf(evc[i].getExcelValue()).intValue());
							} else if (FieldType.BOOLEAN.getValue().equalsIgnoreCase(evc[i].getExcelColType())) {
								field.set(t, Boolean.valueOf(evc[i].getExcelValue()));
							} else if (FieldType.LOCALDATE.getValue().equalsIgnoreCase(evc[i].getExcelColType())) {
								field.set(t, LocalDate.parse(evc[i].getExcelValue(), dtf));
							}
						} catch (IllegalArgumentException | IllegalAccessException e) {
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
}
