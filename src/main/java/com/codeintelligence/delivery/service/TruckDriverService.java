package com.codeintelligence.delivery.service;

import java.util.List;
import java.util.Optional;

import com.codeintelligence.delivery.model.truckdriver.TruckDriverDTO;

/**
 * Service interface for managing truck drivers
 */
public interface TruckDriverService {

    TruckDriverDTO saveTruckDriver(TruckDriverDTO truckDriver);

    List<TruckDriverDTO> findAllTruckDrivers();

    Optional<TruckDriverDTO> findTruckDriverById(Long id);

    Optional<TruckDriverDTO> updateTruckDriverById(Long id, TruckDriverDTO truckDriver);

    void deleteTruckDriverById(Long id);
}
