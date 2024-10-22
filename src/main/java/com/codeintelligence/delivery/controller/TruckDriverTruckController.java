package com.codeintelligence.delivery.controller;

import com.codeintelligence.delivery.model.truckdrivertruck.TruckDriverTruckDTO;
import com.codeintelligence.delivery.model.truckdrivertruck.TruckDriverTruckEntity;
import com.codeintelligence.delivery.service.TruckDriverTruckService;
import com.codeintelligence.delivery.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller for managing the relationship between truck drivers and trucks.
 */
@RestController
@RequestMapping("/api/truck-driver-trucks")
public class TruckDriverTruckController {

    private final TruckDriverTruckService truckDriverTruckService;

    @Autowired
    public TruckDriverTruckController(TruckDriverTruckService truckDriverTruckService) {
        this.truckDriverTruckService = truckDriverTruckService;
    }

    /**
     * Assigns a truck to a truck driver.
     *
     * @param truckDriverTruckDTO the DTO of the truck-driver-truck relationship to create
     * @return ResponseEntity with the created relationship's DTO and HTTP status 201 (CREATED)
     */
    @PostMapping(value = "/assign")
    public ResponseEntity<TruckDriverTruckDTO> assignTruckToDriver(@RequestBody TruckDriverTruckDTO truckDriverTruckDTO) {
        try {
            TruckDriverTruckEntity truckDriverTruck = EntityConverter.convertToTruckDriverTruckEntity(truckDriverTruckDTO);
            TruckDriverTruckEntity createdTruckDriverTruck = truckDriverTruckService.saveTruckDriverTruck(truckDriverTruck);
            return new ResponseEntity<>(EntityConverter.convertToTruckDriverTruckDTO(createdTruckDriverTruck), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves all truck-driver-truck assignments.
     *
     * @return ResponseEntity with the list of truck-driver-truck DTOs and HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<TruckDriverTruckDTO>> getAllTruckDriverTrucks() {
        try {
            List<TruckDriverTruckEntity> assignments = truckDriverTruckService.findAllTruckDriversTrucks();
            List<TruckDriverTruckDTO> assignmentDTOs = assignments.stream()
                    .map(EntityConverter::convertToTruckDriverTruckDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(assignmentDTOs, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TruckDriverTruckDTO> getTruckDriverTruckById(@PathVariable Long id) {
        try {
            Optional<TruckDriverTruckEntity> assignment = truckDriverTruckService.findTruckDriverTruckById(id);
            return assignment.map(a -> new ResponseEntity<>(EntityConverter.convertToTruckDriverTruckDTO(a), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTruckDriverTruckById(@PathVariable Long id) {
        try {
            truckDriverTruckService.deleteTruckDriverTruckById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
