package com.codeintelligence.delivery.service;

import com.codeintelligence.delivery.model.truckdriver.TruckDriverEntity;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing truck drivers
 */

public interface TruckDriverService {

    TruckDriverEntity saveTruckDriver(TruckDriverEntity truckDriver);

    List<TruckDriverEntity> findAllTruckDrivers();

    Optional<TruckDriverEntity> findTruckDriverByDni(String dni);

    Optional<TruckDriverEntity> updateTruckDriverByDni(String dni, TruckDriverEntity truckDriver);

    void deleteTruckDriverByDni(String dni);

}
