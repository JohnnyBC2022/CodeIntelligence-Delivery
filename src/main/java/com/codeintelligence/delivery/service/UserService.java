package com.codeintelligence.delivery.service;

import com.codeintelligence.delivery.model.user.UserDTO;
import com.codeintelligence.delivery.model.user.UserEntity;

/**
 * Service interface for managing user-related operations.
 * This interface defines the methods for user management,
 * including retrieving user information and saving user data.
 */
public interface UserService {
    String getUserEmail();

    UserEntity getCurrentUser();

    void save(Long id, UserDTO userDTO);

    boolean isAdmin(UserEntity user);

}
