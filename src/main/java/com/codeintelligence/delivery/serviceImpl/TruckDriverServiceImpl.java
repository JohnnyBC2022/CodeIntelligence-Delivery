package com.codeintelligence.delivery.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeintelligence.delivery.model.truckdriver.TruckDriverDTO;
import com.codeintelligence.delivery.repository.TruckDriverRepository;
import com.codeintelligence.delivery.service.TruckDriverService;
import com.codeintelligence.delivery.utilities.Mapper;

/**
 * Service implementation for managing truck drivers
 */
@Service
public class TruckDriverServiceImpl implements TruckDriverService {

    private final TruckDriverRepository truckDriverRepository;
    Mapper mapper;

    /**
     * Constructs a TruckDriverServiceImpl with the specified TruckDriverRepository.
     *
     * @param truckDriverRepository the repository used to perform CRUD operations on truck drivers
     */
    public TruckDriverServiceImpl(TruckDriverRepository truckDriverRepository, Mapper mapper) {
        this.truckDriverRepository = truckDriverRepository;
        this.mapper = mapper;
    }

    /**
     * Saves a truck driver entity to the database.
     *
     * @param truckDriver the TruckDriverEntity object to be saved
     * @return the saved TruckDriverEntity object
     * @throws RuntimeException if there is an error while saving the truck driver
     */
    @Override
    public TruckDriverDTO saveTruckDriver(TruckDriverDTO truckDriver) {
        if (truckDriver == null || truckDriver.getName() == null || truckDriver.getPhone() == null) {
            throw new IllegalArgumentException("TruckDriverEntity cannot be null or missing required fields.");
        }

        try {
            return mapper.convertToDTO(truckDriverRepository.save(mapper.convertToEntity(truckDriver))); // Podria simplificarse
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
    public List<TruckDriverDTO> findAllTruckDrivers() {
        try { 
            List<TruckDriverDTO> truckDrivers = truckDriverRepository.findAll().stream()
            		.map(t -> mapper.convertToDTO(t)) // Se mapea la entidad a Dto
            		.toList();
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
    public Optional<TruckDriverDTO> findTruckDriverById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }

        try {
            return truckDriverRepository.findById(id).map(t -> mapper.convertToDTO(t));
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
    public Optional<TruckDriverDTO> updateTruckDriverById(Long id, TruckDriverDTO truckDriver) {
        if (id == null || truckDriver == null) {
            throw new IllegalArgumentException("ID and TruckDriverEntity cannot be null.");
        }

        try {
            Optional<TruckDriverDTO> existingTruckDriver = truckDriverRepository.findById(id).map(t -> mapper.convertToDTO(t));

            if (existingTruckDriver.isPresent()) {
            	TruckDriverDTO updatedTruckDriver = existingTruckDriver.get(); // Obtenemos el DTO
                updatedTruckDriver.setName(truckDriver.getName());
                updatedTruckDriver.setPhone(truckDriver.getPhone());
                updatedTruckDriver.setAddress(truckDriver.getAddress());
                updatedTruckDriver.setSalary(truckDriver.getSalary());

                truckDriverRepository.save(mapper.convertToEntity(updatedTruckDriver)); // Guardamos la entity mapeando el DTO
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
            Optional<TruckDriverDTO> truckDriverOptional = truckDriverRepository.findById(id).map(t -> mapper.convertToDTO(t));

            if (truckDriverOptional.isPresent()) {
                truckDriverRepository.delete(mapper.convertToEntity(truckDriverOptional.get()));
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
