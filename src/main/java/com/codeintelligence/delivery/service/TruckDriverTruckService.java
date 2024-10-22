package com.codeintelligence.delivery.service;

import com.codeintelligence.delivery.model.truckdrivertruck.TruckDriverTruckEntity;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing trucks and truck drivers.
 */
public interface TruckDriverTruckService {

    TruckDriverTruckEntity saveTruckDriverTruck(TruckDriverTruckEntity truckDriverTruck);

    List<TruckDriverTruckEntity> findAllTruckDriversTrucks();

    Optional<TruckDriverTruckEntity> findTruckDriverTruckById(Long id);

    Optional<TruckDriverTruckEntity> updateTruckDriverTruckById(Long id, TruckDriverTruckEntity truckDriverTruck);

    void deleteTruckDriverTruckById(Long id);
}
