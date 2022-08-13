package com.tim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
