package com.codeintelligence.delivery.service;

import com.codeintelligence.delivery.model.city.CityEntity;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing cities
 */
public interface CityService {
    CityEntity saveCity(CityEntity city);

    List<CityEntity> findAllCities();

    Optional<CityEntity> findCityById(Long id);

    Optional<CityEntity> updateCityById(Long id, CityEntity city);

    void deleteCityById(Long id);
}
