package com.codeintelligence.delivery.model.pack;

import com.codeintelligence.delivery.model.truckdriver.TruckDriverEntity;
import jakarta.persistence.*;

/**
 * Entity that represents a package.
 * This class is mapped to the "pack" table in the database.
 */
@Entity
@Table(name = "pack")
public class PackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pack")
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "destination_address", nullable = false)
    private String destinationAddress;

    @ManyToOne(targetEntity = TruckDriverEntity.class)
    @JoinColumn(name = "truck_driver_id")
    private TruckDriverEntity truckDriver;

    // Getters and Setters

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
    public TruckDriverEntity getTruckDriver() {
        return truckDriver;
    }

    /**
     * Sets the truck driver associated with the pack.
     *
     * @param truckDriver the truck driver entity to set
     */
    public void setTruckDriver(TruckDriverEntity truckDriver) {
        this.truckDriver = truckDriver;
    }

    // Constructors

    /**
     * Default constructor for the PackEntity class.
     */
    public PackEntity() {
    }

    /**
     * Constructs a new PackEntity with the specified description, destination address, and truck driver.
     *
     * @param description        the description of the pack
     * @param destinationAddress the destination address of the pack
     * @param truckDriver        the truck driver associated with the pack
     */
    public PackEntity(String description, String destinationAddress, TruckDriverEntity truckDriver) {
        this.description = description;
        this.destinationAddress = destinationAddress;
        this.truckDriver = truckDriver;
    }
}
