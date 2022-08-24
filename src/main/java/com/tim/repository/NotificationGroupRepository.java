package com.tim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.NotificationGroup;

public interface NotificationGroupRepository extends JpaRepository<NotificationGroup, Long> {

	NotificationGroup findByCode(String code);

	List<NotificationGroup> findAllByStatusTrue();

	NotificationGroup getByCode(String notificationGroupCode);


}
