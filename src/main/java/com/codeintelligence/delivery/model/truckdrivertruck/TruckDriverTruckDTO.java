package com.codeintelligence.delivery.model.truckdrivertruck;

import com.codeintelligence.delivery.model.truck.TruckDTO;
import com.codeintelligence.delivery.model.truckdriver.TruckDriverDTO;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for the association between Truck and TruckDriver entities.
 * This is used to transfer data between different layers of the application.
 */
public class TruckDriverTruckDTO {
    private Long id;
    private TruckDTO truck;
    private TruckDriverDTO truckDriver;
    private LocalDate date;

    // Getters and setters


    /**
     * Gets the unique identifier of the TruckDriverTruck association.
     *
     * @return the unique identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the TruckDriverTruck association.
     *
     * @param id the unique identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the TruckDTO associated with the TruckDriverTruck association.
     *
     * @return the TruckDTO.
     */
    public TruckDTO getTruck() {
        return truck;
    }

    /**
     * Sets the TruckDTO associated with the TruckDriverTruck association.
     *
     * @param truck the TruckDTO to set.
     */
    public void setTruck(TruckDTO truck) {
        this.truck = truck;
    }

    /**
     * Gets the TruckDriverDTO associated with the TruckDriverTruck association.
     *
     * @return the TruckDriverDTO.
     */
    public TruckDriverDTO getTruckDriver() {
        return truckDriver;
    }

    /**
     * Sets the TruckDriverDTO associated with the TruckDriverTruck association.
     *
     * @param truckDriver the TruckDriverDTO to set.
     */
    public void setTruckDriver(TruckDriverDTO truckDriver) {
        this.truckDriver = truckDriver;
    }

    /**
     * Gets the assignment date of the truck to the driver.
     *
     * @return the assignment date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the assignment date of the truck to the driver.
     *
     * @param date the assignment date to set.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Constructors
    /**
     * Default constructor for TruckDriverTruckDTO.
     */
    public TruckDriverTruckDTO() {
    }

    /**
     * Constructor for TruckDriverTruckDTO with parameters.
     *
     * @param id             the unique identifier of the TruckDriverTruck association.
     * @param truck          the TruckDTO associated with the truck driver.
     * @param truckDriver    the TruckDriverDTO associated with the truck.
     * @param date  the date when the truck was assigned to the driver.
     */
    public TruckDriverTruckDTO(Long id, TruckDTO truck, TruckDriverDTO truckDriver, LocalDate date) {
        this.id = id;
        this.truck = truck;
        this.truckDriver = truckDriver;
        this.date = date;
    }
}
