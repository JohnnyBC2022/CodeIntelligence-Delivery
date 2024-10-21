package com.codeintelligence.delivery.service;

import com.codeintelligence.delivery.model.truck.TruckEntity;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing trucks
 */
public interface TruckService {
    TruckEntity saveTruck(TruckEntity truck);

    List<TruckEntity> findAllTrucks();

    Optional<TruckEntity> findTruckById(Long id);

    Optional<TruckEntity> updateTruckById(Long id, TruckEntity truck);

    void deleteTruckById(Long id);
}
