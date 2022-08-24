package com.tim.payload.response;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponse {
	private String accessToken;
	private String refreshToken;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	private List<String> permissions;
	private String name;
	private String avatar;

	public JwtResponse(String token, String refreshToken, Long id, String username, String email, List<String> roles, String name,
			String avatar, List<String> permissions) {
		this.accessToken = token;
		this.refreshToken = refreshToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.name = name;
		this.avatar = avatar;
		this.permissions = permissions;
	}
}
