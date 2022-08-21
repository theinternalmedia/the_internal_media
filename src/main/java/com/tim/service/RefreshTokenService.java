package com.tim.service;

import java.util.Optional;

import com.tim.entity.RefreshToken;

public interface RefreshTokenService {
	Optional<RefreshToken> findByTokenAndUserId(String token, String userId);
	
	RefreshToken createRefreshToken(String userId);
	
	RefreshToken verifyExpiration(RefreshToken token);
}
