package com.tim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tim.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject>{

}
