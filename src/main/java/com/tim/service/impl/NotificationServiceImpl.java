package com.tim.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tim.converter.NotificationConverter;
import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.dto.PagingResponseDto;
import com.tim.dto.notification.NotificationDto;
import com.tim.dto.notification.NotificationRequestDto;
import com.tim.dto.notification.NotificationUpdateRequestDto;
import com.tim.entity.Notification;
import com.tim.entity.NotificationGroup;
import com.tim.entity.NotificationStudent;
import com.tim.entity.NotificationTeacher;
import com.tim.entity.Student;
import com.tim.entity.Teacher;
import com.tim.exception.TimException;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.NotificationGroupRepository;
import com.tim.repository.NotificationRepository;
import com.tim.repository.NotificationStudentRepository;
import com.tim.repository.NotificationTeacherRepository;
import com.tim.repository.StudentRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.NotificationService;
import com.tim.service.StudentService;
import com.tim.utils.UploadUtils;
import com.tim.utils.Utility;
import com.tim.utils.ValidationUtils;


@Service
public class NotificationServiceImpl implements NotificationService {
	private static final String NOTIFICATION = "Thông Báo";

	@Autowired
	private NotificationConverter notificationConverter;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private NotificationTeacherRepository notificationTeacherRepository;
	@Autowired
	private NotificationStudentRepository notificationStudentRepository;
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private StudentService studentService;
	@Autowired
	private NotificationGroupRepository notificationGroupRepository;
	
	@Override
	@Transactional
	public NotificationDto create(NotificationRequestDto requestDto, MultipartFile thumbnail) {
		// Validate input
		ValidationUtils.validateObject(requestDto);
		
		Notification entity = notificationConverter.toEntity(requestDto);
		
		// Set Notification Group
		NotificationGroup notificationGroup = notificationGroupRepository
				.findByCode(requestDto.getNotificationGroupCode()).orElseThrow(() -> 
					new TimNotFoundException(
							"Nhóm Thông Báo", "Mã", requestDto.getNotificationGroupCode()));
		entity.setNotificationGroup(notificationGroup);
		
		// Save thumbnail 
		if (thumbnail != null) {
			final String fileName = Utility.getFileNameFromTime(TimConstants.Upload.NOTIFICATION_PREFIX);
            try {
                String thumnbnail = UploadUtils.uploadImage(thumbnail, 
                		TimConstants.Upload.THUMBNAIL_DIR, fileName);
                entity.setThumbnail(thumnbnail);
            }catch (IOException e){
                throw new TimException(ETimMessages.INTERNAL_SYSTEM_ERROR);
            }
		}
		
		// Save slug
		entity.setSlug(Utility.generateSlugs(entity.getTitle()));
		
		entity = notificationRepository.save(entity);
		// notify to users
		saveNotification(
				entity, 
				requestDto.getSchoolYearCodes(),
				requestDto.getFacultyCodes(),
				requestDto.getClassCodes());
		return notificationConverter.toDto(entity);
	}

	@Override
	@Transactional
	public NotificationDto update(NotificationUpdateRequestDto requestDto, MultipartFile thumbnail) {
		// Validate input
		ValidationUtils.validateObject(requestDto);

		Notification entity = notificationRepository.findById(requestDto.getId()).orElseThrow(
				() -> new TimNotFoundException(NOTIFICATION, "ID", requestDto.getId().toString()));
		
		entity = notificationConverter.toEntity(requestDto, entity);
		
		// Set Notification Group
		NotificationGroup notificationGroup = notificationGroupRepository
				.findByCode(requestDto.getNotificationGroupCode()).orElseThrow(() -> 
				new TimNotFoundException(
						"Nhóm Thông Báo", "Mã", requestDto.getNotificationGroupCode()));
		entity.setNotificationGroup(notificationGroup);
		
		// Save thumbnail
		if (thumbnail != null) {
			final String fileName = Utility.getFileNameFromTime(TimConstants.Upload.NEWS_PREFIX);
            try {
            	if (StringUtils.isNotBlank(entity.getThumbnail())) {
                	UploadUtils.delelteFile(entity.getThumbnail());
                }
                String thumnbnail = UploadUtils.uploadImage(thumbnail, 
                		TimConstants.Upload.THUMBNAIL_DIR, fileName);
                entity.setThumbnail(thumnbnail);
            }catch (IOException e){
                throw new TimException(ETimMessages.INTERNAL_SYSTEM_ERROR);
            }
		}
		
		// Save slug
		entity.setSlug(Utility.generateSlugs(entity.getTitle()));
		
		entity = notificationRepository.save(entity);

		// notify to users
		saveNotification(
				entity, 
				requestDto.getSchoolYearCodes(), 
				requestDto.getFacultyCodes(),
				requestDto.getClassCodes());
		return notificationConverter.toDto(entity);
	}

	/**
	 * Save Notification to users.
	 * 
	 * @author minhtuanitk43
	 * @param notification
	 * @param schoolYearCodes
	 * @param facultyCodes
	 * @param classCodes
	 */
	private void saveNotification(final Notification notification, Set<String> schoolYearCodes, 
			Set<String> facultyCodes, Set<String> classCodes) {
		switch (notification.getType()) {
		case TimConstants.NotificationType.TO_ALL:
			break;
		case TimConstants.NotificationType.TO_STUDENT:
			List<Student> students = new ArrayList<Student>();
			if (ObjectUtils.isNotEmpty(schoolYearCodes) && ObjectUtils.isNotEmpty(facultyCodes)) {
				students = studentService.findByChoolYearAndFacultyAndClass(schoolYearCodes, facultyCodes, null);
			} else if (ObjectUtils.isNotEmpty(schoolYearCodes) && ObjectUtils.isEmpty(facultyCodes)) {
				students = studentService.findByChoolYearAndFacultyAndClass(schoolYearCodes, null, null);
			} else if (ObjectUtils.isEmpty(schoolYearCodes) && ObjectUtils.isNotEmpty(facultyCodes)) {
				students = studentService.findByChoolYearAndFacultyAndClass(null, facultyCodes, null);
			} else if (ObjectUtils.isNotEmpty(classCodes)) {
				students = studentService.findByChoolYearAndFacultyAndClass(null, null, classCodes);
			} else {
				students = studentRepository.getByStatusTrue();
			}
			students.forEach(item -> {
				NotificationStudent notificationStudent = new NotificationStudent();
				notificationStudent.setStudent(item);
				notificationStudent.setNotification(notification);
				notificationStudentRepository.save(notificationStudent);
			});
			break;
		case TimConstants.NotificationType.TO_TEACHER:
			List<Teacher> teachers = new ArrayList<>();
			if (ObjectUtils.isNotEmpty(facultyCodes)) {
				teachers = teacherRepository.getByFaculty_CodeIn(facultyCodes);
			} else {
				teachers = teacherRepository.getByStatusTrue();
			}
			teachers.forEach(item -> {
				NotificationTeacher notificationTeacher = new NotificationTeacher();
				notificationTeacher.setTeacher(item);
				notificationTeacher.setNotification(notification);
				notificationTeacherRepository.save(notificationTeacher);
			});
			break;
		default:
			break;
		}
	}

	@Override
	public NotificationDto getOne(Long notificationId) {
		Notification notification = notificationRepository.findById(notificationId).orElseThrow(
				() -> new TimNotFoundException(NOTIFICATION, "ID", notificationId.toString()));
		return notificationConverter.toDto(notification);
	}

	@Override
	public PagingResponseDto getPage(int page, int size, String usersUserId, boolean isTeacher) {
		Page<Notification> pageNotification;
		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdDate"));
		if (isTeacher) {
			pageNotification = notificationRepository.findByTypeOrNotificationTeachers_Teacher_UserId(
					TimConstants.NotificationType.TO_ALL, usersUserId, pageable);
		} else {
			pageNotification = notificationRepository.findByTypeOrNotificationStudents_Student_UserId(
					TimConstants.NotificationType.TO_ALL, usersUserId, pageable);
		}
		
		List<NotificationDto> data = notificationConverter.toDtoList(pageNotification.getContent());

		return new PagingResponseDto(
				pageNotification.getTotalElements(), 
				pageNotification.getTotalPages(),
				pageNotification.getNumber() + 1, 
				pageNotification.getSize(), data);
	}

	@Override
	public NotificationDto getOne(String slug) {
		Notification notification = notificationRepository.findBySlug(slug).orElseThrow(
				() -> new TimNotFoundException(NOTIFICATION, "Slug", slug));
		return notificationConverter.toDto(notification);
	}
}
