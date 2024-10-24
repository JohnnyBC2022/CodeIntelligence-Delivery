package com.codeintelligence.delivery.model.deliveryaddress;

import com.codeintelligence.delivery.model.city.CityDTO;

/**
 * Data Transfer Object for delivery address.
 * This class is used to transfer delivery address data between layers.
 */
public class DeliveryAddressDTO {

    private Long id;
    private String street;
    private String postalCode;
    private CityDTO city;


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
    public CityDTO getCity() {
        return city;
    }

    /**
     * Sets the city associated with the delivery address.
     *
     * @param city the city entity to set
     */
    public void setCity(CityDTO city) {
        this.city = city;
    }

    // Constructors

    /**
     * Default constructor for the DeliveryAddressDTO class.
     */
    public DeliveryAddressDTO() {
    }

    /**
     * Constructs a new DeliveryAddressDTO with the specified id, street, and postal code.
     *
     * @param id         the unique identifier of the delivery address
     * @param street     the street of the delivery address
     * @param postalCode the postal code of the delivery address
     * @param city       the city associated with the delivery address; must not be null
     */
    public DeliveryAddressDTO(Long id, String street, String postalCode, CityDTO city) {
        this.id = id;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }
}
