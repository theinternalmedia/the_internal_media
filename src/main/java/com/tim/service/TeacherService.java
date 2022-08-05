package com.tim.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.tim.dto.teacher.TeacherDto;

public interface TeacherService {
	long save(@Valid TeacherDto dto);
	TeacherDto findByUserId(@NotBlank String userId);
	List<TeacherDto> importExcelFile(MultipartFile file);
	void exportExcelFile(String fileName, List<TeacherDto> teacherDtos);
}
