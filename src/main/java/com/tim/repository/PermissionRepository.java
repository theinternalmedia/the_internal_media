package com.tim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
