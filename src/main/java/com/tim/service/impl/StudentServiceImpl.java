package com.tim.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tim.converter.StudentConverter;
import com.tim.data.ETimMessages;
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
import com.tim.utils.ImageFileUploadUtil;
import com.tim.utils.Utility;
import com.tim.utils.ValidationUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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


	@Override
	public ResponseDto upload(String studentDtoJsonRequest, MultipartFile image) {
		StudentRequestDto studentRequestDto = Utility.convertStringJsonToObject(studentDtoJsonRequest,
											new TypeReference<StudentRequestDto>(){} );
		ValidationUtils.validateObject(studentRequestDto);
		ValidationUtils.validateImage(image);

//		if(studentRequestDto.getClassCode())

		Student student = studentConverter.toEntity(studentRequestDto);
		String fileName = ImageFileUploadUtil.createFileName(image, TimConstants.Upload.USER_PREFIX);
		try{
			ImageFileUploadUtil.saveFile(TimConstants.Upload.USER_UPLOAD_DIR, fileName, image);
		}catch(IOException e){
			return new ResponseDto(TimConstants.Upload.SAVE_UNSUCCESS);
		}
		String userAvatarImage = TimConstants.Upload.USER_PATH + fileName;
		student.setAvatar(userAvatarImage);
		StudentDto savedStudent = studentConverter.toDto(studentRepository.save(student));
		return new ResponseDto(savedStudent);
	}

	@Transactional
	@Override
	public ResponseDto create(StudentRequestDto requestDto) {
		// Validate input
		ValidationUtils.validateObject(requestDto);
		
		Student entity = studentConverter.toEntity(requestDto);
		Classz classz = classRepository.getByCode(requestDto.getClassCode());
		entity.setClassz(classz);
		entity.setPassword(encoder.encode(entity.getPassword()));
		return new ResponseDto(studentConverter.toDto(studentRepository.save(entity) ));
	}

	@Transactional
	@Override
	public ResponseDto create(MultipartFile file) {
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

    @Override
    public ResponseDto update(StudentDto dto) {
        Student student = studentConverter.toEntity(dto);
        Long studentId = dto.getId();
        if(studentId != null){
            Student oldStudent = studentRepository.getOneById(studentId);
            oldStudent.setClassz(student.getClassz());
            oldStudent.setRoles(student.getRoles());
            oldStudent.setPhone(student.getPhone());
            oldStudent.setGender(student.getGender());
            oldStudent.setAddress(student.getAddress());
            return new ResponseDto(studentConverter.toDto(studentRepository.save(oldStudent)));
        }
        return new ResponseDto(TimConstants.NOT_OK_MESSAGE);
    }

    @Override
    public ResponseDto getOne(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return new ResponseDto(studentConverter.toDto(student.get()));
        }
        return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND,
                                TimConstants.ActualEntityName.STUDENT,
                                "Id", id.toString()));
    }

    @Override
    public ResponseDto getByUserName(String name){
        Student student = studentRepository.findByUserId(name).orElse(null);
        if(student != null){
            return new ResponseDto(studentConverter.toDto(student));
        }
        return new ResponseDto(Utility.getMessage(ETimMessages.USER_NOT_FOUND));
    }

    @Override
    public ResponseDto getByEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        if(student != null){
            return new ResponseDto(studentConverter.toDto(student));
        }
        return new ResponseDto(Utility.getMessage(ETimMessages.USER_NOT_FOUND));
    }

    @Override
    public ResponseDto toggleStatus(Long id) {
        Student student = studentRepository.getOneById(id);
        student.setStatus(false);
        studentRepository.save(student);
        return new ResponseDto();
    }

}
