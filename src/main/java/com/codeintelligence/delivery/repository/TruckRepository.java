package com.codeintelligence.delivery.repository;

import com.codeintelligence.delivery.model.truck.TruckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the Truck entity.
 * Provides methods to perform CRUD operations on trucks.
 */
@Repository
public interface TruckRepository extends JpaRepository<TruckEntity, Long> {
}
