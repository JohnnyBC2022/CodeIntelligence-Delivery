package com.codeintelligence.delivery.controller;

import com.codeintelligence.delivery.model.truck.TruckDTO;
import com.codeintelligence.delivery.model.truck.TruckEntity;
import com.codeintelligence.delivery.service.TruckService;
import com.codeintelligence.delivery.utils.EntityConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller for managing truck operations.
 */
@RestController
@RequestMapping("/api/trucks")
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    /**
     * Creates a new truck.
     *
     * @param truckDTO the DTO of the truck to create
     * @return ResponseEntity with the created truck's DTO and HTTP status 201 (CREATED)
     */
    @PostMapping(value = "/save")
    public ResponseEntity<TruckDTO> createTruck(@RequestBody TruckDTO truckDTO) {
        try {
            TruckEntity truck = EntityConverter.convertToTruckEntity(truckDTO);
            TruckEntity createdTruck = truckService.saveTruck(truck);
            return new ResponseEntity<>(EntityConverter.convertToTruckDTO(createdTruck), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves all trucks.
     *
     * @return ResponseEntity with the list of truck DTOs and HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<TruckDTO>> getAllTrucks() {
        try {
            List<TruckEntity> trucks = truckService.findAllTrucks();
            List<TruckDTO> truckDTOs = trucks.stream()
                    .map(EntityConverter::convertToTruckDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(truckDTOs, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a truck by its ID.
     *
     * @param id the ID of the truck
     * @return ResponseEntity with the found truck's DTO or HTTP status 404 (NOT FOUND)
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<TruckDTO> getTruckById(@PathVariable Long id) {
        try {
            Optional<TruckEntity> truck = truckService.findTruckById(id);
            return truck.map(t -> new ResponseEntity<>(EntityConverter.convertToTruckDTO(t), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates a truck by its ID.
     *
     * @param id        the ID of the truck to update
     * @param truckDTO  the updated truck's DTO
     * @return ResponseEntity with the updated truck's DTO or HTTP status 404 (NOT FOUND)
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<TruckDTO> updateTruckById(@PathVariable Long id, @RequestBody TruckDTO truckDTO) {
        try {
            TruckEntity truck = EntityConverter.convertToTruckEntity(truckDTO);
            Optional<TruckEntity> updatedTruck = truckService.updateTruckById(id, truck);
            return updatedTruck.map(t -> new ResponseEntity<>(EntityConverter.convertToTruckDTO(t), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a truck by its ID.
     *
     * @param id the ID of the truck to delete
     * @return ResponseEntity with HTTP status 204 (NO CONTENT) if deletion was successful
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTruckById(@PathVariable Long id) {
        try {
            truckService.deleteTruckById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
