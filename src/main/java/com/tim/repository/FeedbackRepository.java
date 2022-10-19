package com.tim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
