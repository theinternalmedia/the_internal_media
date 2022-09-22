package com.tim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim.entity.Permission;

/**
 * 
 * @appName the_internal_media
 *
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	Optional<Permission> findByCode(String code);

}
