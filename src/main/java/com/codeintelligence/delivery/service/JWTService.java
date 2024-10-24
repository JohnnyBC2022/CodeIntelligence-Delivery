package com.codeintelligence.delivery.service;

import com.codeintelligence.delivery.model.user.UserEntity;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

/**
 * JWTService defines the contract for services that handle JWT token generation, validation, and claim extraction.
 */
public interface JWTService {

    String extractUsername(String token);
    boolean isValidToken(String token, UserDetails user);
    boolean isTokenExpired(String token);
    Date extractExpiration(String token);
    <T> T extractClaim(String token, Function<Claims, T> resolver);
    String generateToken(UserEntity user);
}
