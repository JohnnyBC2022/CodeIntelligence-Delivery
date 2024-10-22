package com.codeintelligence.delivery.serviceImpl;

import com.codeintelligence.delivery.model.truckdrivertruck.TruckDriverTruckEntity;
import com.codeintelligence.delivery.repository.TruckDriverTruckRepository;
import com.codeintelligence.delivery.service.TruckDriverTruckService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the TruckDriverTruckService interface.
 * This service manages CRUD operations for the TruckDriverTruckEntity.
 */
@Service
public class TruckDriverTruckServiceImpl implements TruckDriverTruckService {

    private final TruckDriverTruckRepository truckDriverTruckRepository;

    /**
     * Constructor for TruckDriverTruckServiceImpl.
     *
     * @param truckDriverTruckRepository Repository used to perform CRUD operations on Truck entities.
     */
    public TruckDriverTruckServiceImpl(TruckDriverTruckRepository truckDriverTruckRepository) {
        this.truckDriverTruckRepository = truckDriverTruckRepository;
    }


    /**
     * Saves a TruckDriverTruckEntity to the database.
     *
     * @param truckDriverTruck The TruckDriverTruckEntity to be saved.
     * @return The saved TruckDriverTruckEntity.
     * @throws RuntimeException if the provided entity is invalid or if an error occurs during saving.
     */
    @Override
    public TruckDriverTruckEntity saveTruckDriverTruck(TruckDriverTruckEntity truckDriverTruck) {
        if (truckDriverTruck == null || !isValidTruckDriverTruck(truckDriverTruck)) {
            throw new RuntimeException("Invalid truck-driver truck data. Make sure all required fields are filled.");
        }

        try {
            return truckDriverTruckRepository.save(truckDriverTruck);
        } catch (Exception e) {
            System.out.println("[saveTruckDriverTruck] exception: " + e.getMessage());
            throw new RuntimeException("Error saving truck-driver truck: " + e.getMessage());
        }
    }

    /**
     * Validates the TruckDriverTruckEntity.
     *
     * @param truckDriverTruck The TruckDriverTruckEntity to validate.
     * @return true if valid; false otherwise.
     */
    private boolean isValidTruckDriverTruck(TruckDriverTruckEntity truckDriverTruck) {
        return truckDriverTruck.getTruck() != null &&
                truckDriverTruck.getTruckDriver() != null &&
                truckDriverTruck.getDate() != null;
    }


    /**
     * Retrieves all TruckDriverTruckEntity from the database.
     *
     * @return A list of TruckDriverTruckEntity or an empty list if none found.
     * @throws RuntimeException if an error occurs during retrieval.
     */
    @Override
    public List<TruckDriverTruckEntity> findAllTruckDriversTrucks() {
        try {
            List<TruckDriverTruckEntity> trucksDriverTrucks = truckDriverTruckRepository.findAll();
            if (trucksDriverTrucks.isEmpty()) {
                System.out.println("No trucks - driver trucks found.");
                return Collections.emptyList();
            }
            return trucksDriverTrucks;
        } catch (Exception e) {
            System.out.println("[findAllTrucksDriverTrucks] exception: " + e.getMessage());
            throw new RuntimeException("Error retrieving trucks - driver trucks: " + e.getMessage());
        }
    }

    /**
     * Finds a TruckDriverTruckEntity by its ID.
     *
     * @param id The ID of the TruckDriverTruckEntity to find.
     * @return An Optional containing the TruckDriverTruckEntity if found, or empty if not.
     * @throws IllegalArgumentException if the ID is invalid.
     * @throws RuntimeException         if an error occurs during retrieval.
     */
    @Override
    public Optional<TruckDriverTruckEntity> findTruckDriverTruckById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid truck - driver truck ID.");
        }

        try {
            Optional<TruckDriverTruckEntity> truckDriverTruck = truckDriverTruckRepository.findById(id);
            if (truckDriverTruck.isEmpty()) {
                System.out.println("Truck - driver truck with ID " + id + " not found.");
            }
            return truckDriverTruck;
        } catch (Exception e) {
            System.out.println("[findTruckDriverTruckById] exception: " + e.getMessage());
            throw new RuntimeException("Error retrieving truck - driver truck by ID: " + e.getMessage());
        }
    }

    /**
     * Updates a TruckDriverTruckEntity by its ID.
     *
     * @param id               The ID of the TruckDriverTruckEntity to update.
     * @param truckDriverTruck The TruckDriverTruckEntity containing updated information.
     * @return An Optional containing the updated TruckDriverTruckEntity if successful, or empty if not found.
     * @throws IllegalArgumentException if the ID is invalid.
     * @throws RuntimeException         if the provided entity is invalid or if an error occurs during the update.
     */
    @Override
    public Optional<TruckDriverTruckEntity> updateTruckDriverTruckById(Long id, TruckDriverTruckEntity truckDriverTruck) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid truck - driver truck ID.");
        }

        if (truckDriverTruck == null || !isValidTruckDriverTruck(truckDriverTruck)) {
            throw new RuntimeException("Invalid truck - driver truck data. Make sure all required fields are filled.");
        }

        try {
            Optional<TruckDriverTruckEntity> existingTruckDriverTruck = truckDriverTruckRepository.findById(id);

            if (existingTruckDriverTruck.isPresent()) {
                TruckDriverTruckEntity updatedTruckDriverTruck = existingTruckDriverTruck.get();
                updatedTruckDriverTruck.setDate(truckDriverTruck.getDate());


                truckDriverTruckRepository.save(updatedTruckDriverTruck);
                return Optional.of(updatedTruckDriverTruck);
            } else {
                System.out.println("Truck - driver truck with ID " + id + " not found.");
                return Optional.empty();
            }

        } catch (Exception e) {
            System.out.println("[updateTruckDriverTruckById] exception: " + e.getMessage());
            throw new RuntimeException("Error updating truck - driver truck by ID: " + e.getMessage());
        }
    }

    /**
     * Deletes a TruckDriverTruckEntity by its ID.
     *
     * @param id The ID of the TruckDriverTruckEntity to delete.
     * @throws IllegalArgumentException if the ID is invalid.
     * @throws RuntimeException         if an error occurs during deletion.
     */
    @Override
    public void deleteTruckDriverTruckById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid truck - driver truck ID.");
        }

        try {
            Optional<TruckDriverTruckEntity> truckDriverTruckOptional = truckDriverTruckRepository.findById(id);

            if (truckDriverTruckOptional.isPresent()) {
                truckDriverTruckRepository.delete(truckDriverTruckOptional.get());
                System.out.println("Truck - driver truck with ID " + id + " has been deleted.");
            } else {
                System.out.println("Truck - driver truck with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("[deleteTruckDriverTruckById] exception: " + e.getMessage());
            throw new RuntimeException("Error deleting truck - driver truck by ID: " + e.getMessage());
        }
    }
}
