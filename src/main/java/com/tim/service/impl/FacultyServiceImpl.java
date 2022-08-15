package com.tim.service.impl;

import com.tim.converter.FacultyConverter;
import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.dto.ResponseDto;
import com.tim.dto.faculty.FacultyDto;
import com.tim.entity.Faculty;
import com.tim.entity.Teacher;
import com.tim.repository.FacultyRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.FacultyService;
import com.tim.utils.Utility;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {
	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private FacultyConverter facultyConverter;
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public ResponseDto insert(FacultyDto dto) {
		Faculty entity = facultyConverter.toEntity(dto);
		if (StringUtils.isNotBlank(dto.getHeadOfFacultyUserId())) {
			Teacher teacher = teacherRepository.getByUserId(dto.getHeadOfFacultyUserId()).orElse(null);
			if (teacher == null) {
				return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND,
						TimConstants.ActualEntityName.TEACHER, "Mã GV", dto.getHeadOfFacultyUserId()));
			}
			entity.setHeadOfFaculty(teacher);
		}
		return new ResponseDto(facultyConverter.toDto(facultyRepository.save(entity)));
	}
}
