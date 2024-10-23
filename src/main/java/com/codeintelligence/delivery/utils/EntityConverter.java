package com.codeintelligence.delivery.utils;

import com.codeintelligence.delivery.model.city.CityDTO;
import com.codeintelligence.delivery.model.city.CityEntity;
import com.codeintelligence.delivery.model.deliveryaddress.DeliveryAddressDTO;
import com.codeintelligence.delivery.model.deliveryaddress.DeliveryAddressEntity;
import com.codeintelligence.delivery.model.pack.PackDTO;
import com.codeintelligence.delivery.model.pack.PackEntity;
import com.codeintelligence.delivery.model.truck.TruckDTO;
import com.codeintelligence.delivery.model.truck.TruckEntity;
import com.codeintelligence.delivery.model.truckdriver.TruckDriverDTO;
import com.codeintelligence.delivery.model.truckdriver.TruckDriverEntity;
import com.codeintelligence.delivery.model.truckdrivertruck.TruckDriverTruckDTO;
import com.codeintelligence.delivery.model.truckdrivertruck.TruckDriverTruckEntity;

public class EntityConverter {

    /**
     * Converts a truck driver entity to its corresponding DTO.
     *
     * @param truckDriverEntity the truck driver entity
     * @return the truck driver DTO, or null if the entity is null
     */
    public static TruckDriverDTO convertToTruckDriverDTO(TruckDriverEntity truckDriverEntity) {
        if (truckDriverEntity == null) {
            return null;
        }

        TruckDriverDTO dto = new TruckDriverDTO();
        dto.setId(truckDriverEntity.getId());
        dto.setDni(truckDriverEntity.getDni());
        dto.setName(truckDriverEntity.getName());
        dto.setPhone(truckDriverEntity.getPhone());
        dto.setAddress(truckDriverEntity.getAddress());
        dto.setSalary(truckDriverEntity.getSalary());
        dto.setTruckDriverTrucks(truckDriverEntity.getTruckDriverTrucks());
        dto.setPackages(truckDriverEntity.getPackages());
        return dto;
    }

    /**
     * Converts a truck driver DTO to its corresponding entity.
     *
     * @param truckDriverDTO the truck driver DTO
     * @return the truck driver entity, or null if the DTO is null
     */
    public static TruckDriverEntity convertToTruckDriverEntity(TruckDriverDTO truckDriverDTO) {
        if (truckDriverDTO == null) {
            return null;
        }

        TruckDriverEntity entity = new TruckDriverEntity();
        entity.setId(truckDriverDTO.getId());
        entity.setDni(truckDriverDTO.getDni());
        entity.setName(truckDriverDTO.getName());
        entity.setPhone(truckDriverDTO.getPhone());
        entity.setAddress(truckDriverDTO.getAddress());
        entity.setSalary(truckDriverDTO.getSalary());
        entity.setTruckDriverTrucks(truckDriverDTO.getTruckDriverTrucks());
        entity.setPackages(truckDriverDTO.getPackages());
        return entity;
    }

    /**
     * Converts a truck entity to its corresponding DTO.
     *
     * @param truckEntity the truck entity
     * @return the truck DTO, or null if the entity is null
     */
    public static TruckDTO convertToTruckDTO(TruckEntity truckEntity) {
        if (truckEntity == null) {
            return null;
        }

        TruckDTO dto = new TruckDTO();
        dto.setId(truckEntity.getId());
        dto.setLicensePlate(truckEntity.getLicensePlate());
        dto.setModel(truckEntity.getModel());
        dto.setKilometers(truckEntity.getKilometers());
        return dto;
    }

    /**
     * Converts a truck DTO to its corresponding entity.
     *
     * @param truckDTO the truck DTO
     * @return the truck entity, or null if the DTO is null
     */
    public static TruckEntity convertToTruckEntity(TruckDTO truckDTO) {
        if (truckDTO == null) {
            return null;
        }

        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setId(truckDTO.getId());
        truckEntity.setLicensePlate(truckDTO.getLicensePlate());
        truckEntity.setModel(truckDTO.getModel());
        truckEntity.setKilometers(truckDTO.getKilometers());
        return truckEntity;
    }

    /**
     * Converts a truck-driver-truck entity to its corresponding DTO.
     *
     * @param truckDriverTruckEntity the truck-driver-truck entity
     * @return the truck-driver-truck DTO, or null if the entity is null
     */
    public static TruckDriverTruckDTO convertToTruckDriverTruckDTO(TruckDriverTruckEntity truckDriverTruckEntity) {
        if (truckDriverTruckEntity == null) {
            return null;
        }

        TruckDriverTruckDTO dto = new TruckDriverTruckDTO();
        dto.setId(truckDriverTruckEntity.getId());
        dto.setTruckDriver(convertToTruckDriverDTO(truckDriverTruckEntity.getTruckDriver()));
        dto.setTruck(convertToTruckDTO(truckDriverTruckEntity.getTruck()));
        dto.setDate(truckDriverTruckEntity.getDate());
        return dto;
    }

    /**
     * Converts a truck-driver-truck DTO to its corresponding entity.
     *
     * @param truckDriverTruckDTO the truck-driver-truck DTO
     * @return the truck-driver-truck entity, or null if the DTO is null
     */
    public static TruckDriverTruckEntity convertToTruckDriverTruckEntity(TruckDriverTruckDTO truckDriverTruckDTO) {
        if (truckDriverTruckDTO == null) {
            return null;
        }

        TruckDriverTruckEntity truckDriverTruckEntity = new TruckDriverTruckEntity();
        truckDriverTruckEntity.setId(truckDriverTruckDTO.getId());
        truckDriverTruckEntity.setTruckDriver(convertToTruckDriverEntity(truckDriverTruckDTO.getTruckDriver()));
        truckDriverTruckEntity.setTruck(convertToTruckEntity(truckDriverTruckDTO.getTruck()));
        truckDriverTruckEntity.setDate(truckDriverTruckDTO.getDate());
        return truckDriverTruckEntity;
    }

    /**
     * Converts a city entity to its corresponding DTO.
     *
     * @param cityEntity the city entity
     * @return the city DTO, or null if the entity is null
     */
    public static CityDTO convertToCityDTO(CityEntity cityEntity) {
        if (cityEntity == null) {
            return null;
        }

        CityDTO dto = new CityDTO();
        dto.setId(cityEntity.getId());
        dto.setName(cityEntity.getName());
        return dto;
    }

    /**
     * Converts a city DTO to its corresponding entity.
     *
     * @param cityDTO the city DTO
     * @return the city entity, or null if the DTO is null
     */
    public static CityEntity convertToCityEntity(CityDTO cityDTO) {
        if (cityDTO == null) {
            return null;
        }

        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(cityDTO.getId());
        cityEntity.setName(cityDTO.getName());
        return cityEntity;
    }

    /**
     * Converts a delivery address entity to its corresponding DTO.
     *
     * @param deliveryAddressEntity the delivery address entity
     * @return the delivery address DTO, or null if the entity is null
     */
    public static DeliveryAddressDTO convertToDeliveryAddressDTO(DeliveryAddressEntity deliveryAddressEntity) {
        if (deliveryAddressEntity == null) {
            return null;
        }

        DeliveryAddressDTO dto = new DeliveryAddressDTO();
        dto.setId(deliveryAddressEntity.getId());
        dto.setStreet(deliveryAddressEntity.getStreet());
        dto.setPostalCode(deliveryAddressEntity.getPostalCode());

        if (deliveryAddressEntity.getCity() != null) {
            CityDTO cityDTO = new CityDTO();
            cityDTO.setId(deliveryAddressEntity.getCity().getId());
            cityDTO.setName(deliveryAddressEntity.getCity().getName());
            dto.setCity(cityDTO);
        }
        return dto;
    }

    /**
     * Converts a delivery address DTO to its corresponding entity.
     *
     * @param deliveryAddressDTO the delivery address DTO
     * @return the delivery address entity, or null if the DTO is null
     */
    public static DeliveryAddressEntity convertToDeliveryAddressEntity(DeliveryAddressDTO deliveryAddressDTO) {
        if (deliveryAddressDTO == null) {
            return null;
        }

        DeliveryAddressEntity deliveryAddressEntity = new DeliveryAddressEntity();
        deliveryAddressEntity.setId(deliveryAddressDTO.getId());
        deliveryAddressEntity.setStreet(deliveryAddressDTO.getStreet());
        deliveryAddressEntity.setPostalCode(deliveryAddressDTO.getPostalCode());

        if (deliveryAddressDTO.getCity() != null) {
            CityEntity cityEntity = new CityEntity();
            cityEntity.setId(deliveryAddressDTO.getCity().getId());
            deliveryAddressEntity.setCity(cityEntity);
        }
        return deliveryAddressEntity;
    }

    /**
     * Converts a PackEntity to a PackDTO.
     *
     * @param packEntity the PackEntity to convert
     * @return the corresponding PackDTO, or null if the input is null
     */
    public static PackDTO convertToPackDTO(PackEntity packEntity) {
        if (packEntity == null) {
            return null;
        }

        PackDTO packDTO = new PackDTO();
        packDTO.setId(packEntity.getId());
        packDTO.setDescription(packEntity.getDescription());
        packDTO.setDestinationAddress(packEntity.getDestinationAddress());
        packDTO.setTruckDriver(convertToTruckDriverDTO(packEntity.getTruckDriver()));
        return packDTO;
    }

    /**
     * Converts a PackDTO to a PackEntity.
     *
     * @param packDTO the PackDTO to convert
     * @return the corresponding PackEntity, or null if the input is null
     */
    public static PackEntity convertToPackEntity(PackDTO packDTO) {
        if (packDTO == null) {
            return null;
        }

        PackEntity packEntity = new PackEntity();
        packEntity.setId(packDTO.getId());
        packEntity.setDescription(packDTO.getDescription());
        packEntity.setDestinationAddress(packDTO.getDestinationAddress());
        packEntity.setTruckDriver(convertToTruckDriverEntity(packDTO.getTruckDriver()));
        return packEntity;
    }
}
