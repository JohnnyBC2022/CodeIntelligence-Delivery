package com.codeintelligence.delivery.model.city;

import jakarta.persistence.*;

/**
 * Entity that represents a city.
 * This class is mapped to the "city" table in the database.
 */
@Entity
@Table(name = "city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city")
    private Long id;

    @Column(name = "city", nullable = false)
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
     * Default constructor for the CityEntity class.
     */
    public CityEntity() {
    }

    /**
     * Constructs a new CityEntity with the specified id and name.
     *
     * @param id   the unique identifier of the city
     * @param name the name of the city
     */
    public CityEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
