package com.tim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tim.entity.Classz;

public interface ClassRepository extends JpaRepository<com.tim.entity.Classz, Long>, JpaSpecificationExecutor<Classz>  {

	Optional<Classz> getByCodeAndStatusTrue(String classCode);

	boolean existsByCode(String code);
}
