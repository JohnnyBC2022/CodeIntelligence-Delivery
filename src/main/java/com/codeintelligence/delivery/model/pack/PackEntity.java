package com.codeintelligence.delivery.model.pack;

import com.codeintelligence.delivery.model.city.CityEntity;
import com.codeintelligence.delivery.model.truckdriver.TruckDriverEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference("truck_driver - packs")
    private TruckDriverEntity truckDriver;

    @ManyToOne(targetEntity = CityEntity.class)
    @JsonBackReference("city - packs")
    private CityEntity city;

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

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    // Constructors

    /**
     * Default constructor for the PackEntity class.
     */
    public PackEntity() {
    }

    public PackEntity(Long id, String description, String destinationAddress, TruckDriverEntity truckDriver, CityEntity city) {
        this.id = id;
        this.description = description;
        this.destinationAddress = destinationAddress;
        this.truckDriver = truckDriver;
        this.city = city;
    }
}
