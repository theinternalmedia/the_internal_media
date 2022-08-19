package com.tim.service.impl;

import com.tim.converter.ClassConverter;
import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.dto.ResponseDto;
import com.tim.dto.classz.ClassDto;
import com.tim.entity.Classz;
import com.tim.entity.Faculty;
import com.tim.entity.SchoolYear;
import com.tim.entity.Teacher;
import com.tim.repository.ClassRepository;
import com.tim.repository.FacultyRepository;
import com.tim.repository.SchoolYearRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.ClassService;
import com.tim.utils.Utility;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public ResponseDto create(ClassDto classDto) {
		Classz entity = classConverter.toEntity(classDto);
		if(StringUtils.isNotBlank(classDto.getFacultyCode())) {
			Faculty faculty = facultyRepository.getByCode(classDto.getFacultyCode()).orElse(null);
			if(faculty == null) {
				return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND,
						TimConstants.ActualEntityName.FACULTY, "Mã Khoa", classDto.getFacultyCode()));
			}
			entity.setFaculty(faculty);
		}
		if(StringUtils.isNotBlank(classDto.getSchoolYearCode())) {
			SchoolYear schoolYear = schoolYearRepository.getByCode(
					classDto.getSchoolYearCode()).orElse(null);
			if(schoolYear == null) {
				return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND,
						TimConstants.ActualEntityName.SCHOOL_YEAR, "Mã Khóa", classDto.getSchoolYearCode()));
			}
			entity.setSchoolYear(schoolYear);
		}
		if(StringUtils.isNotBlank(classDto.getAdvisorId())) {
			Teacher teacher = teacherRepository.getByUserId(classDto.getAdvisorId()).orElse(null);
			if(teacher == null) {
				return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND,
						TimConstants.ActualEntityName.TEACHER, "Mã GV", classDto.getAdvisorId()));
			}
			entity.setAdviser(teacher);
		}
		entity = classRepository.save(entity);
		return new ResponseDto(classConverter.toDto(entity));
	}
}