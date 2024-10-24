package com.codeintelligence.delivery.config;

import com.codeintelligence.delivery.authentication.JWTAuthenticationFilter;
import com.codeintelligence.delivery.serviceImpl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfig defines the security configurations for the application, such as authentication mechanisms,
 * session policies, and filter chains. It uses JWT tokens to authenticate and authorize requests.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    private final CustomLogoutHandler customLogoutHandler;

    /**
     * Constructor for SecurityConfig.
     *
     * @param userDetailsServiceImpl handles loading user details from the database.
     * @param jwtAuthenticationFilter JWT filter that authenticates requests based on JWT tokens.
     * @param customLogoutHandler handles custom logout behavior.
     */
    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, JWTAuthenticationFilter jwtAuthenticationFilter, CustomLogoutHandler customLogoutHandler) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customLogoutHandler = customLogoutHandler;
    }

    /**
     * Configures the security filter chain for HTTP requests.
     *
     * @param http HttpSecurity object used to configure security settings.
     * @return SecurityFilterChain with the defined security configurations.
     * @throws Exception in case of configuration errors.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(@NonNull HttpSecurity http) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(req -> req
                        .requestMatchers("/login/**", "/register/**", "/p/**").permitAll()
                        .requestMatchers("/a/**").hasAuthority("ADMIN").requestMatchers("/u/**")
                        .hasAnyAuthority("ADMIN", "USER").requestMatchers("/ua/**").hasAnyAuthority("ADMIN", "USER")
                        .anyRequest().authenticated())

                .userDetailsService(userDetailsServiceImpl)

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling(e -> e
                        .accessDeniedHandler((request, response, accessDeniedException) -> response.setStatus(403))
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))

                .logout(l -> l.logoutUrl("/logout").addLogoutHandler(customLogoutHandler).logoutSuccessHandler(
                        (request, response, authentication) -> SecurityContextHolder.clearContext()))

                .build();
    }

    /**
     * Configures the password encoder used for securing user passwords.
     *
     * @return BCryptPasswordEncoder as the password encoding implementation.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the AuthenticationManager, which is used to authenticate users.
     *
     * @param configuration the AuthenticationConfiguration object that provides the manager.
     * @return AuthenticationManager used to handle authentication requests.
     * @throws Exception in case of configuration errors.
     */
    @Bean
    public AuthenticationManager authenticationManager(@NonNull AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
