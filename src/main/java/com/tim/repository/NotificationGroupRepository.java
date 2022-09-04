package com.tim.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.NotificationGroup;

public interface NotificationGroupRepository extends JpaRepository<NotificationGroup, Long> {

	Optional<NotificationGroup> findByCodeAndStatusTrue(String code);

	List<NotificationGroup> findAllByStatus(boolean status);

}
