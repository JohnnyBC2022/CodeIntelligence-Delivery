package com.codeintelligence.delivery.serviceImpl;

import com.codeintelligence.delivery.model.user.Role;
import com.codeintelligence.delivery.model.user.UserDTO;
import com.codeintelligence.delivery.model.user.UserEntity;
import com.codeintelligence.delivery.repository.UserRepository;
import com.codeintelligence.delivery.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Retrieves the email of the currently logged-in user.
     *
     * @return the email address of the current user.
     */
    @Override
    public String getUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    /**
     * Retrieves the current user's details.
     *
     * @return a UserEntity object representing the current user.
     */
    @Override
    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found");
        }
        return (UserEntity) authentication.getPrincipal();
    }


    /**
     * Saves the provided user data.
     *
     * @param id       the unique identifier of the user to be updated.
     * @param userDTO  the UserDTO object containing the user data to save.
     */
    @Override
    public void save(Long id, UserDTO userDTO) {
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDTO, user);
        this.userRepository.save(user);
    }

    /**
     * Checks if the currently logged-in user has admin privileges.
     *
     * @return true if the user has admin privileges, false otherwise.
     */
    @Override
    public boolean isAdmin(@NonNull UserEntity user) {
        return user.getRole() == Role.ADMIN;
    }
}
