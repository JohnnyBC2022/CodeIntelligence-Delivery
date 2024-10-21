package com.codeintelligence.delivery.serviceImpl;

import com.codeintelligence.delivery.model.truckdriver.TruckDriverEntity;
import com.codeintelligence.delivery.repository.TruckDriverRepository;
import com.codeintelligence.delivery.service.TruckDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
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
        try {
            return truckDriverRepository.save(truckDriver);
        } catch (Exception e) {
            System.out.println("[saveTruckDriver] exception: " + e.getMessage());
            throw new RuntimeException("Error saving truck driver: " + e.getMessage());
        }
    }


    /**
     * Retrieves all truck driver entities from the database.
     *
     * @return a list of all TruckDriverEntity objects
     * @throws RuntimeException if there is an error while retrieving the truck drivers
     */
    @Override
    public List<TruckDriverEntity> findAllTruckDrivers() {
        try {
            return truckDriverRepository.findAll();
        } catch (Exception e) {
            System.out.println("[findAllTruckDrivers] exception: " + e.getMessage());
            throw new RuntimeException("Error retrieving truck drivers: " + e.getMessage());
        }
    }


    /**
     * Retrieves a truck driver entity by its DNI.
     *
     * @param dni the DNI of the truck driver to retrieve
     * @return an Optional containing the found TruckDriverEntity, or empty if not found
     * @throws RuntimeException if there is an error while retrieving the truck driver
     */
    @Override
    public Optional<TruckDriverEntity> findTruckDriverByDni(String dni) {
        try {
            return truckDriverRepository.findByDni(dni);
        } catch (Exception e) {
            System.out.println("[findTruckDriverByDni] exception: " + e.getMessage());
            throw new RuntimeException("Error retrieving truck driver by DNI: " + e.getMessage());
        }
    }

    /**
     * Updates a truck driver entity by its DNI.
     *
     * @param dni         the DNI of the truck driver to update
     * @param truckDriver the truck driver entity with updated data
     * @return an Optional containing the updated TruckDriverEntity, or empty if not found
     */

    @Override
    public Optional<TruckDriverEntity> updateTruckDriverByDni(String dni, TruckDriverEntity truckDriver) {
        try {
            Optional<TruckDriverEntity> existingTruckDriver = truckDriverRepository.findByDni(dni);

            if (existingTruckDriver.isPresent()) {
                TruckDriverEntity updatedTruckDriver = existingTruckDriver.get();
                updatedTruckDriver.setName(truckDriver.getName());
                updatedTruckDriver.setPhone(truckDriver.getPhone());
                updatedTruckDriver.setAddress(truckDriver.getAddress());
                updatedTruckDriver.setSalary(truckDriver.getSalary());

                truckDriverRepository.save(updatedTruckDriver);
                return Optional.of(updatedTruckDriver);
            } else {
                System.out.println("Truck driver with DNI " + dni + " not found.");
                return Optional.empty();
            }
        } catch (Exception e) {
            System.out.println("[updateTruckDriverByDni] exception: " + e.getMessage());
            throw new RuntimeException("Error updating truck driver by DNI: " + e.getMessage());
        }
    }

    /**
     * Deletes a truck driver entity by its DNI.
     *
     * @param dni the DNI of the truck driver to delete
     */

    @Override
    public void deleteTruckDriverByDni(String dni) {
        try {
            Optional<TruckDriverEntity> truckDriverOptional = truckDriverRepository.findByDni(dni);

            if (truckDriverOptional.isPresent()) {
                truckDriverRepository.delete(truckDriverOptional.get());
                System.out.println("Truck driver with DNI " + dni + " has been deleted.");
            } else {
                System.out.println("Truck driver with DNI " + dni + " not found.");
            }
        } catch (Exception e) {
            System.out.println("[deleteByDni] exception: " + e.getMessage());
            throw new RuntimeException("Error deleting truck driver by DNI: " + e.getMessage());
        }
    }

}
