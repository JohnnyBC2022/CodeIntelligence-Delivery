package com.codeintelligence.delivery.service;

import com.codeintelligence.delivery.model.user.UserDTO;
import com.codeintelligence.delivery.model.user.UserEntity;

public interface UserService {
    String getUserEmail();

    UserEntity getCurrentUser();

    void save(Long id, UserDTO userDTO);

    boolean isAdmin();

}
