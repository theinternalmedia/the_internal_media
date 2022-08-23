package com.tim.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.StudentConverter;
import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.dto.ResponseDto;
import com.tim.dto.specification.TimSpecification;
import com.tim.dto.student.StudentDto;
import com.tim.dto.student.StudentRequestDto;
import com.tim.dto.student.StudentUpdateRequestDto;
import com.tim.entity.Classz;
import com.tim.entity.Faculty;
import com.tim.entity.SchoolYear;
import com.tim.entity.Student;
import com.tim.repository.ClassRepository;
import com.tim.repository.StudentRepository;
import com.tim.service.StudentService;
import com.tim.service.excel.ExcelService;
import com.tim.utils.Utility;
import com.tim.utils.ValidationUtils;

import java.util.Optional;

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
	public ResponseDto create(StudentRequestDto requestDto) {
		// Validate input
		ValidationUtils.validateObject(requestDto);
		
		Student entity = studentConverter.toEntity(requestDto);
		Classz classz = classRepository.getByCode(requestDto.getClassCode()).orElse(null);
		if(classz == null) {
			return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND, "Lớp Học",
					"Mã Lớp", requestDto.getClassCode()));
		}
		entity.setClassz(classz);
		entity.setPassword(encoder.encode(entity.getPassword()));
		return new ResponseDto(studentConverter.toDto(studentRepository.save(entity) ));
	}

	@Transactional
	@Override
	public ResponseDto create(MultipartFile file) {
		List<StudentDto> dtoList = excelService.getListObjectFromExcelFile(file, StudentDto.class);
		List<Student> entityList = new ArrayList<>();
		for(StudentDto dto : dtoList){
			Student entity = studentConverter.toEntity(dto);
			Classz classz = classRepository.getByCode(dto.getClassCode()).orElse(null);
			if(classz == null) {
				return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND, "Lớp học",
						"Mã Lớp", dto.getClassCode()));
			}
			entity.setClassz(classz);
			entity.setPassword(encoder.encode(entity.getPassword()));
			entityList.add(entity);
		}
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

    @Override
    public ResponseDto update(StudentUpdateRequestDto requestDto) {
    	// Validate input
    	ValidationUtils.validateObject(requestDto);
    	
		Student student = studentRepository.findById(requestDto.getId()).orElse(null);
		if (student != null) {
			student = studentConverter.toEntity(requestDto, student);
			Classz classz = classRepository.getByCode(requestDto.getClassCode()).orElse(null);
			if (classz == null) {
				return new ResponseDto(Utility.getMessage(
						ETimMessages.ENTITY_NOT_FOUND, "Lớp học", "Mã Lớp", requestDto.getClassCode()));
			}
			student.setClassz(classz);
			// If password has changed
			if (StringUtils.isNotBlank(requestDto.getPassword())) {
				student.setPassword(encoder.encode(requestDto.getPassword()));
			}
			student = studentRepository.save(student);
			return new ResponseDto(studentConverter.toDto(student));
		}
		return new ResponseDto(TimConstants.NOT_OK_MESSAGE);
    }

    @Override
    public ResponseDto getOne(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return new ResponseDto(studentConverter.toDto(student.get()));
        }
        return new ResponseDto(Utility.getMessage(
        		ETimMessages.ENTITY_NOT_FOUND,
                "Sinh viên",
                "ID", id.toString()));
    }

    @Override
    public ResponseDto getByUserId(String userId){
        Student student = studentRepository.findByUserId(userId).orElse(null);
        if(student != null){
            return new ResponseDto(studentConverter.toDto(student));
        }
        return new ResponseDto(Utility.getMessage(
        		ETimMessages.ENTITY_NOT_FOUND,
                "Sinh viên",
                "Mã SV", userId));
    }

    @Override
    public ResponseDto getByEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        if(student != null){
            return new ResponseDto(studentConverter.toDto(student));
        }
        return new ResponseDto(Utility.getMessage(
        		ETimMessages.ENTITY_NOT_FOUND,
                "Sinh viên",
                "Email", email));
    }

    @Override
    public ResponseDto toggleStatus(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
        	student.setStatus(!student.getStatus());
            studentRepository.save(student);
            return new ResponseDto();
        }
        return new ResponseDto(TimConstants.NOT_OK_MESSAGE);
    }
}
