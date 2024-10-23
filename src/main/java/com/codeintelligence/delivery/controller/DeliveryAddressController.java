package com.codeintelligence.delivery.controller;

import com.codeintelligence.delivery.model.deliveryaddress.DeliveryAddressDTO;
import com.codeintelligence.delivery.model.deliveryaddress.DeliveryAddressEntity;
import com.codeintelligence.delivery.service.DeliveryAddressService;
import com.codeintelligence.delivery.utils.EntityConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller for managing delivery address operations.
 */
@RestController
@RequestMapping("/api/delivery-addresses")
public class DeliveryAddressController {

    private final DeliveryAddressService deliveryAddressService;

    public DeliveryAddressController(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    /**
     * Creates a new delivery address.
     *
     * @param deliveryAddressDTO the DTO of the delivery address to create
     * @return ResponseEntity with the created delivery address's DTO and HTTP status 201 (CREATED)
     */
    @PostMapping(value = "/save")
    public ResponseEntity<DeliveryAddressDTO> createDeliveryAddress(@RequestBody DeliveryAddressDTO deliveryAddressDTO) {
        try {
            DeliveryAddressEntity deliveryAddress = EntityConverter.convertToDeliveryAddressEntity(deliveryAddressDTO);
            DeliveryAddressEntity createdDeliveryAddress = deliveryAddressService.saveDeliveryAddress(deliveryAddress);
            return new ResponseEntity<>(EntityConverter.convertToDeliveryAddressDTO(createdDeliveryAddress), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves all delivery addresses.
     *
     * @return ResponseEntity with the list of delivery address DTOs and HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<DeliveryAddressDTO>> getAllDeliveryAddresses() {
        try {
            List<DeliveryAddressEntity> deliveryAddresses = deliveryAddressService.findAllDeliveryAddresses();
            List<DeliveryAddressDTO> deliveryAddressDTOs = deliveryAddresses.stream()
                    .map(EntityConverter::convertToDeliveryAddressDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(deliveryAddressDTOs, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a delivery address by its ID.
     *
     * @param id the ID of the delivery address
     * @return ResponseEntity with the found delivery address's DTO or HTTP status 404 (NOT FOUND)
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<DeliveryAddressDTO> getDeliveryAddressById(@PathVariable Long id) {
        try {
            Optional<DeliveryAddressEntity> deliveryAddress = deliveryAddressService.findDeliveryAddressById(id);
            return deliveryAddress.map(d -> new ResponseEntity<>(EntityConverter.convertToDeliveryAddressDTO(d), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates a delivery address by its ID.
     *
     * @param id                  the ID of the delivery address to update
     * @param deliveryAddressDTO  the updated delivery address's DTO
     * @return ResponseEntity with the updated delivery address's DTO or HTTP status 404 (NOT FOUND)
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<DeliveryAddressDTO> updateDeliveryAddressById(@PathVariable Long id, @RequestBody DeliveryAddressDTO deliveryAddressDTO) {
        try {
            DeliveryAddressEntity deliveryAddress = EntityConverter.convertToDeliveryAddressEntity(deliveryAddressDTO);
            Optional<DeliveryAddressEntity> updatedDeliveryAddress = deliveryAddressService.updateDeliveryAddressById(id, deliveryAddress);
            return updatedDeliveryAddress.map(d -> new ResponseEntity<>(EntityConverter.convertToDeliveryAddressDTO(d), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a delivery address by its ID.
     *
     * @param id the ID of the delivery address to delete
     * @return ResponseEntity with HTTP status 204 (NO CONTENT) or HTTP status 404 (NOT FOUND)
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDeliveryAddressById(@PathVariable Long id) {
        try {
            deliveryAddressService.deleteDeliveryAddressById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
