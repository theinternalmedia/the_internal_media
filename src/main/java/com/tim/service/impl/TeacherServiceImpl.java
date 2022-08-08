package com.tim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.TeacherConverter;
import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.dto.ResponseDto;
import com.tim.dto.teacher.TeacherDto;
import com.tim.entity.Teacher;
import com.tim.repository.TeacherRepository;
import com.tim.service.TeacherService;
import com.tim.service.excel.ExcelService;
import com.tim.utils.Utility;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private TeacherConverter teacherConverter;
	
	@Autowired
	private ExcelService excelService;

	@Override
	public ResponseDto save(TeacherDto dto) {
		Teacher entity = teacherConverter.toEntity(dto);
        return new ResponseDto(teacherConverter.toDto(teacherRepository.save(entity)));
	}

	@Override
	public ResponseDto findByUserId(String userId) {
		Teacher entity = teacherRepository.findByUserId(userId).orElse(null);
		if(entity == null) {
			return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND, 
					TimConstants.ActualEntityName.TEACHER, "Mã GV", userId));
		}
		return new ResponseDto(teacherConverter.toDto(entity));
	}

	@Override
	public List<TeacherDto> importExcelFile(MultipartFile file) {
		List<TeacherDto> teachers = excelService.getListObjectFromExcelFile(file, TeacherDto.class);
		return teachers;
	}

	@Override
	public void exportExcelFile(String fileName, List<TeacherDto> teacherDtos) {
		excelService.writeListObjectToExcel(fileName, teacherDtos);
	}

}
