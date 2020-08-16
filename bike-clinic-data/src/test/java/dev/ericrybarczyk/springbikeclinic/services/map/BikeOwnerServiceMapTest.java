package dev.ericrybarczyk.springbikeclinic.services.map;

import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import dev.ericrybarczyk.springbikeclinic.services.BikeOwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BikeOwnerServiceMapTest {

    private BikeOwnerService bikeOwnerService;
    private final Long OWNER_ID = 1L;
    private final String LAST_NAME = "PICARD";

    @BeforeEach
    void setUp() {
        // no real need to mock these since we are testing the HashMap implementations here
        bikeOwnerService = new BikeOwnerServiceMap(new BikeTypeServiceMap(), new BikeServiceMap());

        // build and save a minimal entity
        bikeOwnerService.save(BikeOwner.builder().id(OWNER_ID).lastName(LAST_NAME).build());
    }

    @Test
    void findAll() {
        Set<BikeOwner> owners = bikeOwnerService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        BikeOwner bikeOwner = bikeOwnerService.findById(OWNER_ID);
        assertEquals(OWNER_ID, bikeOwner.getId());
    }

    @Test
    void saveWithExistingId() {
        Long ID = 2L;
        BikeOwner bikeOwner = BikeOwner.builder().id(ID).build();
        BikeOwner savedBikeOwner = bikeOwnerService.save(bikeOwner);
        assertEquals(bikeOwner.getId(), savedBikeOwner.getId());
    }

    @Test
    void saveWithNoId() {
        BikeOwner savedBikeOwner = bikeOwnerService.save(BikeOwner.builder().build());
        assertNotNull(savedBikeOwner);
        assertNotNull(savedBikeOwner.getId());
    }

    @Test
    void delete() {
        bikeOwnerService.delete(bikeOwnerService.findById(OWNER_ID));
        assertEquals(0, bikeOwnerService.findAll().size());
    }

    @Test
    void deleteById() {
        bikeOwnerService.deleteById(OWNER_ID);
        assertEquals(0, bikeOwnerService.findAll().size());
    }

    @Test
    void findByLastName() {
        Optional<BikeOwner> bikeOwner = bikeOwnerService.findByLastName(LAST_NAME);
        assertTrue(bikeOwner.isPresent());
        assertEquals(LAST_NAME, bikeOwner.get().getLastName());
    }

    @Test
    void findAllByLastName() {
        Long secondId = 99L;
        bikeOwnerService.save(BikeOwner.builder().id(secondId).lastName(LAST_NAME).build());
        List<BikeOwner> allByLastName = bikeOwnerService.findAllByLastName(LAST_NAME);
        assertEquals(2, allByLastName.size());
    }
}