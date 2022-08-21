package com.tim.payload.response;

import lombok.Data;

@Data
public class TokenRefreshResponse {
	
	private String accessToken;
	private String refreshToken;
	private String tokenType = "Bearer";
	public TokenRefreshResponse(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}
