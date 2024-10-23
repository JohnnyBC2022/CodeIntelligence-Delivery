package com.codeintelligence.delivery.serviceImpl;

import com.codeintelligence.delivery.model.city.CityEntity;
import com.codeintelligence.delivery.repository.CityRepository;
import com.codeintelligence.delivery.service.CityService;
import com.codeintelligence.delivery.utils.EntityValidator;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    /**
     * Constructor for CityServiceImpl.
     *
     * @param cityRepository Repository used to perform CRUD operations on City entities.
     */
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * Saves a city entity to the database after validating its data.
     *
     * @param city the CityEntity object to be saved
     * @return the saved CityEntity object
     * @throws RuntimeException if there is an error while saving the city or if the city data is invalid
     */
    @Override
    public CityEntity saveCity(CityEntity city) {
        if (city == null || !EntityValidator.isValidCity(city)) {
            throw new RuntimeException("Invalid city data. Make sure all required fields are filled.");
        }

        try {
            System.out.println("New City created.");
            return cityRepository.save(city);
        } catch (Exception e) {
            System.out.println("[saveCity] exception: " + e.getMessage());
            throw new RuntimeException("Error saving city: " + e.getMessage());
        }
    }

    /**
     * Retrieves all city entities from the database.
     *
     * @return a list of all CityEntity objects, or an empty list if no cities are found
     * @throws RuntimeException if there is an error while retrieving the cities
     */
    @Override
    public List<CityEntity> findAllCities() {
        try {
            List<CityEntity> cities = cityRepository.findAll();
            if (cities.isEmpty()) {
                System.out.println("No cities found.");
                return Collections.emptyList();
            }
            System.out.println("All cities has been found.");
            return cities;
        } catch (Exception e) {
            System.out.println("[findAllCities] exception: " + e.getMessage());
            throw new RuntimeException("Error retrieving cities: " + e.getMessage());
        }
    }

    /**
     * Retrieves a city entity by its ID.
     *
     * @param id the ID of the city to retrieve
     * @return an Optional containing the found CityEntity, or empty if not found
     * @throws RuntimeException if there is an error while retrieving the city
     */
    @Override
    public Optional<CityEntity> findCityById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid city ID.");
        }

        try {
            Optional<CityEntity> city = cityRepository.findById(id);
            if (city.isEmpty()) {
                System.out.println("City with ID " + id + " not found.");
            }
            System.out.println("City with ID " + id + " has been found.");
            return city;
        } catch (Exception e) {
            System.out.println("[findCityById] exception: " + e.getMessage());
            throw new RuntimeException("Error retrieving city by ID: " + e.getMessage());
        }
    }

    /**
     * Updates a city entity by its ID.
     *
     * @param id   the ID of the city to update
     * @param city the city entity with updated data
     * @return an Optional containing the updated CityEntity, or empty if not found
     */
    @Override
    public Optional<CityEntity> updateCityById(Long id, CityEntity city) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid city ID.");
        }

        if (city == null || !EntityValidator.isValidCity(city)) {
            throw new RuntimeException("Invalid city data. Make sure all required fields are filled.");
        }

        try {
            Optional<CityEntity> existingCity = cityRepository.findById(id);

            if (existingCity.isPresent()) {
                CityEntity updatedCity = existingCity.get();
                updatedCity.setName(city.getName());

                cityRepository.save(updatedCity);
                System.out.println("City with ID " + id + " has been updated.");
                return Optional.of(updatedCity);
            } else {
                System.out.println("City with ID " + id + " not found.");
                return Optional.empty();
            }

        } catch (Exception e) {
            System.out.println("[updateCityById] exception: " + e.getMessage());
            throw new RuntimeException("Error updating city by ID: " + e.getMessage());
        }
    }

    /**
     * Deletes a city entity by its ID.
     *
     * @param id the ID of the city to delete
     */
    @Override
    public void deleteCityById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid city ID.");
        }

        try {
            Optional<CityEntity> cityOptional = cityRepository.findById(id);

            if (cityOptional.isPresent()) {
                cityRepository.delete(cityOptional.get());
                System.out.println("City with ID " + id + " has been deleted.");
            } else {
                System.out.println("City with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("[deleteCityById] exception: " + e.getMessage());
            throw new RuntimeException("Error deleting city by ID: " + e.getMessage());
        }
    }
}
