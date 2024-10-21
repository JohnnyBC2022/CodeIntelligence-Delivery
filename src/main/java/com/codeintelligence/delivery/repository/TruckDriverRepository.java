package com.codeintelligence.delivery.repository;

import com.codeintelligence.delivery.model.truckdriver.TruckDriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * Repository interface for the TruckDriver entity.
 * Provides methods to perform CRUD operations on truck drivers.
 */
@Repository
public interface TruckDriverRepository extends JpaRepository<TruckDriverEntity,Long> {

}
