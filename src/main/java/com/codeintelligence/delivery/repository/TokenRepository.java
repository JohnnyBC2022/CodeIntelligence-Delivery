package com.codeintelligence.delivery.repository;

import com.codeintelligence.delivery.model.token.TokenEntity;
import com.codeintelligence.delivery.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for the token entity.
 * Provides methods to perform CRUD operations on trucks.
 */
@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    List<TokenEntity> findAllTokensByUser(UserEntity user);

    Optional<TokenEntity> findByToken(String token);
}
