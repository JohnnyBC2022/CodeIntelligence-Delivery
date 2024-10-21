package com.codeintelligence.delivery.utilities;

import org.springframework.stereotype.Component;

import com.codeintelligence.delivery.model.truck.TruckDTO;
import com.codeintelligence.delivery.model.truck.TruckEntity;
import com.codeintelligence.delivery.model.truckdriver.TruckDriverDTO;
import com.codeintelligence.delivery.model.truckdriver.TruckDriverEntity;

@Component
public class Mapper {
	
    // Conversion methods between entities

    /**
     * Converts a truck driver entity to its corresponding DTO.
     *
     * @param truckDriver the truck driver entity
     * @return the truck driver DTO
     */
    public TruckDriverDTO convertToDTO(TruckDriverEntity truckDriver) {
        return new TruckDriverDTO(truckDriver.getId(),
						        truckDriver.getDni(),
						        truckDriver.getName(),
						        truckDriver.getPhone(),
						        truckDriver.getAddress(),
						        truckDriver.getSalary());
    
        
        /*
        TruckDriverDTO dto = new TruckDriverDTO();
        dto.setId(truckDriver.getId());
        dto.setDni(truckDriver.getDni());
        dto.setName(truckDriver.getName());
        dto.setPhone(truckDriver.getPhone());
        dto.setAddress(truckDriver.getAddress());
        dto.setSalary(truckDriver.getSalary());
        return dto;
         */
    }

    /**
     * Converts a truck driver DTO to its corresponding entity.
     *
     * @param dto the truck driver DTO
     * @return the truck driver entity
     */
    public TruckDriverEntity convertToEntity(TruckDriverDTO dto) {
        return new TruckDriverEntity(dto.getId(),
							        dto.getDni(),
							        dto.getName(),
							        dto.getPhone(),
							        dto.getAddress(),
							        dto.getSalary());

    	/*
        TruckDriverEntity truckDriver = new TruckDriverEntity();
        truckDriver.setId(dto.getId());
        truckDriver.setDni(dto.getDni());
        truckDriver.setName(dto.getName());
        truckDriver.setPhone(dto.getPhone());
        truckDriver.setAddress(dto.getAddress());
        truckDriver.setSalary(dto.getSalary());
        return truckDriver;
        */
    }
    // Conversion methods between entities

    /**
     * Converts a truck entity to its corresponding DTO.
     *
     * @param truck the truck entity
     * @return the truck DTO
     */
    public TruckDTO convertToDTO(TruckEntity truck) {
    	
        return new TruckDTO(truck.getId(),
					        truck.getLicensePlate(),
					        truck.getModel(),
					        truck.getKilometers());

    	/*
        TruckDTO dto = new TruckDTO();
        dto.setId(truck.getId());
        dto.setLicensePlate(truck.getLicensePlate());
        dto.setModel(truck.getModel());
        dto.setKilometers(truck.getKilometers());
        return dto;
        */
    }

    /**
     * Converts a truck DTO to its corresponding entity.
     *
     * @param dto the truck DTO
     * @return the truck entity
     */
    public TruckEntity convertToEntity(TruckDTO dto) {
    	
        return new TruckEntity(dto.getId(),
						        dto.getLicensePlate(),
						        dto.getModel(),
						        dto.getKilometers());

    	
    	/*
        TruckEntity truck = new TruckEntity();
        truck.setId(dto.getId());
        truck.setLicensePlate(dto.getLicensePlate());
        truck.setModel(dto.getModel());
        truck.setKilometers(dto.getKilometers());
        return truck;
        */
    }

}
