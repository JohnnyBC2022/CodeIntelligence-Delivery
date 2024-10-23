package com.codeintelligence.delivery.repository;

import com.codeintelligence.delivery.model.pack.PackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the pack entity.
 * Provides methods to perform CRUD operations on trucks.
 */
@Repository
public interface PackRepository extends JpaRepository<PackEntity, Long> {
}
