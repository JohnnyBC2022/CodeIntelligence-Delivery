package com.codeintelligence.delivery.model.deliveryaddress;

import com.codeintelligence.delivery.model.city.CityEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

/**
 * Entity that represents a delivery address.
 * This class is mapped to the "delivery_address" table in the database.
 */
@Entity
@Table(name = "delivery_address")
public class DeliveryAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_delivery_address")
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @ManyToOne(targetEntity = CityEntity.class)
    @JsonIgnoreProperties({"deliveryAddresses"})
    private CityEntity city;


    // Getters and Setters

    /**
     * Gets the unique identifier of the delivery address.
     *
     * @return the id of the delivery address
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the delivery address.
     *
     * @param id the unique id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the street of the delivery address.
     *
     * @return the street of the delivery address
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street of the delivery address.
     *
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the postal code of the delivery address.
     *
     * @return the postal code of the delivery address
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the delivery address.
     *
     * @param postalCode the postal code to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the city associated with the delivery address.
     *
     * @return the city entity of the delivery address
     */
    public CityEntity getCity() {
        return city;
    }

    /**
     * Sets the city associated with the delivery address.
     *
     * @param city the city entity to set
     */
    public void setCity(CityEntity city) {
        this.city = city;
    }

    // Constructors

    /**
     * Default constructor for the DeliveryAddressEntity class.
     */
    public DeliveryAddressEntity() {
    }

    /**
     * Constructs a new DeliveryAddressEntity with the specified id, street, and postal code.
     *
     * @param id         the unique identifier of the delivery address
     * @param street     the street of the delivery address
     * @param postalCode the postal code of the delivery address
     * @param city       the city associated with the delivery address; must not be null
     */
    public DeliveryAddressEntity(Long id, String street, String postalCode, CityEntity city) {
        this.id = id;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }


}
