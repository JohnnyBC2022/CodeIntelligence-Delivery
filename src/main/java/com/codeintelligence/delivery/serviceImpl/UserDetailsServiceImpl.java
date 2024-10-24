package com.codeintelligence.delivery.serviceImpl;

import com.codeintelligence.delivery.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * Implementation of the UserDetailsService interface for loading user-specific data.
 * This service is used by Spring Security to retrieve user details for authentication.
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * Constructs a UserDetailsServiceImpl with the specified UserRepository.
     *
     * @param userRepository the repository used to access user data
     */
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads user details by username.
     *
     * @param username the username of the user to be loaded
     * @return a UserDetails object representing the user
     * @throws UsernameNotFoundException if no user is found with the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User has not found"));
    }
}
