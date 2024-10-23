package com.codeintelligence.delivery.serviceImpl;

import com.codeintelligence.delivery.model.truckdriver.TruckDriverEntity;
import com.codeintelligence.delivery.repository.TruckDriverRepository;
import com.codeintelligence.delivery.service.TruckDriverService;
import com.codeintelligence.delivery.utils.EntityValidator;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing truck drivers
 */
@Service
public class TruckDriverServiceImpl implements TruckDriverService {

    private final TruckDriverRepository truckDriverRepository;

    /**
     * Constructs a TruckDriverServiceImpl with the specified TruckDriverRepository.
     *
     * @param truckDriverRepository the repository used to perform CRUD operations on truck drivers
     */
    public TruckDriverServiceImpl(TruckDriverRepository truckDriverRepository) {
        this.truckDriverRepository = truckDriverRepository;
    }

    /**
     * Saves a truck driver entity to the database.
     *
     * @param truckDriver the TruckDriverEntity object to be saved
     * @return the saved TruckDriverEntity object
     * @throws RuntimeException if there is an error while saving the truck driver
     */
    @Override
    public TruckDriverEntity saveTruckDriver(TruckDriverEntity truckDriver) {
        if (truckDriver == null || !EntityValidator.isValidTruckDriver(truckDriver)) {
            throw new IllegalArgumentException("TruckDriverEntity cannot be null or missing required fields.");
        }

        try {
            System.out.println("New truck driver created.");
            return truckDriverRepository.save(truckDriver);
        } catch (Exception e) {
            System.out.println("[saveTruckDriver] exception: " + e.getMessage());
            throw new RuntimeException("Error saving truck driver: " + e.getMessage());
        }
    }

    /**
     * Retrieves all truck driver entities from the database.
     *
     * @return a list of all TruckDriverEntity objects, or an empty list if none found
     * @throws RuntimeException if there is an error while retrieving the truck drivers
     */
    @Override
    public List<TruckDriverEntity> findAllTruckDrivers() {
        try {
            List<TruckDriverEntity> truckDrivers = truckDriverRepository.findAll();
            System.out.println("All truck drivers has been found.");
            return truckDrivers.isEmpty() ? Collections.emptyList() : truckDrivers;
        } catch (Exception e) {
            System.out.println("[findAllTruckDrivers] exception: " + e.getMessage());
            throw new RuntimeException("Error retrieving truck drivers: " + e.getMessage());
        }
    }

    /**
     * Retrieves a truck driver entity by its ID.
     *
     * @param id the ID of the truck driver to retrieve
     * @return an Optional containing the found TruckDriverEntity, or empty if not found
     * @throws RuntimeException if there is an error while retrieving the truck driver
     */
    @Override
    public Optional<TruckDriverEntity> findTruckDriverById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }

        try {
            System.out.println("Truck driver with ID " + id + " has been found.");
            return truckDriverRepository.findById(id);
        } catch (Exception e) {
            System.out.println("[findTruckDriverById] exception: " + e.getMessage());
            throw new RuntimeException("Error retrieving truck driver by ID: " + e.getMessage());
        }
    }

    /**
     * Updates a truck driver entity by its ID.
     *
     * @param id          the ID of the truck driver to update
     * @param truckDriver the truck driver entity with updated data
     * @return an Optional containing the updated TruckDriverEntity, or empty if not found
     */
    @Override
    public Optional<TruckDriverEntity> updateTruckDriverById(Long id, TruckDriverEntity truckDriver) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid truck driver ID.");
        }

        if (truckDriver == null || !EntityValidator.isValidTruckDriver(truckDriver)) {
            throw new RuntimeException("Invalid truck driver data. Make sure all required fields are filled.");
        }

        try {
            Optional<TruckDriverEntity> existingTruckDriver = truckDriverRepository.findById(id);

            if (existingTruckDriver.isPresent()) {
                TruckDriverEntity updatedTruckDriver = existingTruckDriver.get();
                updatedTruckDriver.setName(truckDriver.getName());
                updatedTruckDriver.setPhone(truckDriver.getPhone());
                updatedTruckDriver.setAddress(truckDriver.getAddress());
                updatedTruckDriver.setSalary(truckDriver.getSalary());

                truckDriverRepository.save(updatedTruckDriver);
                System.out.println("Truck driver with ID " + id + " has been updated.");
                return Optional.of(updatedTruckDriver);
            } else {
                System.out.println("Truck driver with ID " + id + " not found.");
                return Optional.empty();
            }

        } catch (Exception e) {
            System.out.println("[updateTruckDriverById] exception: " + e.getMessage());
            throw new RuntimeException("Error updating truck driver by ID: " + e.getMessage());
        }
    }

    /**
     * Deletes a truck driver entity by its ID.
     *
     * @param id the ID of the truck driver to delete
     */
    @Override
    public void deleteTruckDriverById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }

        try {
            Optional<TruckDriverEntity> truckDriverOptional = truckDriverRepository.findById(id);

            if (truckDriverOptional.isPresent()) {
                truckDriverRepository.delete(truckDriverOptional.get());
                System.out.println("Truck driver with ID " + id + " has been deleted.");
            } else {
                System.out.println("Truck driver with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("[deleteTruckDriverById] exception: " + e.getMessage());
            throw new RuntimeException("Error deleting truck driver by ID: " + e.getMessage());
        }
    }
}
