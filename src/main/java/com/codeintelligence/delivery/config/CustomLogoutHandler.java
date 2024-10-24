package com.codeintelligence.delivery.config;

import com.codeintelligence.delivery.model.token.TokenEntity;
import com.codeintelligence.delivery.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * Custom Logout Handler for handling logout requests.
 * This class implements the LogoutHandler interface to manage token invalidation on logout.
 */
@Configuration
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;

    /**
     * Constructs a CustomLogoutHandler with the specified TokenRepository.
     *
     * @param tokenRepository the repository used to manage token data
     */
    public CustomLogoutHandler(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    /**
     * Handles the logout process.
     * This method invalidates the token associated with the authenticated user by marking it as logged out.
     *
     * @param request       the HttpServletRequest object containing the request details
     * @param response      the HttpServletResponse object containing the response details
     * @param authentication the Authentication object representing the authenticated user
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        String token = authHeader.substring( 7);
        TokenEntity storedToken = tokenRepository.findByToken(token).orElse(null);

        if (storedToken != null){
            storedToken.setLoggedOut(true);
            tokenRepository.save(storedToken);
        }

    }
}
