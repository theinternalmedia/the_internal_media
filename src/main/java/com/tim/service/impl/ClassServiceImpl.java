package com.tim.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.converter.ClassConverter;
import com.tim.data.ETimMessages;
import com.tim.dto.classz.ClassDto;
import com.tim.dto.classz.ClassRequestDto;
import com.tim.entity.Classz;
import com.tim.entity.Faculty;
import com.tim.entity.SchoolYear;
import com.tim.entity.Teacher;
import com.tim.exception.TimException;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.ClassRepository;
import com.tim.repository.FacultyRepository;
import com.tim.repository.SchoolYearRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.ClassService;

@Service
public class ClassServiceImpl implements ClassService {
	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private ClassConverter classConverter;
	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private SchoolYearRepository schoolYearRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Override
	public ClassDto create(ClassRequestDto requestDto) {
		Classz entity = classConverter.toEntity(requestDto);
		if(StringUtils.isNotBlank(requestDto.getFacultyCode())) {
			Faculty faculty = facultyRepository.getByCode(requestDto.getFacultyCode()).orElse(null);
			if(faculty == null) {
				throw new TimNotFoundException("Khoa", "Mã Khoa", requestDto.getFacultyCode());
			}
			entity.setFaculty(faculty);
		}
		if(StringUtils.isNotBlank(requestDto.getSchoolYearCode())) {
			SchoolYear schoolYear = schoolYearRepository.getByCode(
					requestDto.getSchoolYearCode()).orElse(null);
			if(schoolYear == null) {
				throw new TimException(ETimMessages.ENTITY_NOT_FOUND,
						"Khóa", "Mã Khóa", requestDto.getSchoolYearCode());
			}
			entity.setSchoolYear(schoolYear);
		}
		if(StringUtils.isNotBlank(requestDto.getAdvisorId())) {
			Teacher teacher = teacherRepository.getByUserId(requestDto.getAdvisorId()).orElse(null);
			if(teacher == null) {
				throw new TimNotFoundException("Giảng Viên", "Mã GV", requestDto.getAdvisorId());
			}
			entity.setAdviser(teacher);
		}
		entity = classRepository.save(entity);
		return classConverter.toDto(entity);
	}
}