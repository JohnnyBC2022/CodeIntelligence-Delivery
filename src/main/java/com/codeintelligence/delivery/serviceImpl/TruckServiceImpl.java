package com.codeintelligence.delivery.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.codeintelligence.delivery.model.truck.TruckDTO;
import com.codeintelligence.delivery.repository.TruckRepository;
import com.codeintelligence.delivery.service.TruckService;
import com.codeintelligence.delivery.utilities.Mapper;

@Service
public class TruckServiceImpl implements TruckService {

    private final TruckRepository truckRepository;
    Mapper mapper;

    public TruckServiceImpl(TruckRepository truckRepository, Mapper mapper) {
        this.truckRepository = truckRepository;
        this.mapper = mapper;
    }

    /**
     * Saves a truck entity to the database after validating its data.
     *
     * @param truck the TruckEntity object to be saved
     * @return the saved TruckEntity object
     * @throws RuntimeException if there is an error while saving the truck or if the truck data is invalid
     */
    @Override
    public TruckDTO saveTruck(TruckDTO truck) {
    	
        if (truck == null || !isValidTruck(truck)) {
            throw new RuntimeException("Invalid truck data. Make sure all required fields are filled.");
        }
        
        try {
            truckRepository.save(mapper.convertToEntity(truck));
            return truck;
        } catch (Exception e) {
            System.out.println("[saveTruck] exception: " + e.getMessage());
            throw new RuntimeException("Error saving truck: " + e.getMessage());
        }
    }

    /**
     * Validates the truck data.
     *
     * @param truck the TruckEntity object to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidTruck(TruckDTO truck) {
        return StringUtils.hasText(truck.getLicensePlate()) &&
                StringUtils.hasText(truck.getModel()) &&
                truck.getKilometers() != null;
    }

    /**
     * Retrieves all truck entities from the database.
     *
     * @return a list of all TruckEntity objects, or an empty list if no trucks are found
     * @throws RuntimeException if there is an error while retrieving the trucks
     */
    @Override
    public List<TruckDTO> findAllTrucks() {
        try {
            List<TruckDTO> trucks = truckRepository.findAll().stream()
            		.map(t -> mapper.convertToDTO(t)) // Se mapea la entidad a Dto
            		.toList();
            
            if (trucks.isEmpty()) {
                System.out.println("No trucks found.");
                return Collections.emptyList();
            }
            return trucks;
        } catch (Exception e) {
            System.out.println("[findAllTrucks] exception: " + e.getMessage());
            throw new RuntimeException("Error retrieving trucks: " + e.getMessage());
        }
    }

    /**
     * Retrieves a truck entity by its ID.
     *
     * @param id the ID of the truck to retrieve
     * @return an Optional containing the found TruckEntity, or empty if not found
     * @throws RuntimeException if there is an error while retrieving the truck
     */
    @Override
    public Optional<TruckDTO> findTruckById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid truck ID.");
        }
 
        		
        try {
            Optional<TruckDTO> truck = truckRepository.findById(id)
            							.map(t -> mapper.convertToDTO(t)); // Mapeado para obtener el DTO
            if (truck.isEmpty()) {
                System.out.println("Truck with ID " + id + " not found.");
            }
            return truck;
        } catch (Exception e) {
            System.out.println("[findTruckById] exception: " + e.getMessage());
            throw new RuntimeException("Error retrieving truck by ID: " + e.getMessage());
        }
    }

    /**
     * Updates a truck entity by its ID.
     *
     * @param id    the ID of the truck to update
     * @param truck the truck entity with updated data
     * @return an Optional containing the updated TruckEntity, or empty if not found
     */
    @Override
    public Optional<TruckDTO> updateTruckById(Long id, TruckDTO truck) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid truck ID.");
        }

        if (truck == null || !isValidTruck(truck)) {
            throw new RuntimeException("Invalid truck data. Make sure all required fields are filled.");
        }

        try {
            Optional<TruckDTO> existingTruck = truckRepository.findById(id).map(t -> mapper.convertToDTO(t));

            if (existingTruck.isPresent()) {
            	TruckDTO updatedTruck = existingTruck.get();
                updatedTruck.setLicensePlate(truck.getLicensePlate());
                updatedTruck.setModel(truck.getModel());
                updatedTruck.setKilometers(truck.getKilometers());

                truckRepository.save(mapper.convertToEntity(updatedTruck));
                return Optional.of(updatedTruck);
            } else {
                System.out.println("Truck with ID " + id + " not found.");
                return Optional.empty();
            }

        } catch (Exception e) {
            System.out.println("[updateTruckById] exception: " + e.getMessage());
            throw new RuntimeException("Error updating truck by ID: " + e.getMessage());
        }
    }

    /**
     * Deletes a truck entity by its ID.
     *
     * @param id the ID of the truck to delete
     */
    @Override
    public void deleteTruckById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid truck ID.");
        }

        try {
            Optional<TruckDTO> truckOptional = truckRepository.findById(id).map(t -> mapper.convertToDTO(t));

            if (truckOptional.isPresent()) {
                truckRepository.delete(mapper.convertToEntity(truckOptional.get()));
                System.out.println("Truck with ID " + id + " has been deleted.");
            } else {
                System.out.println("Truck with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("[deleteTruckById] exception: " + e.getMessage());
            throw new RuntimeException("Error deleting truck by ID: " + e.getMessage());
        }
    }
}
