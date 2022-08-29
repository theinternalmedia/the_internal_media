package com.tim.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.StudentConverter;
import com.tim.data.ETimMessages;
import com.tim.data.ETimRoles;
import com.tim.data.TimConstants;
import com.tim.dto.specification.TimSpecification;
import com.tim.dto.student.StudentDto;
import com.tim.dto.student.StudentRequestDto;
import com.tim.dto.student.StudentUpdateRequestDto;
import com.tim.entity.Classz;
import com.tim.entity.Faculty;
import com.tim.entity.Role;
import com.tim.entity.SchoolYear;
import com.tim.entity.Student;
import com.tim.exception.TimException;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.ClassRepository;
import com.tim.repository.RoleRepository;
import com.tim.repository.StudentRepository;
import com.tim.service.StudentService;
import com.tim.service.excel.ExcelService;
import com.tim.utils.PrincipalUtils;
import com.tim.utils.UploadUtils;
import com.tim.utils.ValidationUtils;

@Service
public class StudentServiceImpl implements StudentService {
	private static final String STUDENT = "Sinh Viên";
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
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public StudentDto create(StudentRequestDto requestDto) {
		// Validate input
		ValidationUtils.validateObject(requestDto);
		
		// Check exists UserId
		if (studentRepository.existsByUserId(requestDto.getUserId())) {
			throw new TimException(
					ETimMessages.ALREADY_EXISTS, "Mã SV", requestDto.getUserId());
		}
		
		// Check exists Email
		if (StringUtils.isNotBlank(requestDto.getEmail())) {
			if (studentRepository.existsByEmail(requestDto.getEmail())) {
				throw new TimException(
						ETimMessages.ALREADY_EXISTS, "Email", requestDto.getEmail());
			}
		}
		
		Student entity = studentConverter.toEntity(requestDto);
		// Set Class
		Classz classz = classRepository.getByCode(requestDto.getClassCode()).orElseThrow(
				() -> new TimNotFoundException("Lớp Học", "Mã Lớp", requestDto.getClassCode()));
		entity.setClassz(classz);
		
		// Set Role
		Role role = roleRepository.findByCode(ETimRoles.ROLE_STUDENT.toString());
		entity.setRoles(Set.of(role));
		entity.setPassword(encoder.encode(entity.getPassword()));
		entity = studentRepository.save(entity);
		return studentConverter.toDto(entity);
	}

	@Transactional
	@Override
	public void create(MultipartFile file) {
		List<StudentDto> dtoList = excelService.getListObjectFromExcelFile(file, StudentDto.class);
		List<Student> entityList = new ArrayList<>();
		// UserID Set
		Set<String> userIdSet = new HashSet<String>();
		// Email Set
		Set<String> emailSet = new HashSet<String>();
		for(StudentDto dto : dtoList){
			Student entity = studentConverter.toEntity(dto);
			Classz classz = classRepository.getByCode(dto.getClassCode()).orElse(null);
			if(classz == null) {
				throw new TimNotFoundException("Lớp học", "Mã Lớp", dto.getClassCode());
			}
			entity.setClassz(classz);
			entity.setPassword(encoder.encode(entity.getPassword()));
			Role role = roleRepository.findByCode(ETimRoles.ROLE_STUDENT.toString());
			
			entity.setRoles(Set.of(role));
			entityList.add(entity);
			userIdSet.add(entity.getUserId());
			if (StringUtils.isNotBlank(entity.getEmail())) {
				emailSet.add(entity.getEmail());
			}
		}
		// Check exists UserId
		List<Student> studentLst = studentRepository.findByUserIdIn(userIdSet);
		userIdSet.clear();
		if (!CollectionUtils.isEmpty(studentLst)) {
			studentLst.forEach(item -> {
				userIdSet.add(item.getUserId());
			});
			throw new TimException(List.of(userIdSet.toString()), 
					ETimMessages.ALREADY_EXISTS, "Mã SV", "[Chi tiết]");
		}
		// Check exists Email
		if (emailSet.size() > 0) {
			studentLst = studentRepository.findByEmailIn(emailSet);
			if (!CollectionUtils.isEmpty(studentLst)) {
				studentLst.forEach(item -> {
					emailSet.add(item.getEmail());
				});
				throw new TimException(List.of(emailSet.toString()), 
						ETimMessages.ALREADY_EXISTS, "Email", "[Chi tiết]");
			}
		}
		studentRepository.saveAll(entityList);
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
	@Transactional
	public String updateAvatar(MultipartFile file) {
		String usersUserId = PrincipalUtils.getAuthenticatedUsersUserId();
		Student student = studentRepository.findByUserId(usersUserId).orElseThrow(
				() -> new TimNotFoundException(STUDENT, "Mã SV", usersUserId));
		final String fileName = TimConstants.Upload.STUDENT_PREFIX + student.getUserId();
		try {
			if (StringUtils.isNotBlank(student.getAvatar())) {
				UploadUtils.delelteFile(student.getAvatar());
			}
			String avatar = UploadUtils.uploadImage(file, TimConstants.Upload.AVATAR_DIR, fileName);
			student.setAvatar(avatar);
			studentRepository.save(student);
			return avatar;
		} catch (Exception e) {
			throw new TimException(ETimMessages.INTERNAL_SYSTEM_ERROR);
		}
	}
	@Override
    public StudentDto update(StudentUpdateRequestDto requestDto) {
    	// Validate input
    	ValidationUtils.validateObject(requestDto);
    	
    	// Get Student Entity
		Student student = studentRepository.findById(requestDto.getId()).orElseThrow(
				() -> new TimNotFoundException(STUDENT, "ID", requestDto.getId().toString()));
		
		// Check exists UserId and not equals old entity
		if (!student.getUserId().equals(requestDto.getUserId())) {
			if(studentRepository.existsByUserId(requestDto.getUserId())) {
				throw new TimException(
						ETimMessages.ALREADY_EXISTS, "Mã SV", requestDto.getUserId());
			}
		}
		
		// Check exists Email
		if (StringUtils.isNotBlank(requestDto.getEmail())
				&& !student.getEmail().equals(requestDto.getEmail())) {
			if (studentRepository.existsByEmail(requestDto.getUserId())) {
				throw new TimException(
						ETimMessages.ALREADY_EXISTS, "Email", requestDto.getEmail());
			}
		}
		
		// Convert to entity from dto and old entity
		student = studentConverter.toEntity(requestDto, student);
		
		// Mapping Class
		Classz classz = classRepository.getByCode(requestDto.getClassCode()).orElseThrow(
				() -> new TimNotFoundException("Lớp Học", "Mã", requestDto.getClassCode()));
		student.setClassz(classz);
		
		// If password has changed
		if (StringUtils.isNotBlank(requestDto.getPassword())) {
			student.setPassword(encoder.encode(requestDto.getPassword()));
		}
		student = studentRepository.save(student);
		return studentConverter.toDto(student);
    }

    @Override
    public StudentDto getOne(Long id) {
        Optional<Student> student = studentRepository.findById(id);
		return student.map(student1 -> studentConverter.toDto(student1))
							.orElseThrow(() -> new TimNotFoundException(
									STUDENT, "ID", id.toString()));
	}

    @Override
    public StudentDto getByUserId(String userId){
        Student student = studentRepository.findByUserId(userId).orElseThrow(
        		() -> new TimNotFoundException(STUDENT, "Mã SV", userId));
        return studentConverter.toDto(student);
    }

    @Override
    public Long toggleStatus(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
        		() -> new TimNotFoundException(STUDENT, "ID", id.toString()));
        student.setStatus(!student.getStatus());
        studentRepository.save(student);
        return id;
    }

}
