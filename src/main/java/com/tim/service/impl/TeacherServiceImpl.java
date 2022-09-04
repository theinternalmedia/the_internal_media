package com.tim.service.impl;

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
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.TeacherConverter;
import com.tim.data.ETimMessages;
import com.tim.data.ETimRoles;
import com.tim.data.SearchOperation;
import com.tim.data.TimConstants;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.PasswordDto;
import com.tim.dto.specification.SearchCriteria;
import com.tim.dto.specification.TimSpecification;
import com.tim.dto.teacher.TeacherDto;
import com.tim.dto.teacher.TeacherRequestDto;
import com.tim.dto.teacher.TeacherUpdateRequestDto;
import com.tim.entity.Faculty;
import com.tim.entity.Role;
import com.tim.entity.Teacher;
import com.tim.exception.TimException;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.FacultyRepository;
import com.tim.repository.RoleRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.TeacherService;
import com.tim.service.excel.ExcelService;
import com.tim.utils.PrincipalUtils;
import com.tim.utils.UploadUtils;
import com.tim.utils.ValidationUtils;

@Service
public class TeacherServiceImpl implements TeacherService {
	private static final String TEACHER = "Giảng Viên";
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
	public TeacherDto create(TeacherRequestDto requestDto) {
		// Validate input
		ValidationUtils.validateObject(requestDto);
		
		// Check exists UserId
		if (teacherRepository.existsByUserId(requestDto.getUserId())) {
			throw new TimException(
					ETimMessages.ALREADY_EXISTS, "Mã SV", requestDto.getUserId());
		}
		
		// Check exists Email
		if (StringUtils.isNotBlank(requestDto.getEmail())) {
			if (teacherRepository.existsByEmail(requestDto.getEmail())) {
				throw new TimException(
						ETimMessages.ALREADY_EXISTS, "Email", requestDto.getEmail());
			}
		}
		
		Teacher entity = teacherConverter.toEntity(requestDto);
		
		// Mapping Faculty
		Faculty faculty = facultyRepository.getByCode(requestDto.getFacultyCode()).orElseThrow(
				() -> new TimNotFoundException("Khoa", "Mã Khoa", requestDto.getFacultyCode()));
		entity.setFaculty(faculty);
		
		// Mapping Roles
		Set<Role> roles = new HashSet<Role>();
		requestDto.getRoleCodes().add(ETimRoles.ROLE_TEACHER);
		for(ETimRoles code : requestDto.getRoleCodes()) {
			Role role = roleRepository.findByCode(code.toString());
			roles.add(role);
		}
		entity.setRoles(roles);
		entity.setPassword(encoder.encode(entity.getPassword()));
		return teacherConverter.toDto(teacherRepository.save(entity));
	}

	@Override
	public TeacherDto getOne(String userId) {
		Teacher entity = teacherRepository.findByUserId(userId).orElseThrow(
				() -> new TimNotFoundException(TEACHER, "Mã GV", userId));
		return teacherConverter.toDto(entity);
	}


	@Override
	@Transactional
	public void create(MultipartFile file) {
		// Get dto list from excel file
		List<TeacherDto> dtoList = excelService.getListObjectFromExcelFile(file, TeacherDto.class);
		
		List<Teacher> entityList = new ArrayList<Teacher>();
		// UserID Set
		Set<String> userIdSet = new HashSet<String>();
		// Email Set
		Set<String> emailSet = new HashSet<String>();
		dtoList.forEach(item -> {
			Teacher entity = teacherConverter.toEntity(item);
			Faculty faculty = facultyRepository.getByCode(item.getFacultyCode()).orElseThrow(
					() -> new TimNotFoundException("Khoa", "Mã Khoa", item.getFacultyCode()));
			entity.setFaculty(faculty);
			entity.setPassword(encoder.encode(entity.getPassword()));
			Role role = roleRepository.findByCode(ETimRoles.ROLE_TEACHER.toString());
			entity.setRoles(Set.of(role));
			entityList.add(entity);
			
			userIdSet.add(entity.getUserId());
			if (StringUtils.isNotBlank(entity.getEmail())) {
				emailSet.add(entity.getEmail());
			}
		});
		// Check exists UserId
		List<Teacher> teacherLst = teacherRepository.findByUserIdIn(userIdSet);
		userIdSet.clear();
		if (!CollectionUtils.isEmpty(teacherLst)) {
			teacherLst.forEach(item -> {
				userIdSet.add(item.getUserId());
			});
			throw new TimException(List.of(userIdSet.toString()), 
					ETimMessages.ALREADY_EXISTS, "Mã GV", "[Chi tiết]");
		}
		// Check exists Email
		if (emailSet.size() > 0) {
			teacherLst = teacherRepository.findByEmailIn(emailSet);
			if (!CollectionUtils.isEmpty(teacherLst)) {
				teacherLst.forEach(item -> {
					emailSet.add(item.getEmail());
				});
				throw new TimException(List.of(emailSet.toString()), 
						ETimMessages.ALREADY_EXISTS, "Email", "[Chi tiết]");
			}
		}
		teacherRepository.saveAll(entityList);
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
	public String updateAvatar(MultipartFile file) {
		String usersUserId = PrincipalUtils.getAuthenticatedUsersUserId();
		Teacher teacher = teacherRepository.findByUserId(usersUserId).orElseThrow(
				() -> new TimNotFoundException(TEACHER, "Mã GV", usersUserId));
		final String fileName = TimConstants.Upload.TEACHER_PREFIX + teacher.getUserId();
		try {
			if (StringUtils.isNotBlank(teacher.getAvatar())) {
				UploadUtils.delelteFile(teacher.getAvatar());
			}
			String avatar = UploadUtils.uploadImage(file, TimConstants.Upload.AVATAR_DIR, fileName);
			teacher.setAvatar(avatar);
			teacherRepository.save(teacher);
			return avatar;
		} catch (Exception e) {
			throw new TimException(ETimMessages.INTERNAL_SYSTEM_ERROR);
		}
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
	public TeacherDto getByUserId(String userId) {
		Teacher teacher = teacherRepository.findByUserId(userId).orElseThrow(
				() -> new TimNotFoundException(TEACHER, "Mã GV", userId));
		return teacherConverter.toDto(teacher);
	}

	@Override
	public TeacherDto update(TeacherUpdateRequestDto requestDto) {
		// Validate input
		ValidationUtils.validateObject(requestDto);
		
		// Get Teacher Entity
		Teacher entity = teacherRepository.findById(requestDto.getId()).orElseThrow(
				() -> new TimNotFoundException(TEACHER, "ID", requestDto.getId().toString()));
		
		// Check exists UserId and not equals old entity
		if (!entity.getUserId().equals(requestDto.getUserId())) {
			if(teacherRepository.existsByUserId(requestDto.getUserId())) {
				throw new TimException(
						ETimMessages.ALREADY_EXISTS, "Mã SV", requestDto.getUserId());
			}
		}
		
		// Check exists Email
		if (StringUtils.isNotBlank(requestDto.getEmail())
				&& !entity.getEmail().equals(requestDto.getEmail())) {
			if (teacherRepository.existsByEmail(requestDto.getUserId())) {
				throw new TimException(
						ETimMessages.ALREADY_EXISTS, "Email", requestDto.getEmail());
			}
		}
		
		// Convert to entity from old entity and request dto
		entity = teacherConverter.toEntity(requestDto, entity);		
		
		// Mapping Faculty
		Faculty faculty = facultyRepository.getByCode(requestDto.getFacultyCode()).orElseThrow(
				() -> new TimNotFoundException("Khoa", "Mã Khoa", requestDto.getFacultyCode()));
		entity.setFaculty(faculty);
		
		// Reset - Mapping Roles
		Set<Role> roles = new HashSet<Role>();
		requestDto.getRoleCodes().add(ETimRoles.ROLE_TEACHER);
		for(ETimRoles code : requestDto.getRoleCodes()) {
			Role role = roleRepository.findByCode(code.toString());
			roles.add(role);
		}
		entity.setRoles(roles);
		
		// Set password
		if (StringUtils.isNotEmpty(requestDto.getPassword())) {
			entity.setPassword(encoder.encode(requestDto.getPassword()));
		}
		return teacherConverter.toDto(teacherRepository.save(entity));
	}

	@Override
	public Long toggleStatus(Long id) {
		Teacher teacher = teacherRepository.findById(id).orElseThrow(
				() -> new TimNotFoundException(TEACHER, "ID", id.toString()));
		teacher.setStatus(!teacher.getStatus());
		teacherRepository.save(teacher);
		return id;
	}

	@Override
	@Transactional
	public void updatePassword(PasswordDto passwordDto) {
		ValidationUtils.validateObject(passwordDto);
		
		String userId = PrincipalUtils.getAuthenticatedUsersUserId();
		Teacher teacher = teacherRepository.getByUserId(userId).orElseThrow(
				() -> new TimNotFoundException(TEACHER, "Mã GV", "userId"));
		
		if (!encoder.matches(passwordDto.getOldPassword(), teacher.getPassword())) {
			throw new TimException(ETimMessages.PASSWORD_NOT_MATCH);
		}
		teacher.setPassword(encoder.encode( passwordDto.getNewPassword()));
		teacherRepository.save(teacher);
	}

}
