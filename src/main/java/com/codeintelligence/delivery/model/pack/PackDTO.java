package com.codeintelligence.delivery.model.pack;

/**
 * Data Transfer Object (DTO) that represents a pack.
 * This class is used to transfer data related to the PackEntity.
 */
public class PackDTO {

    private Long id;
    private String description;
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
     * Default constructor for the PackDTO class.
     */
    public PackDTO() {
    }

    /**
     * Constructs a new PackDTO with the specified id, description, and destination address.
     *
     * @param id                 the unique identifier of the pack
     * @param description        the description of the pack
     * @param destinationAddress the destination address of the pack
     */
    public PackDTO(Long id, String description, String destinationAddress) {
        this.id = id;
        this.description = description;
        this.destinationAddress = destinationAddress;
    }

    /**
     * Constructs a new PackDTO without id, only with description and destination address.
     *
     * @param description        the description of the pack
     * @param destinationAddress the destination address of the pack
     */
    public PackDTO(String description, String destinationAddress) {
        this.description = description;
        this.destinationAddress = destinationAddress;
    }
}

