package com.codeintelligence.delivery.model.truckdriver;

import com.codeintelligence.delivery.model.pack.PackEntity;
import com.codeintelligence.delivery.model.truckdrivertruck.TruckDriverTruckEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

/**
 * Entity that represents a truck driver in the transportation company.
 */
@Entity
@Table(name = "truck_driver")
public class TruckDriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_truck_driver")
    private Long id;

    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "salary", nullable = false)
    private Double salary;

    /**
     * Many-to-one relationship with TruckEntity.
     *
     *  This annotation indicates that multiple instances of the current entity
     *  (TruckDriverTruck) can be associated with a single instance of TruckEntity.
     */
    @OneToMany(targetEntity = TruckDriverTruckEntity.class, fetch = FetchType.LAZY, mappedBy = "truckDriver")
    @JsonManagedReference("truck_driver - trucks")
    private List<TruckDriverTruckEntity> truckDriverTrucks;

    @OneToMany(targetEntity = PackEntity.class, fetch = FetchType.LAZY, mappedBy = "truckDriver")
    @JsonManagedReference("truck_driver - packs")
    private List<PackEntity> packages;

    // Getters y Setters

    /**
     * Gets the unique identifier of the truck driver.
     *
     * @return the unique identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the truck driver.
     *
     * @param id the unique identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the DNI of the truck driver.
     *
     * @return the DNI.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Sets the DNI of the truck driver.
     *
     * @param dni the DNI to set.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Gets the name of the truck driver.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the truck driver.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the phone number of the truck driver.
     *
     * @return the phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the truck driver.
     *
     * @param phone the phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the address of the truck driver.
     *
     * @return the address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the truck driver.
     *
     * @param address the address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the salary of the truck driver.
     *
     * @return the salary.
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * Sets the salary of the truck driver.
     *
     * @param salary the salary to set.
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    /**
     * Gets the list of trucks assigned to the truck driver.
     *
     * @return the list of trucks.
     */
    public List<TruckDriverTruckEntity> getTruckDriverTrucks() {
        return truckDriverTrucks;
    }

    /**
     * Sets the list of trucks assigned to the truck driver.
     *
     * @param truckDriverTrucks the list of trucks to set.
     */
    public void setTruckDriverTrucks(List<TruckDriverTruckEntity> truckDriverTrucks) {
        this.truckDriverTrucks = truckDriverTrucks;
    }

    /**
     * Gets the list of packages assigned to the truck driver.
     *
     * @return the list of packages.
     */
    public List<PackEntity> getPackages() {
        return packages;
    }

    /**
     * Sets the list of packages assigned to the truck driver.
     *
     * @param packages the list of packages to set.
     */
    public void setPackages(List<PackEntity> packages) {
        this.packages = packages;
    }

    // Constructors


    /**
     * Default constructor for truck driver
     * Initializes a new instance of TruckDriver
     */
    public TruckDriverEntity() {
    }

    /**
     * Constructor for truck driver with parameters.
     * Initializes a new instance of TruckDriver with the provided attributes and the assigned trucks.
     *
     * @param id                  the unique identifier of the truck driver.
     * @param dni                 the DNI of the truck driver.
     * @param name                the name of the truck driver.
     * @param phone               the phone number of the truck driver.
     * @param address             the address of the truck driver.
     * @param salary              the salary of the truck driver.
     * @param truckDriverTrucks   the list of trucks assigned to the truck driver.
     * @param packages            the list of packages assigned to the truck driver.
     */
    public TruckDriverEntity(Long id, String dni, String name, String phone, String address, Double salary,
                             List<TruckDriverTruckEntity> truckDriverTrucks, List<PackEntity> packages) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.salary = salary;
        this.truckDriverTrucks = truckDriverTrucks;
        this.packages = packages;
    }
}
