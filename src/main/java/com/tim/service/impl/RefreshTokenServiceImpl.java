package com.tim.service.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tim.data.ETimMessages;
import com.tim.entity.RefreshToken;
import com.tim.exception.CustomException;
import com.tim.repository.RefreshTokenRepository;
import com.tim.service.RefreshTokenService;
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
	
  @Value("${tim.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;
  
  @Autowired
  private RefreshTokenRepository refreshTokenRepository;
 
  @Override
  public Optional<RefreshToken> findByTokenAndUserId(String token, String userId) {
    return refreshTokenRepository.findByTokenAndUserId(token, userId);
  }
  
  @Override
  public RefreshToken createRefreshToken(String userId) {
	
    RefreshToken refreshToken = null;
    refreshToken = refreshTokenRepository.findByUserId(userId).orElse(null);
    if (refreshToken != null) {
    	refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    	return refreshTokenRepository.save(refreshToken);
    } 
    refreshToken  = new RefreshToken();
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setUserId(userId);
    refreshToken.setToken(UUID.randomUUID().toString());
    return refreshTokenRepository.save(refreshToken);
  }
  
  @Override
  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().isBefore(Instant.now())) {
      refreshTokenRepository.delete(token);
      throw new CustomException(ETimMessages.EXPIRED_TOKEN, token.getToken());
    }
    return token;
  }
}