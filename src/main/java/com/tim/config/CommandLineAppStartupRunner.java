package com.tim.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tim.data.ETimPermissions;
import com.tim.data.ETimRoles;
import com.tim.data.TimConstants;
import com.tim.entity.Permission;
import com.tim.entity.Role;
import com.tim.entity.Teacher;
import com.tim.repository.PermissionRepository;
import com.tim.repository.RoleRepository;
import com.tim.repository.TeacherRepository;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

	@Autowired
    private PermissionRepository permissionRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
    @Override
    public void run(String...args) throws Exception {
    	Set<Permission> permissions = new HashSet<>();
    	Permission permission;
		for (ETimPermissions e : ETimPermissions.values()) {
			permission = new Permission(e.code, e.name, new HashSet<>());
			if (!permissionRepository.existsByCode(e.code)) {
				permission = permissionRepository.save(permission);
			}
			permissions.add(permission);
		}
		Set<Role> roles = new HashSet<>();
		Role role;
		for (ETimRoles e : ETimRoles.values()) {
			if (e.code.equals(ETimRoles.ROLE_ADMIN.code)) {
				role = new Role(e.code, e.name, new HashSet<>(),new HashSet<>(), permissions);
			} else {
				role = new Role(e.code, e.name, new HashSet<>(),new HashSet<>(), new HashSet<>());
			}
			
			if (!roleRepository.existsByCode(e.code)) {
				role = roleRepository.saveAndFlush(role);
			}
			roles.add(role);
		}
		if (!teacherRepository.existsByUserId(TimConstants.ADMIN_USERID)) {
			Teacher admin = new Teacher();
			admin.setUserId(TimConstants.ADMIN_USERID);
			admin.setPassword(passwordEncoder.encode(TimConstants.ADMIN_PASSWORD));
			admin.setName("Admin");
			admin.setRemark("Quản Trị Viên");
			admin.setRoles(roles);
			teacherRepository.save(admin);
		}
    }
    
}
