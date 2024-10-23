package com.codeintelligence.delivery.service;

import com.codeintelligence.delivery.model.pack.PackEntity;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing packages.
 */
public interface PackService {
    PackEntity savePack(PackEntity pack);

    List<PackEntity> findAllPacks();

    Optional<PackEntity> findPackById(Long id);

    Optional<PackEntity> updatePackById(Long id, PackEntity pack);

    void deletePackById(Long id);
}
