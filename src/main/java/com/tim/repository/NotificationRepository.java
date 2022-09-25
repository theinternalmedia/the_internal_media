package com.tim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tim.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>, 
	JpaSpecificationExecutor<Notification> {

	Optional<Notification> findBySlug(String slug);

}
