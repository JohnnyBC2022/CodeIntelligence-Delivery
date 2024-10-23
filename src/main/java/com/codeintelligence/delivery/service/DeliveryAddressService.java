package com.codeintelligence.delivery.service;

import com.codeintelligence.delivery.model.deliveryaddress.DeliveryAddressEntity;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing addresses
 */
public interface DeliveryAddressService {
    DeliveryAddressEntity saveDeliveryAddress(DeliveryAddressEntity address);

        List<DeliveryAddressEntity> findAllDeliveryAddresses();

        Optional<DeliveryAddressEntity> findDeliveryAddressById(Long id);

        Optional<DeliveryAddressEntity> updateDeliveryAddressById(Long id, DeliveryAddressEntity address);

        void deleteDeliveryAddressById(Long id);
}
