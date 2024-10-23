package com.codeintelligence.delivery.serviceImpl;

import com.codeintelligence.delivery.model.deliveryaddress.DeliveryAddressEntity;
import com.codeintelligence.delivery.repository.DeliveryAddressRepository;
import com.codeintelligence.delivery.service.DeliveryAddressService;
import com.codeintelligence.delivery.utils.EntityValidator;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    private final DeliveryAddressRepository deliveryAddressRepository;

    /**
     * Constructor for DeliveryAddressServiceImpl.
     *
     * @param deliveryAddressRepository Repository used to perform CRUD operations on DeliveryAddress entities.
     */
    public DeliveryAddressServiceImpl(DeliveryAddressRepository deliveryAddressRepository) {
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    /**
     * Saves a delivery address entity to the database after validating its data.
     *
     * @param address the DeliveryAddressEntity object to be saved
     * @return the saved DeliveryAddressEntity object
     * @throws RuntimeException if there is an error while saving the address or if the address data is invalid
     */
    @Override
    public DeliveryAddressEntity saveDeliveryAddress(DeliveryAddressEntity address) {
        if (address == null || !EntityValidator.isValidDeliveryAddress(address)) {
            throw new RuntimeException("Invalid delivery address data. Make sure all required fields are filled.");
        }

        try {
            System.out.println("New Delivery Address created.");
            return deliveryAddressRepository.save(address);
        } catch (Exception e) {
            System.out.println("[saveDeliveryAddress] exception: " + e.getMessage());
            throw new RuntimeException("Error saving delivery address: " + e.getMessage());
        }
    }

    /**
     * Retrieves all delivery address entities from the database.
     *
     * @return a list of all DeliveryAddressEntity objects, or an empty list if no addresses are found
     * @throws RuntimeException if there is an error while retrieving the addresses
     */
    @Override
    public List<DeliveryAddressEntity> findAllDeliveryAddresses() {
        try {
            List<DeliveryAddressEntity> addresses = deliveryAddressRepository.findAll();
            if (addresses.isEmpty()) {
                System.out.println("No delivery addresses found.");
                return Collections.emptyList();
            }
            System.out.println("All delivery addresses have been found.");
            return addresses;
        } catch (Exception e) {
            System.out.println("[findAllDeliveryAddresses] exception: " + e.getMessage());
            throw new RuntimeException("Error retrieving delivery addresses: " + e.getMessage());
        }
    }

    /**
     * Retrieves a delivery address entity by its ID.
     *
     * @param id the ID of the address to retrieve
     * @return an Optional containing the found DeliveryAddressEntity, or empty if not found
     * @throws RuntimeException if there is an error while retrieving the address
     */
    @Override
    public Optional<DeliveryAddressEntity> findDeliveryAddressById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid delivery address ID.");
        }

        try {
            Optional<DeliveryAddressEntity> address = deliveryAddressRepository.findById(id);
            if (address.isEmpty()) {
                System.out.println("Delivery address with ID " + id + " not found.");
            }
            System.out.println("Delivery address with ID " + id + " has been found.");
            return address;
        } catch (Exception e) {
            System.out.println("[findDeliveryAddressById] exception: " + e.getMessage());
            throw new RuntimeException("Error retrieving delivery address by ID: " + e.getMessage());
        }
    }

    /**
     * Updates a delivery address entity by its ID.
     *
     * @param id      the ID of the address to update
     * @param address the delivery address entity with updated data
     * @return an Optional containing the updated DeliveryAddressEntity, or empty if not found
     * @throws RuntimeException if there is an error while updating the address
     */
    @Override
    public Optional<DeliveryAddressEntity> updateDeliveryAddressById(Long id, DeliveryAddressEntity address) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid delivery address ID.");
        }

        if (address == null || !EntityValidator.isValidDeliveryAddress(address)) {
            throw new RuntimeException("Invalid delivery address data. Make sure all required fields are filled.");
        }

        try {
            Optional<DeliveryAddressEntity> existingAddress = deliveryAddressRepository.findById(id);

            if (existingAddress.isPresent()) {
                DeliveryAddressEntity updatedAddress = existingAddress.get();
                // Update the fields of the address as necessary
                updatedAddress.setStreet(address.getStreet());
                updatedAddress.setPostalCode(address.getPostalCode());
                // Add more fields as necessary

                deliveryAddressRepository.save(updatedAddress);
                System.out.println("Delivery address with ID " + id + " has been updated.");
                return Optional.of(updatedAddress);
            } else {
                System.out.println("Delivery address with ID " + id + " not found.");
                return Optional.empty();
            }

        } catch (Exception e) {
            System.out.println("[updateDeliveryAddressById] exception: " + e.getMessage());
            throw new RuntimeException("Error updating delivery address by ID: " + e.getMessage());
        }
    }

    /**
     * Deletes a delivery address entity by its ID.
     *
     * @param id the ID of the address to delete
     * @throws RuntimeException if there is an error while deleting the address
     */
    @Override
    public void deleteDeliveryAddressById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid delivery address ID.");
        }

        try {
            Optional<DeliveryAddressEntity> addressOptional = deliveryAddressRepository.findById(id);

            if (addressOptional.isPresent()) {
                deliveryAddressRepository.delete(addressOptional.get());
                System.out.println("Delivery address with ID " + id + " has been deleted.");
            } else {
                System.out.println("Delivery address with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("[deleteDeliveryAddressById] exception: " + e.getMessage());
            throw new RuntimeException("Error deleting delivery address by ID: " + e.getMessage());
        }
    }
}
