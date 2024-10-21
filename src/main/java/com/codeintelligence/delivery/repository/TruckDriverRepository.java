package com.codeintelligence.delivery.repository;

import com.codeintelligence.delivery.model.truckdriver.TruckDriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for the TruckDriver entity.
 * Provides methods to perform CRUD operations on truck drivers.
 */
@Repository
public interface TruckDriverRepository extends JpaRepository<TruckDriverEntity,Long> {

    /**
     * Finds a truck driver entity by its DNI.
     *
     * @param dni the DNI of the truck driver to retrieve
     * @return an Optional containing the found TruckDriverEntity, or empty if not found
     */
    Optional<TruckDriverEntity> findByDni(String dni);
}
