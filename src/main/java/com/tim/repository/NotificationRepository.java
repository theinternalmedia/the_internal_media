package com.tim.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tim.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>, 
	JpaSpecificationExecutor<Notification> {

	Page<Notification> findByTypeOrNotificationTeachers_Teacher_UserId(int toAll, String usersUserId,
			Pageable pageable);

	Page<Notification> findByTypeOrNotificationStudents_Student_UserId(int toAll, String usersUserId,
			Pageable pageable);

	Optional<Notification> findBySlug(String slug);

}