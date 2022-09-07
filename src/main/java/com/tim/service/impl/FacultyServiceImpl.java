package com.tim.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.converter.FacultyConverter;
import com.tim.dto.faculty.FacultyDto;
import com.tim.dto.faculty.FacultyRequestDto;
import com.tim.entity.Faculty;
import com.tim.entity.Teacher;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.FacultyRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.FacultyService;
import com.tim.utils.ValidationUtils;

@Service
public class FacultyServiceImpl implements FacultyService {
	private static final String FACULTY = "Khoa";
	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private FacultyConverter facultyConverter;
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public FacultyDto create(FacultyRequestDto dto) {
		// Validate input
		ValidationUtils.validateObject(dto);
		
		Faculty entity = facultyConverter.toEntity(dto);
		if (StringUtils.isNotBlank(dto.getHeadOfFacultyUserId())) {
			Teacher teacher = teacherRepository.getByUserId(dto.getHeadOfFacultyUserId()).orElseThrow(
					() -> new TimNotFoundException("Giảng Viên", "Mã GV", dto.getHeadOfFacultyUserId()));
			entity.setHeadOfFaculty(teacher);
		}
		return facultyConverter.toDto(facultyRepository.save(entity));
	}

	@Override
	public long toggleStatus(Set<Long> ids) {
		List<Faculty> faculties = new ArrayList<>();
		Faculty faculty;
		for (Long id : ids) {
			faculty = facultyRepository.findById(id).orElseThrow(
					() -> new TimNotFoundException(FACULTY, "ID", id.toString()));
			faculty.setStatus(!faculty.getStatus());
			faculties.add(faculty);
		}
		facultyRepository.saveAll(faculties);
		return ids.size();
	}
}
