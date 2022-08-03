package com.tim.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.TeacherConverter;
import com.tim.dto.teacher.TeacherDto;
import com.tim.repository.TeacherRepository;
import com.tim.service.TeacherService;
import com.tim.service.excel.ExcelService;

@Service
@Validated
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private TeacherConverter teacherConverter;
	
	@Autowired
	private ExcelService excelService;

	@Override
	public long save(@Valid TeacherDto dto) {
		Teacher entity = teacherConverter.toEntity(teacherDto);
        return teacherRepository.save(entity).getId();
	}

	@Override
	public TeacherDto findByUserId(@NotBlank String userId) {
		// TODO Auto-generated method stub
		return teacherConverter.toDto(teacherRepository.findByUserId(userId)
				.orElseThrow(()-> new EntityNotFoundException("Không tìm thấy người dùng với id = " + userId)));
	}

	@Override
	public List<TeacherDto> importExcelFile(MultipartFile file) {
		List<TeacherDto> teachers = excelService.getListObjectFromExcelFile(file, TeacherDto.class);
		return teachers;
	}

}
