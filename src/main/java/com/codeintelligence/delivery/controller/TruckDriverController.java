package com.codeintelligence.delivery.controller;

import com.codeintelligence.delivery.model.truckdriver.TruckDriverDTO;
import com.codeintelligence.delivery.model.truckdriver.TruckDriverEntity;
import com.codeintelligence.delivery.service.TruckDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller for managing truck driver operations.
 */
@RestController
@RequestMapping("/api/truck-drivers")
public class TruckDriverController {

    private final TruckDriverService truckDriverService;

    @Autowired
    public TruckDriverController(TruckDriverService truckDriverService) {
        this.truckDriverService = truckDriverService;
    }

    /**
     * Creates a new truck driver.
     *
     * @param truckDriverDTO the DTO of the truck driver to create
     * @return ResponseEntity with the created truck driver's DTO and HTTP status 201 (CREATED)
     */
    @PostMapping(value = "/save")
    public ResponseEntity<TruckDriverDTO> createTruckDriver(@RequestBody TruckDriverDTO truckDriverDTO) {
        try {
            TruckDriverEntity truckDriver = convertToEntity(truckDriverDTO);
            TruckDriverEntity createdTruckDriver = truckDriverService.saveTruckDriver(truckDriver);
            return new ResponseEntity<>(convertToDTO(createdTruckDriver), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves all truck drivers.
     *
     * @return ResponseEntity with the list of truck driver DTOs and HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<TruckDriverDTO>> getAllTruckDrivers() {
        try {
            List<TruckDriverEntity> truckDrivers = truckDriverService.findAllTruckDrivers();
            List<TruckDriverDTO> truckDriverDTOs = truckDrivers.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(truckDriverDTOs, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a truck driver by their ID.
     *
     * @param id the ID of the truck driver
     * @return ResponseEntity with the found truck driver's DTO or HTTP status 404 (NOT FOUND)
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<TruckDriverDTO> getTruckDriverById(@PathVariable Long id) {
        try {
            Optional<TruckDriverEntity> truckDriver = truckDriverService.findTruckDriverById(id);
            return truckDriver.map(driver -> new ResponseEntity<>(convertToDTO(driver), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates a truck driver by their ID.
     *
     * @param id             the ID of the truck driver to update
     * @param truckDriverDTO the updated truck driver's DTO
     * @return ResponseEntity with the updated truck driver's DTO or HTTP status 404 (NOT FOUND)
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<TruckDriverDTO> updateTruckDriverById(@PathVariable Long id, @RequestBody TruckDriverDTO truckDriverDTO) {
        try {
            TruckDriverEntity truckDriver = convertToEntity(truckDriverDTO);
            Optional<TruckDriverEntity> updatedTruckDriver = truckDriverService.updateTruckDriverById(id, truckDriver);
            return updatedTruckDriver.map(driver -> new ResponseEntity<>(convertToDTO(driver), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a truck driver by their ID.
     *
     * @param id the ID of the truck driver to delete
     * @return ResponseEntity with HTTP status 204 (NO CONTENT) if deletion was successful
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTruckDriverById(@PathVariable Long id) {
        try {
            truckDriverService.deleteTruckDriverById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Conversion methods between entities

    /**
     * Converts a truck driver entity to its corresponding DTO.
     *
     * @param truckDriver the truck driver entity
     * @return the truck driver DTO
     */
    private TruckDriverDTO convertToDTO(TruckDriverEntity truckDriver) {
        TruckDriverDTO dto = new TruckDriverDTO();
        dto.setId(truckDriver.getId());
        dto.setDni(truckDriver.getDni());
        dto.setName(truckDriver.getName());
        dto.setPhone(truckDriver.getPhone());
        dto.setAddress(truckDriver.getAddress());
        dto.setSalary(truckDriver.getSalary());
        return dto;
    }

    /**
     * Converts a truck driver DTO to its corresponding entity.
     *
     * @param dto the truck driver DTO
     * @return the truck driver entity
     */
    private TruckDriverEntity convertToEntity(TruckDriverDTO dto) {
        TruckDriverEntity truckDriver = new TruckDriverEntity();
        truckDriver.setId(dto.getId());
        truckDriver.setDni(dto.getDni());
        truckDriver.setName(dto.getName());
        truckDriver.setPhone(dto.getPhone());
        truckDriver.setAddress(dto.getAddress());
        truckDriver.setSalary(dto.getSalary());
        return truckDriver;
    }
}
