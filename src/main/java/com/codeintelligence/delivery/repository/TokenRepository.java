package com.codeintelligence.delivery.repository;

import com.codeintelligence.delivery.model.token.TokenEntity;
import com.codeintelligence.delivery.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for the token entity.
 * Provides methods to perform CRUD operations on trucks.
 */
@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    @Query("SELECT t FROM TokenEntity t WHERE t.user.id = :id")
    List<TokenEntity> findAllTokensByUser(@Param("id") Long userId);


    Optional<TokenEntity> findByToken(String token);
}
