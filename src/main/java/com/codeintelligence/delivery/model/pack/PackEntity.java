package com.codeintelligence.delivery.model.pack;

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

    // Constructors

    /**
     * Default constructor for the PackEntity class.
     */
    public PackEntity() {
    }

    /**
     * Constructs a new PackEntity with the specified description and destination address.
     *
     * @param description         the description of the pack
     * @param destinationAddress  the destination address of the pack
     */
    public PackEntity(String description, String destinationAddress) {
        this.description = description;
        this.destinationAddress = destinationAddress;
    }
}
