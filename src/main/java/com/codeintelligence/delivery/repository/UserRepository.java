package com.codeintelligence.delivery.repository;

import com.codeintelligence.delivery.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for the user entity.
 * Provides methods to perform CRUD operations on trucks.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
