package com.codeintelligence.delivery.model.city;

import com.codeintelligence.delivery.model.deliveryaddress.DeliveryAddressDTO;
import com.codeintelligence.delivery.model.pack.PackDTO;

import java.util.List;

/**
 * Data Transfer Object (DTO) for the CityEntity.
 * This class is used to transfer city data between processes.
 */
public class CityDTO {

    private Long id;
    private String name;
    private List<DeliveryAddressDTO> deliveryAddresses;
    private List<PackDTO> packs;

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
    public List<DeliveryAddressDTO> getDeliveryAddresses() {
        return deliveryAddresses;
    }

    /**
     * Sets the list of delivery addresses associated with the city.
     *
     * @param deliveryAddresses the list of delivery addresses to set
     */
    public void setDeliveryAddresses(List<DeliveryAddressDTO> deliveryAddresses) {
        this.deliveryAddresses = deliveryAddresses;
    }


    /**
     * Gets the list of packs associated with the city.
     *
     * @return the list of packs associated with the city
     */
    public List<PackDTO> getPacks() {
        return packs;
    }

    /**
     * Sets the list of packs associated with the city.
     *
     * @param packs the list of packs to set
     */
    public void setPacks(List<PackDTO> packs) {
        this.packs = packs;
    }

    // Constructors

    /**
     * Default constructor for the CityDTO class.
     */
    public CityDTO() {
    }

    /**
     * Constructs a new CityDTO with the specified id, name, delivery addresses, and packs.
     *
     * @param id                 the unique identifier of the city
     * @param name               the name of the city
     * @param deliveryAddresses  the list of delivery addresses associated with the city
     * @param packs              the list of packs associated with the city
     */
    public CityDTO(Long id, String name, List<DeliveryAddressDTO> deliveryAddresses, List<PackDTO> packs) {
        this.id = id;
        this.name = name;
        this.deliveryAddresses = deliveryAddresses;
        this.packs = packs;
    }
}
