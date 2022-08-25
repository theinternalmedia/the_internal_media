package com.tim.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.TeacherConverter;
import com.tim.data.ETimMessages;
import com.tim.data.ETimRoles;
import com.tim.data.SearchOperation;
import com.tim.data.TimConstants;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.ResponseDto;
import com.tim.dto.specification.SearchCriteria;
import com.tim.dto.specification.TimSpecification;
import com.tim.dto.teacher.TeacherDto;
import com.tim.dto.teacher.TeacherRequestDto;
import com.tim.dto.teacher.TeacherUpdateRequestDto;
import com.tim.entity.Faculty;
import com.tim.entity.Role;
import com.tim.entity.Teacher;
import com.tim.exception.CustomException;
import com.tim.repository.FacultyRepository;
import com.tim.repository.RoleRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.TeacherService;
import com.tim.service.excel.ExcelService;
import com.tim.utils.UploadUtil;
import com.tim.utils.Utility;
import com.tim.utils.ValidationUtils;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private TeacherConverter teacherConverter;
	@Autowired
	private ExcelService excelService;
	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public ResponseDto create(TeacherRequestDto requestDto) {
		// Validate input
		ValidationUtils.validateObject(requestDto);
		
		Teacher entity = teacherConverter.toEntity(requestDto);
		Faculty faculty = facultyRepository.getByCode(requestDto.getFacultyCode()).orElse(null);
		if (faculty == null) {
			return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND,
					"Khoa", "Mã Khoa", requestDto.getFacultyCode()));
		}
		entity.setFaculty(faculty);
		Set<Role> roles = new HashSet<Role>();
		requestDto.getRoleCodes().add(ETimRoles.ROLE_TEACHER);
		for(ETimRoles code : requestDto.getRoleCodes()) {
			Role role = roleRepository.findByCode(code);
			roles.add(role);
		}
		entity.setRoles(roles);
		entity.setPassword(encoder.encode(entity.getPassword()));
		return new ResponseDto(teacherConverter.toDto(teacherRepository.save(entity)));
	}

	@Override
	public ResponseDto getOne(String userId) {
		Teacher entity = teacherRepository.findByUserId(userId).orElse(null);
		if (entity == null) {
			return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND,
					"Giáo Viên", "Mã GV", userId));
		}
		return new ResponseDto(teacherConverter.toDto(entity));
	}


	@Override
	@Transactional
	public ResponseDto create(MultipartFile file) {
		List<TeacherDto> dtoList = excelService.getListObjectFromExcelFile(file, TeacherDto.class);
		List<Teacher> entityList = new ArrayList<Teacher>();
		dtoList.forEach(item -> {
			Teacher entity = teacherConverter.toEntity(item);
			Faculty faculty = facultyRepository.getByCode(item.getFacultyCode()).orElse(null);
			entity.setFaculty(faculty);
			entity.setPassword(encoder.encode(entity.getPassword()));
			entityList.add(entity);
		});
		teacherRepository.saveAll(entityList);
		return new ResponseDto();
	}

	@Override
	public String exportToExcelFile() {
		List<Teacher> entityList = teacherRepository.findAll();
		List<TeacherDto> dtos = teacherConverter.toDtoList(entityList);
		excelService.writeListObjectToExcel(TimConstants.ExcelFiledName.TEACHER, dtos);
		return null;
	}

	@Override
	@Transactional
	public ResponseDto updateAvatar(MultipartFile file, String usersUserId) {
		Teacher teacher = teacherRepository.findByUserId(usersUserId).orElse(null);
		if (teacher != null) {
			final String fileName = TimConstants.Upload.TEACHER_PREFIX + teacher.getUserId();
			try {
				if (StringUtils.isNotBlank(teacher.getAvatar())) {
					UploadUtil.delelteFile(teacher.getAvatar());
				}
				String avatar = UploadUtil.uploadImage(file, TimConstants.Upload.AVATAR_DIR, fileName);
				teacher.setAvatar(avatar);
				return new ResponseDto();
			} catch (IOException e) {
				throw new CustomException(ETimMessages.INTERNAL_SYSTEM_ERROR);
			}
		}
		return new ResponseDto(Utility.getMessage(ETimMessages.ENTITY_NOT_FOUND, 
				"Sinh viên", "Mã SV", usersUserId));
	}

	@Override
	public PagingResponseDto getPage(String facultyCode, String name, String userId, int page, int size) {
		TimSpecification<Teacher> timSpecification = new TimSpecification<>();
		if (StringUtils.isNotEmpty(name)) {
			timSpecification.add(new SearchCriteria("name", name, SearchOperation.LIKE));
		}
		if (StringUtils.isNotEmpty(userId)) {
			timSpecification.add(new SearchCriteria("userId", userId, SearchOperation.EQUAL));
		}
		Specification<Teacher> specification = timSpecification;
		if (StringUtils.isNotEmpty(facultyCode)) {
			specification = specification.and((root, query, builder) -> {
				return builder.equal(root.join("faculty").get("code"), facultyCode);
			});
		}
		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("name"));
		Page<Teacher> pageTeachers = teacherRepository.findAll(specification, pageable);
		List<TeacherDto> data = teacherConverter.toDtoList(pageTeachers.getContent());
		return new PagingResponseDto(pageTeachers.getTotalElements(), 
				pageTeachers.getTotalPages(),
				pageTeachers.getNumber() + 1, 
				pageTeachers.getSize(), 
				data);
	}

	@Override
	public ResponseDto findByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto update(TeacherUpdateRequestDto requestDto) {
		// Validate input
		ValidationUtils.validateObject(requestDto);
		
		Teacher entity = teacherRepository.findById(requestDto.getId()).orElse(null);
		entity = teacherConverter.toEntity(requestDto, entity);		
		// Faculty
		Faculty faculty = facultyRepository.getByCode(requestDto.getFacultyCode()).orElse(null);
		if (faculty == null) {
			return new ResponseDto(Utility.getMessage(
					ETimMessages.ENTITY_NOT_FOUND,
					"Khoa", "Mã Khoa", 
					requestDto.getFacultyCode()));
		}
		entity.setFaculty(faculty);
		// Reset roles
		Set<Role> roles = new HashSet<Role>();
		requestDto.getRoleCodes().add(ETimRoles.ROLE_TEACHER);
		for(ETimRoles code : requestDto.getRoleCodes()) {
			Role role = roleRepository.findByCode(code);
			roles.add(role);
		}
		entity.setRoles(roles);
		// Set password
		if (StringUtils.isNotEmpty(requestDto.getPassword())) {
			entity.setPassword(encoder.encode(requestDto.getPassword()));
		}
		return new ResponseDto(teacherConverter.toDto(teacherRepository.save(entity)));
	}

	@Override
	public ResponseDto toggleStatus(Long id) {
		Teacher teacher = teacherRepository.findById(id).orElse(null);
		if (teacher != null) {
			teacher.setStatus(teacher.getStatus());
			teacherRepository.save(teacher);
			return new ResponseDto();
		}
		return new ResponseDto(Utility.getMessage(
				ETimMessages.ENTITY_NOT_FOUND, 
				"Giảng viên", 
				"ID", String.valueOf(id)));
	}

}
