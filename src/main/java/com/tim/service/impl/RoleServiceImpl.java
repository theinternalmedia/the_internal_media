package com.tim.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tim.converter.RoleConverter;
import com.tim.data.ETimMessages;
import com.tim.data.ETimRoles;
import com.tim.dto.role.RoleCreateDto;
import com.tim.dto.role.RoleDto;
import com.tim.dto.role.RoleUpdateDto;
import com.tim.entity.Permission;
import com.tim.entity.Role;
import com.tim.entity.Teacher;
import com.tim.exception.TimException;
import com.tim.exception.TimNotFoundException;
import com.tim.repository.PermissionRepository;
import com.tim.repository.RoleRepository;
import com.tim.repository.TeacherRepository;
import com.tim.service.RoleService;
import com.tim.utils.MessageUtils;
import com.tim.utils.PrincipalUtils;
import com.tim.utils.ValidationUtils;

@Service
public class RoleServiceImpl implements RoleService {
	private static final String ROLE = "Vai trò";

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RoleConverter roleConverter;
	@Autowired
	PermissionRepository permissionRepository;
	@Autowired
	TeacherRepository teacherRepository;

	@Override
	@Transactional
	public RoleDto create(RoleCreateDto createDto) {
		if(!isAdmin()) {
			throw new TimException(ETimMessages.ACCESS_DENIED);
		}
		ValidationUtils.validateObject(createDto);

		if (roleRepository.existsByCode(createDto.getCode())) {
			throw new TimException(ETimMessages.ALREADY_EXISTS, "Mã Vai Trò", createDto.getCode());
		}
		if (roleRepository.existsByName(createDto.getName())) {
			throw new TimException(ETimMessages.ALREADY_EXISTS, "Tên Vai Trò", createDto.getName());
		}
		Role roleEntity = roleConverter.toEntity(createDto);

		Set<String> permissionSet = createDto.getPermissions();
		Set<Permission> permissionEntities = new HashSet<>();
		Permission permission;
		Set<String> notFoundPermissions = new HashSet<>();
		if (permissionSet.size() > 0) {
			for (String item : permissionSet) {
				permission = permissionRepository.findByCode(item).orElse(null);
				if (permission == null) {
					notFoundPermissions.add(item);
				} else {
					permissionEntities.add(permission);
				}
			}
		}
		if (notFoundPermissions.size() > 0) {
			throw new TimException(Arrays.asList(MessageUtils
									.get(ETimMessages.ENTITY_NOT_FOUND, 
									"Vai trò", notFoundPermissions.toString())),
									ETimMessages.INVALID_OBJECT_VALUE);
		}
		roleEntity.setPermissions(permissionEntities);
		Role savedRole = roleRepository.save(roleEntity);

		return roleConverter.toDto(savedRole);
	}


	@Override
	@Transactional
	public RoleDto update(RoleUpdateDto updateDto) {
		if(!isAdmin()) {
			throw new TimException(ETimMessages.ACCESS_DENIED);
		}
		ValidationUtils.validateObject(updateDto);

		Role roleEntity = roleRepository.findById(updateDto.getId())
				.orElseThrow(() -> new TimNotFoundException(ROLE, "ID", 
								updateDto.getId().toString()));

		if (!isSystemRole(updateDto.getCode(), updateDto.getName())) {
			roleEntity.setCode(updateDto.getCode());
			roleEntity.setName(updateDto.getName());
		}

		Set<String> notFoundPermissions = new HashSet<>();
		Set<String> permissionSet = updateDto.getPermissions();
		Set<Permission> permissionEntities = new HashSet<>();
		Permission permission;
		if (permissionSet.size() > 0) {
			for (String item : permissionSet) {
				permission = permissionRepository.findByCode(item).orElse(null);
				if (permission == null) {
					notFoundPermissions.add(item);
				} else {
					permissionEntities.add(permission);
				}
			}
		}
		if (notFoundPermissions.size() > 0) {
			throw new TimException(Arrays.asList(MessageUtils.get(ETimMessages.ENTITY_NOT_FOUND, 
				"Vai trò", notFoundPermissions.toString())),ETimMessages.INVALID_OBJECT_VALUE);
		}
		roleEntity.setPermissions(permissionEntities);

		Role updatedRole = roleRepository.save(roleEntity);

		return roleConverter.toDto(updatedRole);
	}

	
	@Override
	public RoleDto getOneByCode(String roleCode) {
		if(!isAdmin()) {
			throw new TimException(ETimMessages.ACCESS_DENIED);
		}
		Role entity = roleRepository.findByCodeAndStatusTrue(roleCode).orElseThrow(
				() -> new TimNotFoundException(ROLE, "Mã Vai Trò", roleCode));
		return roleConverter.toDto(entity);
	}
	
	
	@Override
	public long toggleStatus(Set<Long> ids) {
		if(!isAdmin()) {
			throw new TimException(ETimMessages.ACCESS_DENIED);
		}
		String roleCode;
		String roleName;
		Role entity;
		Set<Role> entities = new HashSet<>();
		for(Long id : ids) {
			entity = roleRepository.findById(id).orElseThrow(
					() -> new TimNotFoundException(ROLE, "ID", String.valueOf(id)));
			roleCode = entity.getCode();
			roleName = entity.getName();
			if(isSystemRole(roleCode, roleName)) {
				throw new TimException(ETimMessages.ACCESS_DENIED,"ID", entity.getId().toString());
			}
			entity.setStatus(!entity.getStatus());
			entities.add(entity);
		}
		roleRepository.saveAll(entities);
		return ids.size();
	}

	
	private boolean isSystemRole(String code, String name) {
		Boolean isSystemRole = false;
		for (ETimRoles item : ETimRoles.values()) {
			if (code.equals(item.code) || name.equals(item.name)) {
				isSystemRole = true;
			}
		}
		return isSystemRole;
	}
	
	private boolean isAdmin() {
		String userId = PrincipalUtils.getAuthenticatedUsersUserId();
		Teacher teacher = teacherRepository.findByUserId(userId).orElse(null);
		Set<Role> roleSet = teacher.getRoles();
		for(Role role : roleSet) {
			if(role.getCode().equals(ETimRoles.ROLE_ADMIN.code)) {
				return true;
			}
		}
		return false;
	}

}
