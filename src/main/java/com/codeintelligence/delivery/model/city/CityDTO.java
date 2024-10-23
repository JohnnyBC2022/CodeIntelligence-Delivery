package com.codeintelligence.delivery.model.city;

/**
 * Data Transfer Object (DTO) for the CityEntity.
 * This class is used to transfer city data between processes.
 */
public class CityDTO {

    private Long id;
    private String name;

    // Getters and Setters

    /**
     * Gets the unique identifier of the city.
     *
     * @return the id of the city
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the city.
     *
     * @param id the unique id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the city.
     *
     * @return the name of the city
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the city.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    // Constructors

    /**
     * Default constructor for the CityDTO class.
     */
    public CityDTO() {
    }

    /**
     * Constructs a new CityDTO with the specified id and name.
     *
     * @param id   the unique identifier of the city
     * @param name the name of the city
     */
    public CityDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
