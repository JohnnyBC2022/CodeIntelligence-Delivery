package com.codeintelligence.delivery.utils;

import com.codeintelligence.delivery.model.city.CityEntity;
import com.codeintelligence.delivery.model.deliveryaddress.DeliveryAddressEntity;
import com.codeintelligence.delivery.model.truck.TruckEntity;
import com.codeintelligence.delivery.model.truckdriver.TruckDriverEntity;
import com.codeintelligence.delivery.model.truckdrivertruck.TruckDriverTruckEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

/**
 * Utility class for validating entities.
 */
public class EntityValidator {
    /**
     * Validates the TruckDriverEntity.
     *
     * @param truckDriver The TruckDriverEntity to validate, must not be null.
     * @return true if valid; false otherwise.
     */
    public static boolean isValidTruckDriver(@NonNull TruckDriverEntity truckDriver) {
        return StringUtils.hasText(truckDriver.getDni()) &&
                StringUtils.hasText(truckDriver.getName()) &&
                StringUtils.hasText(truckDriver.getPhone()) &&
                StringUtils.hasText(truckDriver.getAddress()) &&
                truckDriver.getSalary() != null &&
                truckDriver.getSalary() > 0;
    }

    /**
     * Validates the truck data.
     *
     * @param truck the TruckEntity object to validate, must not be null.
     * @return true if valid, false otherwise
     */
    public static boolean isValidTruck(@NonNull TruckEntity truck) {
        return StringUtils.hasText(truck.getLicensePlate()) &&
                StringUtils.hasText(truck.getModel()) &&
                truck.getKilometers() != null;
    }

    /**
     * Validates the TruckDriverTruckEntity.
     *
     * @param truckDriverTruck The TruckDriverTruckEntity to validate, must not be null.
     * @return true if valid; false otherwise.
     */
    public static boolean isValidTruckDriverTruck(@NonNull TruckDriverTruckEntity truckDriverTruck) {
        return truckDriverTruck.getTruck() != null &&
                truckDriverTruck.getTruckDriver() != null &&
                truckDriverTruck.getDate() != null;
    }

    /**
     * Validates the CityEntity.
     *
     * @param city The CityEntity to validate.
     * @return true if valid; false otherwise.
     */
    public static boolean isValidCity(@NonNull CityEntity city) {
        return StringUtils.hasText(city.getName());
    }

    /**
     * Validates the DeliveryAddressEntity.
     *
     * @param deliveryAddress The DeliveryAddressEntity to validate, must not be null.
     * @return true if valid; false otherwise.
     */
    public static boolean isValidDeliveryAddress(@NonNull DeliveryAddressEntity deliveryAddress) {
        return StringUtils.hasText(deliveryAddress.getStreet()) &&
                StringUtils.hasText(deliveryAddress.getPostalCode());
    }
}
