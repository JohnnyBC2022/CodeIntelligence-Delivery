package com.codeintelligence.delivery.repository;

import com.codeintelligence.delivery.model.truckdrivertruck.TruckDriverTruckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository interface for the TruckDriverTruck entity.
 * Provides methods to perform CRUD operations on table with relationship between truck drivers and trucks.
 */
@Repository
public interface TruckDriverTruckRepository extends JpaRepository<TruckDriverTruckEntity, Long> {
}
