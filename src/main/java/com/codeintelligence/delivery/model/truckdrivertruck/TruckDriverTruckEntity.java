package com.codeintelligence.delivery.model.truckdrivertruck;

import com.codeintelligence.delivery.model.truck.TruckEntity;
import com.codeintelligence.delivery.model.truckdriver.TruckDriverEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "truck_driver_truck", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_truck_driver", "date"})})
public class TruckDriverTruckEntity {

    /**
     * Entity representing the relationship between a truck driver and a truck,
     * including the date when the driver was assigned to drive a particular truck.
     * This entity serves as the intermediate table in the many-to-many relationship
     * between TruckDriverEntity and TruckEntity.
     *
     * Ensures that a truck driver can drive only one truck per day.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "truck_driver_truck_id", nullable = false)
    private Long id;

    /**
     * Many-to-one relationship with TruckDriverEntity.
     * This represents the truck driver who drove the truck.
     */
    @ManyToOne
    @JoinColumn(name = "id_truck_driver", nullable = false)
    private TruckDriverEntity truckDriver;

    /**
     * Many-to-one relationship with TruckEntity.
     * This represents the truck that was driven by the truck driver.
     */
    @ManyToOne
    @JoinColumn(name = "id_truck", nullable = false)
    private TruckEntity truck;

    /**
     * The date when the truck driver drove the truck.
     */
    @Column(name = "date", nullable = false)
    private LocalDate date;

    // Getters and Setters


    /**
     * Gets the unique identifier of the truck driver-truck relationship.
     *
     * @return the unique identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the truck driver-truck relationship.
     *
     * @param id the unique identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the truck driver associated with this relationship.
     *
     * @return the truck driver.
     */
    public TruckDriverEntity getTruckDriver() {
        return truckDriver;
    }

    /**
     * Sets the truck driver for this relationship.
     *
     * @param truckDriver the truck driver to set.
     */
    public void setTruckDriver(TruckDriverEntity truckDriver) {
        this.truckDriver = truckDriver;
    }

    /**
     * Gets the truck associated with this relationship.
     *
     * @return the truck.
     */
    public TruckEntity getTruck() {
        return truck;
    }

    /**
     * Sets the truck for this relationship.
     *
     * @param truck the truck to set.
     */
    public void setTruck(TruckEntity truck) {
        this.truck = truck;
    }

    /**
     * Gets the date when the truck driver drove the truck.
     *
     * @return the date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date when the truck driver drove the truck.
     *
     * @param date the date to set.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Constructors

    /**
     * Default constructor for the TruckDriverTruckEntity.
     */
    public TruckDriverTruckEntity() {
    }

    /**
     * Constructor for TruckDriverTruckEntity with parameters.
     * Initializes a new instance of TruckDriverTruckEntity with the provided attributes.
     *
     * @param id          the unique identifier of the truck driver-truck relationship.
     * @param truckDriver the truck driver associated with this relationship.
     * @param truck       the truck associated with this relationship.
     * @param date        the date when the truck driver drove the truck.
     */
    public TruckDriverTruckEntity(Long id, TruckDriverEntity truckDriver, TruckEntity truck, LocalDate date) {
        this.id = id;
        this.truckDriver = truckDriver;
        this.truck = truck;
        this.date = date;
    }
}
