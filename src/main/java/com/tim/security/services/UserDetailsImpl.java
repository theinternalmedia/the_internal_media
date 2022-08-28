package com.tim.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tim.entity.Permission;
import com.tim.entity.Role;

/**
 * 
 * @appName the_internal_media
 *
 */
public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String email;
	@JsonIgnore
	private String password;
	private List<String> roles;
	private Boolean status;
	private String name;
	private String avatar;
	private boolean isTeacher;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailsImpl(Long id, String username, String name, String email, String password, boolean status,
			Collection<? extends GrantedAuthority> authorities, List<String> roles, String avatar, boolean isTeacher) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.status = status;
		this.roles = roles;
		this.avatar = avatar;
		this.isTeacher = isTeacher;
	}
	
	@SuppressWarnings("unchecked")
	public static UserDetailsImpl build(UserModel user) {
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) getAuthorities(user.getRoles());
		return new UserDetailsImpl(
				user.getId(), 
				user.getUserId(), 
				user.getName(),
				user.getEmail(), 
				user.getPassword(), 
				user.getStatus(),
				authorities,
				user.getRoles().stream().map(r -> r.getCode()).collect(Collectors.toList()),
				user.getAvatar(),
				user.isTeacher());
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		return getGrantedAuthorities(getPermission(roles));
	}

	private static List<GrantedAuthority> getGrantedAuthorities(List<String> permissions) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String permission : permissions) {
			authorities.add(new SimpleGrantedAuthority(permission));
		}
		return authorities;
	}
	
	private static List<String> getPermission(Collection<Role> roles) {

		List<String> permissions = new ArrayList<>();
		List<Permission> collection = new ArrayList<>();
		for (Role role : roles) {
			permissions.add(role.getCode().toString());
			collection.addAll(role.getPermissions());
		}
		for (Permission item : collection) {
			permissions.add(item.getCode().toString());
		}
		return permissions;
	}
	
	public String getAvatar() {
		return avatar;
	}

	public List<String> getRoles() {
		return roles;
	}
	
	public String getName() {
		return name;
	}
	
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public boolean isTeacher() {
		return isTeacher;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return status;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if( obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		UserDetailsImpl user = (UserDetailsImpl) obj;
		return Objects.equals(this.id, user.id);
	}
}
