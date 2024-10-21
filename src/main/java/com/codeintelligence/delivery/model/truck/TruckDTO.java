package com.codeintelligence.delivery.model.truck;

/**
 * Data Transfer Object (DTO) for Truck entity.
 * This is used to transfer data between different layers of the application.
 */
public class TruckDTO {

    private Long id;
    private String licensePlate;
    private String model;
    private Double kilometers;

    // Getters y Setters

    /**
     * Gets the unique identifier of the truck.
     *
     * @return the unique identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the truck.
     *
     * @param id the unique identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the license plate of the truck.
     *
     * @return the license plate.
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Sets the license plate of the truck.
     *
     * @param licensePlate the license plate to set.
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * Gets the model of the truck.
     *
     * @return the model of the truck.
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the truck.
     *
     * @param model the model to set.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the kilometers the truck has traveled.
     *
     * @return the kilometers.
     */
    public Double getKilometers() {
        return kilometers;
    }

    /**
     * Sets the kilometers the truck has traveled.
     *
     * @param kilometers the kilometers to set.
     */
    public void setKilometers(Double kilometers) {
        this.kilometers = kilometers;
    }

    // Constructores

    /**
     * Default constructor for truck DTO.
     */
    public TruckDTO() {
    }

    /**
     * Constructor for truck DTO with parameters.
     *
     * @param id           the unique identifier of the truck.
     * @param licensePlate the license plate of the truck.
     * @param model        the model of the truck.
     * @param kilometers   the kilometers traveled by the truck.
     */
    public TruckDTO(Long id, String licensePlate, String model, Double kilometers) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.model = model;
        this.kilometers = kilometers;
    }
}
