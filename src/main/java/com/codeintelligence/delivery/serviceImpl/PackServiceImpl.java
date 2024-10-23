package com.codeintelligence.delivery.serviceImpl;

import com.codeintelligence.delivery.model.pack.PackEntity;
import com.codeintelligence.delivery.repository.PackRepository;
import com.codeintelligence.delivery.service.PackService;
import com.codeintelligence.delivery.utils.EntityValidator;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the PackService interface for managing packages.
 */
@Service
public class PackServiceImpl implements PackService {

    private final PackRepository packRepository;

    /**
     * Constructor for PackServiceImpl.
     *
     * @param packRepository Repository used to perform CRUD operations on Pack entities.
     */
    public PackServiceImpl(PackRepository packRepository) {
        this.packRepository = packRepository;
    }

    /**
     * Saves a package entity to the database after validating its data.
     *
     * @param pack the PackEntity object to be saved
     * @return the saved PackEntity object
     * @throws RuntimeException if there is an error while saving the package or if the package data is invalid
     */
    @Override
    public PackEntity savePack(PackEntity pack) {
        if (pack == null || !EntityValidator.isValidPack(pack)) {
            throw new RuntimeException("Invalid package data. Make sure all required fields are filled.");
        }

        try {
            System.out.println("New Package created.");
            return packRepository.save(pack);
        } catch (Exception e) {
            System.out.println("[savePack] exception: " + e.getMessage());
            throw new RuntimeException("Error saving package: " + e.getMessage());
        }
    }

    /**
     * Retrieves all package entities from the database.
     *
     * @return a list of all PackEntity objects, or an empty list if no packages are found
     * @throws RuntimeException if there is an error while retrieving the packages
     */
    @Override
    public List<PackEntity> findAllPacks() {
        try {
            List<PackEntity> packs = packRepository.findAll();
            if (packs.isEmpty()) {
                System.out.println("No packages found.");
                return Collections.emptyList();
            }
            System.out.println("All packages have been found.");
            return packs;
        } catch (Exception e) {
            System.out.println("[findAllPacks] exception: " + e.getMessage());
            throw new RuntimeException("Error retrieving packages: " + e.getMessage());
        }
    }

    /**
     * Retrieves a package entity by its ID.
     *
     * @param id the ID of the package to retrieve
     * @return an Optional containing the found PackEntity, or empty if not found
     * @throws RuntimeException if there is an error while retrieving the package
     */
    @Override
    public Optional<PackEntity> findPackById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid package ID.");
        }

        try {
            Optional<PackEntity> pack = packRepository.findById(id);
            if (pack.isEmpty()) {
                System.out.println("Package with ID " + id + " not found.");
            }
            System.out.println("Package with ID " + id + " has been found.");
            return pack;
        } catch (Exception e) {
            System.out.println("[findPackById] exception: " + e.getMessage());
            throw new RuntimeException("Error retrieving package by ID: " + e.getMessage());
        }
    }

    /**
     * Updates a package entity by its ID.
     *
     * @param id   the ID of the package to update
     * @param pack the package entity with updated data
     * @return an Optional containing the updated PackEntity, or empty if not found
     * @throws RuntimeException if there is an error while updating the package
     */
    @Override
    public Optional<PackEntity> updatePackById(Long id, PackEntity pack) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid package ID.");
        }

        if (pack == null || !EntityValidator.isValidPack(pack)) {
            throw new RuntimeException("Invalid package data. Make sure all required fields are filled.");
        }

        try {
            Optional<PackEntity> existingPack = packRepository.findById(id);

            if (existingPack.isPresent()) {
                PackEntity updatedPack = existingPack.get();
                updatedPack.setDescription(pack.getDescription());
                updatedPack.setDestinationAddress(pack.getDestinationAddress());

                packRepository.save(updatedPack);
                System.out.println("Package with ID " + id + " has been updated.");
                return Optional.of(updatedPack);
            } else {
                System.out.println("Package with ID " + id + " not found.");
                return Optional.empty();
            }

        } catch (Exception e) {
            System.out.println("[updatePackById] exception: " + e.getMessage());
            throw new RuntimeException("Error updating package by ID: " + e.getMessage());
        }
    }

    /**
     * Deletes a package entity by its ID.
     *
     * @param id the ID of the package to delete
     * @throws RuntimeException if there is an error while deleting the package
     */
    @Override
    public void deletePackById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid package ID.");
        }

        try {
            Optional<PackEntity> packOptional = packRepository.findById(id);

            if (packOptional.isPresent()) {
                packRepository.delete(packOptional.get());
                System.out.println("Package with ID " + id + " has been deleted.");
            } else {
                System.out.println("Package with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("[deletePackById] exception: " + e.getMessage());
            throw new RuntimeException("Error deleting package by ID: " + e.getMessage());
        }
    }
}
