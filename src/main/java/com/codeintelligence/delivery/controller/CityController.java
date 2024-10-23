package com.codeintelligence.delivery.controller;

import com.codeintelligence.delivery.model.city.CityDTO;
import com.codeintelligence.delivery.model.city.CityEntity;
import com.codeintelligence.delivery.service.CityService;
import com.codeintelligence.delivery.utils.EntityConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller for managing city operations.
 */
@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Creates a new city.
     *
     * @param cityDTO the DTO of the city to create
     * @return ResponseEntity with the created city's DTO and HTTP status 201 (CREATED)
     */
    @PostMapping(value = "/save")
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO) {
        try {
            CityEntity city = EntityConverter.convertToCityEntity(cityDTO);
            CityEntity createdCity = cityService.saveCity(city);
            return new ResponseEntity<>(EntityConverter.convertToCityDTO(createdCity), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves all cities.
     *
     * @return ResponseEntity with the list of city DTOs and HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {
        try {
            List<CityEntity> cities = cityService.findAllCities();
            List<CityDTO> cityDTOs = cities.stream()
                    .map(EntityConverter::convertToCityDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(cityDTOs, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a city by its ID.
     *
     * @param id the ID of the city
     * @return ResponseEntity with the found city's DTO or HTTP status 404 (NOT FOUND)
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) {
        try {
            Optional<CityEntity> city = cityService.findCityById(id);
            return city.map(c -> new ResponseEntity<>(EntityConverter.convertToCityDTO(c), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates a city by its ID.
     *
     * @param id      the ID of the city to update
     * @param cityDTO the updated city's DTO
     * @return ResponseEntity with the updated city's DTO or HTTP status 404 (NOT FOUND)
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<CityDTO> updateCityById(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
        try {
            CityEntity city = EntityConverter.convertToCityEntity(cityDTO);
            Optional<CityEntity> updatedCity = cityService.updateCityById(id, city);
            return updatedCity.map(c -> new ResponseEntity<>(EntityConverter.convertToCityDTO(c), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a city by its ID.
     *
     * @param id the ID of the city to delete
     * @return ResponseEntity with HTTP status 204 (NO CONTENT) if deletion was successful
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCityById(@PathVariable Long id) {
        try {
            cityService.deleteCityById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
