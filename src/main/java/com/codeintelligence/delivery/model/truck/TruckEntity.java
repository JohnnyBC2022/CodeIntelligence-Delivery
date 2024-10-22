package com.codeintelligence.delivery.model.truck;

import com.codeintelligence.delivery.model.truckdriver.TruckDriverEntity;
import jakarta.persistence.*;

/**
 * Entity that represents a truck in the transportation company.
 */
@Entity
@Table(name = "truck")
public class TruckEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_truck")
    private Long id;

    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "kilometers", nullable = false)
    private Double kilometers;

    /**
     * Many-to-one relationship with TruckDriverEntity
     *
     * This annotation indicates that multiple instances of the current entity
     *  * (TruckDriverTruck) can be associated with a single instance of TruckDriverEntity.
     */
    @ManyToOne
    @JoinColumn(name = "id_truck_driver", nullable = false)
    private TruckDriverEntity truckDriver;

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
     * @return the model.
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

    /**
     * Gets the truck driver assigned to the truck.
     *
     * @return the truck driver.
     */
    public TruckDriverEntity getTruckDriver() {
        return truckDriver;
    }

    /**
     * Sets the truck driver assigned to the truck.
     *
     * @param truckDriver the truck driver to set.
     */
    public void setTruckDriver(TruckDriverEntity truckDriver) {
        this.truckDriver = truckDriver;
    }

    // Constructores

    /**
     * Default constructor for truck.
     */
    public TruckEntity() {
    }

    /**
     * Constructor for truck with parameters.
     * Initializes a new instance of Truck with the provided attributes and the assigned truck driver.
     *
     * @param id           the unique identifier of the truck.
     * @param licensePlate the license plate of the truck.
     * @param model        the model of the truck.
     * @param kilometers   the kilometers traveled by the truck.
     * @param truckDriver  the truck driver assigned to the truck.
     */
    public TruckEntity(Long id, String licensePlate, String model, Double kilometers, TruckDriverEntity truckDriver) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.model = model;
        this.kilometers = kilometers;
        this.truckDriver = truckDriver;
    }
}
