package com.tim.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tim.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>, 
	JpaSpecificationExecutor<Notification> {

	Page<Notification> findByStatusTrueAndTypeOrStatusTrueAndNotificationTeachers_Teacher_UserId(
			int type, String usersUserId, Pageable pageable);

	Page<Notification> findByStatusTrueAndTypeOrStatusTrueAndNotificationStudents_Student_UserId(
			int type, String usersUserId, Pageable pageable);

	Optional<Notification> findBySlug(String slug);

}
