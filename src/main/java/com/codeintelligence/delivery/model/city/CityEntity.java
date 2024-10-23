package com.codeintelligence.delivery.model.city;

import com.codeintelligence.delivery.model.deliveryaddress.DeliveryAddressEntity;
import jakarta.persistence.*;

import java.util.List;

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

    @OneToMany(targetEntity = DeliveryAddressEntity.class, fetch = FetchType.LAZY, mappedBy = "city")
    private List<DeliveryAddressEntity> deliveryAddresses;

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

    /**
     * Gets the list of delivery addresses associated with the city.
     *
     * @return the list of delivery addresses
     */
    public List<DeliveryAddressEntity> getDeliveryAddresses() {
        return deliveryAddresses;
    }

    /**
     * Sets the list of delivery addresses associated with the city.
     *
     * @param deliveryAddresses the list of delivery addresses to set
     */
    public void setDeliveryAddresses(List<DeliveryAddressEntity> deliveryAddresses) {
        this.deliveryAddresses = deliveryAddresses;
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
     * @param deliveryAddresses the list of delivery addresses associated with the city; can be null if not initialized
     */
    public CityEntity(Long id, String name, List<DeliveryAddressEntity> deliveryAddresses) {
        this.id = id;
        this.name = name;
        this.deliveryAddresses = deliveryAddresses;
    }
}
