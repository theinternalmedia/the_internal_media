package com.tim.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.StudentConverter;
import com.tim.data.TimConstants;
import com.tim.dto.ResponseDto;
import com.tim.dto.specification.TimSpecification;
import com.tim.dto.student.StudentDto;
import com.tim.dto.student.StudentRequestDto;
import com.tim.entity.Classz;
import com.tim.entity.Faculty;
import com.tim.entity.SchoolYear;
import com.tim.entity.Student;
import com.tim.repository.ClassRepository;
import com.tim.repository.StudentRepository;
import com.tim.service.StudentService;
import com.tim.service.excel.ExcelService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudentConverter studentConverter;
	@Autowired
	private ExcelService excelService;
	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private PasswordEncoder encoder;

	@Transactional
	@Override
	public ResponseDto insert(StudentRequestDto requestDto) {
		Student entity = studentConverter.toEntity(requestDto);
		Classz classz = classRepository.getByCode(requestDto.getClassCode());
		entity.setClassz(classz);
		entity.setPassword(encoder.encode(entity.getPassword()));
		return new ResponseDto(studentConverter.toDto(studentRepository.save(entity) ));
	}

	@Transactional
	@Override
	public ResponseDto insert(MultipartFile file) {
		List<StudentDto> dtoList = excelService.getListObjectFromExcelFile(file, StudentDto.class);
		List<Student> entityList = new ArrayList<>();
		dtoList.forEach(item -> {
			Student entity = studentConverter.toEntity(item);
			Classz classz = classRepository.getByCode(item.getClassCode());
			entity.setClassz(classz);
			entity.setPassword(encoder.encode(entity.getPassword()));
			entityList.add(entity);
		});
		studentRepository.saveAll(entityList);
		return new ResponseDto();
	}

	@Override
	public List<Student> findByChoolYearAndFacultyAndClass(Set<String> schoolYearCodes, Set<String> facultyCodes,
			Set<String> classCodes) {
		Specification<Student> timSpecification = new TimSpecification<>();
		if (ObjectUtils.isNotEmpty(schoolYearCodes)) {
			timSpecification = timSpecification.and((root, query, builder) -> {
				{
					Join<Classz, Student> studentClass = root.join("classz", JoinType.INNER);
					Join<Classz, SchoolYear> classSchooYear = studentClass.join("schoolYear", JoinType.INNER);
					return builder.in(classSchooYear.get("code")).value(schoolYearCodes);
				}
			});
		}
		if(ObjectUtils.isNotEmpty(facultyCodes)) {
			timSpecification = timSpecification.and((root, query, builder) -> {
				{
					Join<Classz, Student> studentClass = root.join("classz", JoinType.INNER);
					Join<Classz, Faculty> classFaculty = studentClass.join("faculty", JoinType.INNER);
					return builder.in(classFaculty.get("code")).value(facultyCodes);
				}
			});
		}
		if(ObjectUtils.isNotEmpty(classCodes)) {
			timSpecification = timSpecification.and((root, query, builder) -> {
				return builder.in(root.join("classz").get("code")).value(classCodes);
			});
		}
		List<Student> students = studentRepository.findAll(timSpecification);
		return students;
	}

	@Override
	public String exportToExcelFile() {
		List<Student> entityList = studentRepository.findAll();
		List<StudentDto> dtos = studentConverter.toDtoList(entityList);
		excelService.writeListObjectToExcel(TimConstants.ExcelFiledName.STUDENT, dtos);
		return null;
	}
}
