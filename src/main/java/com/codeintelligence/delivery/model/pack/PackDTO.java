package com.codeintelligence.delivery.model.pack;

import com.codeintelligence.delivery.model.truckdriver.TruckDriverDTO;


/**
 * Data Transfer Object (DTO) that represents a pack.
 * This class is used to transfer data related to the PackEntity.
 */
public class PackDTO {

    private Long id;
    private String description;
    private String destinationAddress;
    private TruckDriverDTO truckDriver;

    // Getters y Setters

    /**
     * Gets the unique identifier of the pack.
     *
     * @return the id of the pack
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the pack.
     *
     * @param id the unique id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the description of the pack.
     *
     * @return the description of the pack
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the pack.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the destination address of the pack.
     *
     * @return the destination address of the pack
     */
    public String getDestinationAddress() {
        return destinationAddress;
    }

    /**
     * Sets the destination address of the pack.
     *
     * @param destinationAddress the destination address to set
     */
    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    /**
     * Gets the truck driver associated with the pack.
     *
     * @return the truck driver entity
     */
    public TruckDriverDTO getTruckDriver() {
        return truckDriver;
    }

    /**
     * Sets the truck driver associated with the pack.
     *
     * @param truckDriver the truck driver entity to set
     */
    public void setTruckDriver(TruckDriverDTO truckDriver) {
        this.truckDriver = truckDriver;
    }

    // Constructors

    /**
     * Default constructor for the PackDTO class.
     */
    public PackDTO() {
    }

    /**
     * Constructs a new PackDTO with the specified id, description, destination address, and truck driver.
     *
     * @param id                 the unique identifier of the pack
     * @param description        the description of the pack
     * @param destinationAddress the destination address of the pack
     * @param truckDriver        the truck driver associated with the pack
     */
    public PackDTO(Long id, String description, String destinationAddress, TruckDriverDTO truckDriver) {
        this.id = id;
        this.description = description;
        this.destinationAddress = destinationAddress;
        this.truckDriver = truckDriver;
    }


}
