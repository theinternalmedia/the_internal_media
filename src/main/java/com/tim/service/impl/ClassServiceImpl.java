package com.tim.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	
	private static final String CLASSZ = "Lớp Học";
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

		// Mapping Faculty
		Faculty faculty = facultyRepository.findByCodeAndStatusTrue(requestDto.getFacultyCode())
				.orElseThrow(() -> new TimNotFoundException(
						"Khoa", "Mã Khoa", requestDto.getFacultyCode()));
		entity.setFaculty(faculty);
		
		// Mapping School Year
		SchoolYear schoolYear = schoolYearRepository.findByCodeAndStatusTrue(
				requestDto.getSchoolYearCode())
				.orElseThrow(() -> new TimException(
						ETimMessages.ENTITY_NOT_FOUND,"Khóa", "Mã Khóa", 
						requestDto.getSchoolYearCode()));
		entity.setSchoolYear(schoolYear);
		
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
	
	@Override
	public long toggleStatus(Set<Long> ids) {
		List<Classz> classzs = new ArrayList<>();
		Classz classz;
		for (Long id : ids) {
			classz = classRepository.findById(id).orElseThrow(
					() -> new TimNotFoundException(CLASSZ, "ID", id.toString()));
			classz.setStatus(!classz.getStatus());
			classzs.add(classz);
		}
		classRepository.saveAll(classzs);
		return ids.size();
	}
}