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
 * REST Controller for managing truck drivers.
 */
@RestController
@RequestMapping("/api/truck-drivers")
public class TruckDriverController {
    private final TruckDriverService truckDriverService;

    /**
     * Constructs a TruckDriverController with the specified TruckDriverService.
     *
     * @param truckDriverService the service used to perform CRUD operations on truck drivers     *
     */
    @Autowired
    public TruckDriverController(TruckDriverService truckDriverService){
        this.truckDriverService = truckDriverService;
    }

    /**
     * Handles the creation of a new truck driver.
     *
     * @param truckDriverDTO the DTO containing the information of the truck driver to be created
     * @return a ResponseEntity containing the created TruckDriverDTO and the HTTP status code
     */
    @PostMapping(value = "save")
    public ResponseEntity<TruckDriverDTO> createTruckDriver(@RequestBody TruckDriverDTO truckDriverDTO) {
        TruckDriverEntity truckDriver = convertToEntity(truckDriverDTO);
        TruckDriverEntity createdTruckDriver = truckDriverService.saveTruckDriver(truckDriver);
        return new ResponseEntity<>(convertToDTO(createdTruckDriver), HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all truck drivers.
     *
     * @return a ResponseEntity containing a list of TruckDriverDTOs and the HTTP status code
     */
    @GetMapping
    public ResponseEntity<List<TruckDriverDTO>> getAllTruckDrivers() {
        List<TruckDriverEntity> truckDrivers = truckDriverService.findAllTruckDrivers();
        List<TruckDriverDTO> truckDriverDTOs = truckDrivers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(truckDriverDTOs, HttpStatus.OK);
    }

    /**
     * Finds a truck driver by their ID.
     *
     * @param id The ID of the truck driver to find.
     * @return The response with the found truck driver.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<TruckDriverDTO> getTruckDriverById(@PathVariable Long id) { // Cambiado de dni a id
        Optional<TruckDriverEntity> truckDriver = truckDriverService.findTruckDriverById(id);
        return truckDriver.map(driver -> new ResponseEntity<>(convertToDTO(driver), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Updates a truck driver by their ID.
     *
     * @param id The ID of the truck driver to update.
     * @param truckDriverDTO The DTO object of the truck driver with new data.
     * @return The response with the updated truck driver.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<TruckDriverDTO> updateTruckDriverById(@PathVariable Long id, @RequestBody TruckDriverDTO truckDriverDTO) {
        TruckDriverEntity truckDriver = convertToEntity(truckDriverDTO);
        Optional<TruckDriverEntity> updatedTruckDriver = truckDriverService.updateTruckDriverById(id, truckDriver);
        return updatedTruckDriver.map(driver -> new ResponseEntity<>(convertToDTO(driver), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Deletes a truck driver by their ID.
     *
     * @param id The ID of the truck driver to delete.
     * @return The response for the deletion.
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

    // Métodos de conversión de entidades

    /**
     * Converts a TruckDriverEntity to a TruckDriverDTO.
     *
     * @param truckDriver the TruckDriverEntity object to convert
     * @return the corresponding TruckDriverDTO object
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
     * Converts a TruckDriverDTO to a TruckDriverEntity.
     *
     * @param dto the TruckDriverDTO object to convert
     * @return the corresponding TruckDriverEntity object
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
