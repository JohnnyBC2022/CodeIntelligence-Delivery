package com.codeintelligence.delivery.repository;


import com.codeintelligence.delivery.model.city.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the City entity.
 * Provides methods to perform CRUD operations on trucks.
 */
@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
}
