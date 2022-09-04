package com.tim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.Classz;

public interface ClassRepository extends JpaRepository<com.tim.entity.Classz, Long> {

	Optional<Classz> getByCodeAndStatusTrue(String classCode);

}
