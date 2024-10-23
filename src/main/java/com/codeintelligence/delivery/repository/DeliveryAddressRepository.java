package com.codeintelligence.delivery.repository;

import com.codeintelligence.delivery.model.deliveryaddress.DeliveryAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the delivery address entity.
 * Provides methods to perform CRUD operations on trucks.
 */
@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddressEntity, Long> {
}
