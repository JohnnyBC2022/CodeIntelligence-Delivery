package com.codeintelligence.delivery.repository;

import com.codeintelligence.delivery.model.token.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the token entity.
 * Provides methods to perform CRUD operations on trucks.
 */
@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
}
