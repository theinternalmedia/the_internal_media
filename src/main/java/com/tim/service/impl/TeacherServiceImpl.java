package com.tim.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Predicate;

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
import com.tim.data.TimConstants;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.PasswordDto;
import com.tim.dto.teacher.TeacherDto;
import com.tim.dto.teacher.TeacherPageRequestDto;
import com.tim.dto.teacher.TeacherCreateDto;
import com.tim.dto.teacher.TeacherResponseDto;
import com.tim.dto.teacher.TeacherUpdateProfileDto;
import com.tim.dto.teacher.TeacherUpdateDto;
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
import com.tim.utils.MessageUtils;
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
	public TeacherDto create(TeacherCreateDto requestDto) {
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
		Faculty faculty = facultyRepository.findByCodeAndStatusTrue(requestDto.getFacultyCode()).orElseThrow(
				() -> new TimNotFoundException("Khoa", "Mã Khoa", requestDto.getFacultyCode()));
		entity.setFaculty(faculty);
		
		// Mapping Roles
		Set<Role> roles = new HashSet<Role>();
		requestDto.getRoleCodes().add(ETimRoles.ROLE_TEACHER.code);
		for(String code : requestDto.getRoleCodes()) {
			Role role = roleRepository.findByCodeAndStatusTrue(code.toString())
					.orElseThrow(() -> new TimNotFoundException("Vai Trò", "Mã", code));
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
	public long create(MultipartFile file) {
		// Get dto list from excel file
		List<TeacherCreateDto> dtoList = excelService.getListObjectFromExcelFile(
				file, TeacherCreateDto.class);
		
		List<Teacher> entityList = new ArrayList<Teacher>();
		// UserID Set
		Set<String> userIdSet = new HashSet<String>();
		// Email Set
		Set<String> emailSet = new HashSet<String>();
		for (TeacherCreateDto item : dtoList) {
			Teacher entity = teacherConverter.toEntity(item);
			Faculty faculty = facultyRepository.findByCodeAndStatusTrue(item.getFacultyCode()).orElseThrow(
					() -> new TimNotFoundException("Khoa", "Mã Khoa", item.getFacultyCode()));
			entity.setFaculty(faculty);
			entity.setPassword(encoder.encode(entity.getPassword()));
			Role role = roleRepository.findByCode(ETimRoles.ROLE_TEACHER.code).orElse(null);
			entity.setRoles(new HashSet<>(Arrays.asList(role)));
			entityList.add(entity);
			
			userIdSet.add(entity.getUserId());
			if (StringUtils.isNotBlank(entity.getEmail())) {
				emailSet.add(entity.getEmail());
			}
		}
		// Check exists UserId
		List<Teacher> teacherLst = teacherRepository.findByUserIdIn(userIdSet);
		userIdSet.clear();
		if (!CollectionUtils.isEmpty(teacherLst)) {
			teacherLst.forEach(item -> {
				userIdSet.add(item.getUserId());
			});
			throw new TimException(Arrays.asList(
					MessageUtils.get(ETimMessages.ALREADY_EXISTS, "Mã GV", userIdSet.toString())), 
					ETimMessages.INVALID_OBJECT_VALUE, "Danh Sách GV");
		}
		// Check exists Email
		if (emailSet.size() > 0) {
			teacherLst = teacherRepository.findByEmailIn(emailSet);
			if (!CollectionUtils.isEmpty(teacherLst)) {
				teacherLst.forEach(item -> {
					emailSet.add(item.getEmail());
				});
				throw new TimException(Arrays.asList(
						MessageUtils.get(ETimMessages.ALREADY_EXISTS, "Email", emailSet.toString())), 
						ETimMessages.INVALID_OBJECT_VALUE, "Danh Sách Giảng Viên");
			}
		}
		teacherRepository.saveAll(entityList);
		return entityList.size();
	}

	@Override
	public String exportToExcelFile() {
		List<Teacher> entityList = teacherRepository.findByUserIdAndStatusTrue(
				TimConstants.ADMIN_USERID);

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
	public PagingResponseDto getPage(TeacherPageRequestDto pageRequestDto) {
		// Validate input
		ValidationUtils.validateObject(pageRequestDto);
		
		// Specification
		Specification<Teacher> specification = Specification.where((root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();
			predicates.add(cb.equal(root.get("status"), pageRequestDto.getStatus()));
			if (StringUtils.isNotBlank(pageRequestDto.getSearchKey())) {
				predicates.add(cb.or(
						cb.like(cb.lower(root.get("name")), 
								"%" + pageRequestDto.getSearchKey().toLowerCase() + "%"),
						cb.like(cb.lower(root.get("address")), 
								"%" + pageRequestDto.getSearchKey().toLowerCase() + "%"),
						cb.like(cb.lower(root.get("phone")), 
								"%" + pageRequestDto.getSearchKey().toLowerCase() + "%"),
						cb.like(cb.lower(root.get("email")), 
								"%" + pageRequestDto.getSearchKey().toLowerCase() + "%"),
						cb.like(cb.lower(root.get("remark")), 
								"%" + pageRequestDto.getSearchKey().toLowerCase() + "%")));
			}
			if (pageRequestDto.getGender() != null) {
				predicates.add(cb.equal(root.get("gender"), 
						pageRequestDto.getGender()));
			}
			if (StringUtils.isNotBlank(pageRequestDto.getUserId())) {
				predicates.add(cb.equal(root.get("userId"), 
						pageRequestDto.getUserId()));
			} else {
				predicates.add(cb.notEqual(root.get("userId"), 
						TimConstants.ADMIN_USERID));
			}
			if (StringUtils.isNotBlank(pageRequestDto.getFacultyCode())) {
				predicates.add(cb.equal(root.get("faculty").get("code"), 
						pageRequestDto.getFacultyCode()));
			}
			return cb.and(predicates.toArray(new Predicate[0]));
		});
		
		Pageable pageable = PageRequest.of(
				pageRequestDto.getPage() - 1, 
				pageRequestDto.getSize(), 
				Sort.by("name", "userId"));
		Page<Teacher> pageTeachers = teacherRepository.findAll(specification, pageable);
		List<TeacherResponseDto> data = teacherConverter.toResponseDtoList(pageTeachers.getContent());
		
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
	public TeacherDto update(TeacherUpdateDto requestDto) {
		// Validate input
		ValidationUtils.validateObject(requestDto);
		
		// Get Teacher Entity
		Teacher entity = teacherRepository.findById(requestDto.getId()).orElseThrow(
				() -> new TimNotFoundException(TEACHER, "ID", requestDto.getId().toString()));
		
		// Check exists UserId and not equals old entity
		String newUserId = requestDto.getUserId();
		if (!entity.getUserId().equals(newUserId)) {
			if(teacherRepository.existsByUserId(newUserId)) {
				throw new TimException(
						ETimMessages.ALREADY_EXISTS, "Mã GV", newUserId);
			}
		}
		
		// Check exists Email if change email
		String newEmail = requestDto.getEmail();
		if (StringUtils.isNotBlank(newEmail)
				&& !entity.getEmail().equals(newEmail)) {
			if (teacherRepository.existsByEmail(newEmail)) {
				throw new TimException(
						ETimMessages.ALREADY_EXISTS, "Email", newEmail);
			}
		}
		
		// Convert to entity from old entity and request dto
		entity = teacherConverter.toEntity(requestDto, entity);		
		
		// Mapping Faculty
		Faculty faculty = facultyRepository.findByCodeAndStatusTrue(requestDto.getFacultyCode()).orElseThrow(
				() -> new TimNotFoundException("Khoa", "Mã Khoa", requestDto.getFacultyCode()));
		entity.setFaculty(faculty);
		
		// Reset - Mapping Roles
		Set<Role> roles = new HashSet<Role>();
		requestDto.getRoleCodes().add(ETimRoles.ROLE_TEACHER.code);
		for(String code : requestDto.getRoleCodes()) {
			Role role = roleRepository.findByCodeAndStatusTrue(code.toString())
					.orElseThrow(() -> new TimNotFoundException("Vai Trò", "Mã", code));
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
	public long toggleStatus(Set<Long> ids) {
    	List<Teacher> teachers = new ArrayList<>();
    	Teacher teacher;
		for (Long id : ids) {
			teacher = teacherRepository.findById(id).orElseThrow(
					() -> new TimNotFoundException(TEACHER, "ID", id.toString()));
			if (!teacher.getUserId().equals(TimConstants.ADMIN_USERID)) {
				teacher.setStatus(!teacher.getStatus());
				teachers.add(teacher);
			}
		}
		return teacherRepository.saveAll(teachers).size();
	}

	@Override
	@Transactional
	public void updatePassword(PasswordDto passwordDto) {
		// Validate input
		ValidationUtils.validateObject(passwordDto);
		
		String userId = PrincipalUtils.getAuthenticatedUsersUserId();
		Teacher teacher = teacherRepository.findByUserId(userId).orElseThrow(
				() -> new TimNotFoundException(TEACHER, "Mã GV", "userId"));
		
		// Confirm password is matches
		if (!encoder.matches(passwordDto.getOldPassword(), teacher.getPassword())) {
			throw new TimException(ETimMessages.PASSWORD_NOT_MATCH);
		}
		teacher.setPassword(encoder.encode(passwordDto.getNewPassword()));
		teacherRepository.save(teacher);
	}

	@Override
	@Transactional
	public TeacherDto updateProfile(TeacherUpdateProfileDto updateDto) {
		// Validate input
		ValidationUtils.validateObject(updateDto);
		
		Long id = updateDto.getId();
		Teacher teacher = teacherRepository.findById(id).orElseThrow(
				() -> new TimNotFoundException(TEACHER, "ID", String.valueOf(id)));

		// Check exists Email if change email
		String newEmail = updateDto.getEmail();
		if (StringUtils.isNotBlank(newEmail)
				&& !teacher.getEmail().equals(newEmail)) {
			if (teacherRepository.existsByEmail(newEmail)) {
				throw new TimException(
						ETimMessages.ALREADY_EXISTS, "Email", newEmail);
			}
		}
		teacher = teacherConverter.toEntity(updateDto, teacher);
		return teacherConverter.toDto(teacherRepository.save(teacher));
	}

}
