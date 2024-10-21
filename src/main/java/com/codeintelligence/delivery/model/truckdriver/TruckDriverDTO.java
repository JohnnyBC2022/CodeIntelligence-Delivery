package com.codeintelligence.delivery.model.truckdriver;

/**
 * Data Transfer Object for a truck driver in the transportation company.
 */
public class TruckDriverDTO {

    private Long id;
    private String dni;
    private String name;
    private String phone;
    private String address;
    private Double salary;

    // Getters and setters

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

    // Constructors

    /**
     * Default constructor for TruckDriverDTO.
     * Initializes a new instance of TruckDriverDTO.
     */
    public TruckDriverDTO() {
    }

    /**
     * Constructor for TruckDriverDTO with parameters.
     *
     * @param id      the unique identifier of the truck driver.
     * @param dni     the DNI of the truck driver.
     * @param name    the name of the truck driver.
     * @param phone   the phone number of the truck driver.
     * @param address the address of the truck driver.
     * @param salary  the salary of the truck driver.
     */
    public TruckDriverDTO(Long id, String dni, String name, String phone, String address, Double salary) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.salary = salary;
    }
}

