package com.codeintelligence.delivery.serviceImpl;

import com.codeintelligence.delivery.model.user.UserEntity;
import com.codeintelligence.delivery.repository.TokenRepository;
import com.codeintelligence.delivery.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    private final String SECRET_KEY = "bWVqdW50YXNtaXBhbGFicmFzZWNyZXRhdWx0cmFsYXJnYWNvbmxvcXVlcXVlaXJhcw";

    private final TokenRepository tokenRepository;

    /**
     * Constructs a JwtServiceImpl with the specified TokenRepository.
     *
     * @param tokenRepository the repository used to manage token data
     */
    public JWTServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    /**
     * Extracts the username from the given JWT token.
     *
     * @param token the JWT token
     * @return the username contained in the token
     */
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Validates the JWT token against the provided UserDetails.
     *
     * @param token the JWT token to validate
     * @param user  the UserDetails object representing the authenticated user
     * @return true if the token is valid, false otherwise
     */
    @Override
    public boolean isValidToken(String token, UserDetails user) {
        String username = extractUsername(token);

        boolean validToken = tokenRepository.findByToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);

        return username.equals(user.getUsername()) && !isTokenExpired(token) && validToken;
    }

    /**
     * Checks if the JWT token has expired.
     *
     * @param token the JWT token to check
     * @return true if the token has expired, false otherwise
     */
    @Override
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the given JWT token.
     *
     * @param token the JWT token
     * @return the expiration date of the token
     */
    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from the JWT token.
     *
     * @param token   the JWT token
     * @param resolver a function to resolve the claim
     * @param <T>    the type of the claim to extract
     * @return the resolved claim value
     */
    @Override
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    /**
     * Extracts all claims from the given JWT token.
     *
     * @param token the JWT token
     * @return the claims contained in the token
     */
    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Generates a new JWT token for the specified user.
     *
     * @param user the user for whom to generate the token
     * @return the generated JWT token
     */
    @Override
    public String generateToken(@NonNull UserEntity user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 1 day expiration
                .signWith(getSigninKey())
                .compact();
    }

    /**
     * Retrieves the signing key used for signing JWT tokens.
     *
     * @return the SecretKey used for signing
     */
    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
