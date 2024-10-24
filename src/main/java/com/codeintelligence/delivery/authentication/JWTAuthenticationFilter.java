package com.codeintelligence.delivery.authentication;

import com.codeintelligence.delivery.serviceImpl.JWTServiceImpl;
import com.codeintelligence.delivery.serviceImpl.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWTAuthenticationFilter is responsible for processing JWT authentication tokens
 * for each incoming HTTP request. It ensures that requests containing valid JWT tokens
 * are authenticated and added to the SecurityContext.
 *
 * <p>The filter is invoked once per request, thanks to the OncePerRequestFilter base class,
 * and extracts JWT tokens from the 'Authorization' header of incoming HTTP requests.</p>
 */
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTServiceImpl jwtService;

    private final UserDetailsServiceImpl userDetailsService;

    /**
     * Constructs a JWTAuthenticationFilter with the specified services for handling JWT and user details.
     *
     * @param jwtService         the service for handling JWT token validation and extraction
     * @param userDetailsService the service for loading user details from the database or other sources
     */
    public JWTAuthenticationFilter(JWTServiceImpl jwtService, UserDetailsServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     * <p>
     * Filters each request by extracting the JWT token from the 'Authorization' header, validating the token,
     * and setting up authentication in the SecurityContext if the token is valid and the user is authenticated.
     *
     * @param request     the HTTP request
     * @param response    the HTTP response
     * @param filterChain the filter chain to pass the request and response to the next filter
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs during request processing
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.isValidToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
