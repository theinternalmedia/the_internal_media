package com.tim.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.converter.FacultyConverter;
import com.tim.dto.faculty.FacultyDto;
import com.tim.entity.Faculty;
import com.tim.entity.Teacher;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.FacultyRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService {
	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private FacultyConverter facultyConverter;
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public FacultyDto create(FacultyDto dto) {
		Faculty entity = facultyConverter.toEntity(dto);
		if (StringUtils.isNotBlank(dto.getHeadOfFacultyUserId())) {
			Teacher teacher = teacherRepository.getByUserId(dto.getHeadOfFacultyUserId()).orElseThrow(
					() -> new TimNotFoundException("Giảng Viên", "Mã GV", dto.getHeadOfFacultyUserId()));
			entity.setHeadOfFaculty(teacher);
		}
		return facultyConverter.toDto(facultyRepository.save(entity));
	}
}
