package com.codeintelligence.delivery.service;

import java.util.List;
import java.util.Optional;

import com.codeintelligence.delivery.model.truck.TruckDTO;

/**
 * Service interface for managing trucks
 */
public interface TruckService {
	TruckDTO saveTruck(TruckDTO truck);

    List<TruckDTO> findAllTrucks();

    Optional<TruckDTO> findTruckById(Long id);

    Optional<TruckDTO> updateTruckById(Long id, TruckDTO truck);

    void deleteTruckById(Long id);
}
