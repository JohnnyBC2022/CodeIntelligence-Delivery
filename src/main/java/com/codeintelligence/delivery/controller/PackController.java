package com.codeintelligence.delivery.controller;

import com.codeintelligence.delivery.model.pack.PackDTO;
import com.codeintelligence.delivery.model.pack.PackEntity;
import com.codeintelligence.delivery.service.PackService;
import com.codeintelligence.delivery.utils.EntityConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller for managing pack operations.
 */
@RestController
@RequestMapping("/api/packs")
public class PackController {

    private final PackService packService;

    public PackController(PackService packService) {
        this.packService = packService;
    }

    /**
     * Creates a new pack.
     *
     * @param packDTO the DTO of the pack to create
     * @return ResponseEntity with the created pack's DTO and HTTP status 201 (CREATED)
     */
    @PostMapping(value = "/save")
    public ResponseEntity<PackDTO> createPack(@RequestBody PackDTO packDTO) {
        try {
            PackEntity pack = EntityConverter.convertToPackEntity(packDTO);
            PackEntity createdPack = packService.savePack(pack);
            return new ResponseEntity<>(EntityConverter.convertToPackDTO(createdPack), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves all packs.
     *
     * @return ResponseEntity with the list of pack DTOs and HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<PackDTO>> getAllPacks() {
        try {
            List<PackEntity> packs = packService.findAllPacks();
            List<PackDTO> packDTOs = packs.stream()
                    .map(EntityConverter::convertToPackDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(packDTOs, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a pack by its ID.
     *
     * @param id the ID of the pack
     * @return ResponseEntity with the found pack's DTO or HTTP status 404 (NOT FOUND)
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<PackDTO> getPackById(@PathVariable Long id) {
        try {
            Optional<PackEntity> pack = packService.findPackById(id);
            return pack.map(p -> new ResponseEntity<>(EntityConverter.convertToPackDTO(p), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates a pack by its ID.
     *
     * @param id      the ID of the pack to update
     * @param packDTO the updated pack's DTO
     * @return ResponseEntity with the updated pack's DTO or HTTP status 404 (NOT FOUND)
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<PackDTO> updatePackById(@PathVariable Long id, @RequestBody PackDTO packDTO) {
        try {
            PackEntity pack = EntityConverter.convertToPackEntity(packDTO);
            Optional<PackEntity> updatedPack = packService.updatePackById(id, pack);
            return updatedPack.map(p -> new ResponseEntity<>(EntityConverter.convertToPackDTO(p), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a pack by its ID.
     *
     * @param id the ID of the pack to delete
     * @return ResponseEntity with HTTP status 204 (NO CONTENT) if deletion was successful
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePackById(@PathVariable Long id) {
        try {
            packService.deletePackById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
