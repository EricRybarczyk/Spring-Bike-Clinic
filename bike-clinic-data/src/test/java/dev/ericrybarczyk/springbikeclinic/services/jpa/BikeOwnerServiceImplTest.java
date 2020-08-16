package dev.ericrybarczyk.springbikeclinic.services.jpa;

import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import dev.ericrybarczyk.springbikeclinic.repositories.BikeOwnerRepository;
import dev.ericrybarczyk.springbikeclinic.repositories.BikeRepository;
import dev.ericrybarczyk.springbikeclinic.repositories.BikeTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BikeOwnerServiceImplTest {

    @Mock
    private BikeOwnerRepository bikeOwnerRepository;
    @Mock
    private BikeRepository bikeRepository;
    @Mock
    private BikeTypeRepository bikeTypeRepository;
    @InjectMocks
    private BikeOwnerServiceImpl bikeOwnerService;

    private final Long OWNER_ID = 1L;
    private final String LAST_NAME = "PICARD";
    private BikeOwner BIKE_OWNER_1;
    private BikeOwner BIKE_OWNER_2;
    private List<BikeOwner> BIKE_OWNER_LIST;
    private Set<BikeOwner> BIKE_OWNER_SET;

    @BeforeEach
    void setUp() {
        // build a minimal entity
        BIKE_OWNER_1 = BikeOwner.builder().id(OWNER_ID).lastName(LAST_NAME).build();
        BIKE_OWNER_2 = BikeOwner.builder().id(99L).lastName(LAST_NAME).build();

        BIKE_OWNER_LIST = new ArrayList<>(2);
        BIKE_OWNER_LIST.add(BIKE_OWNER_1);
        BIKE_OWNER_LIST.add(BIKE_OWNER_2);

        BIKE_OWNER_SET = new HashSet<>(2);
        BIKE_OWNER_SET.add(BIKE_OWNER_1);
        BIKE_OWNER_SET.add(BIKE_OWNER_2);
    }

    @Test
    void findByLastName() {
        Mockito.when(bikeOwnerRepository.findByLastName(ArgumentMatchers.anyString())).thenReturn(Optional.of(BIKE_OWNER_1));

        Optional<BikeOwner> bikeOwner = bikeOwnerService.findByLastName(LAST_NAME);

        assertTrue(bikeOwner.isPresent());
        assertEquals(LAST_NAME, bikeOwner.get().getLastName());
        Mockito.verify(bikeOwnerRepository).findByLastName(ArgumentMatchers.anyString());
    }

    @Test
    void findAllByLastName() {
        Mockito.when(bikeOwnerRepository.findAllByLastName(ArgumentMatchers.anyString())).thenReturn(BIKE_OWNER_LIST);

        List<BikeOwner> resultList = bikeOwnerService.findAllByLastName(LAST_NAME);

        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals(LAST_NAME, resultList.get(0).getLastName());
        assertEquals(LAST_NAME, resultList.get(1).getLastName());
    }

    @Test
    void findAll() {
        Mockito.when(bikeOwnerRepository.findAll()).thenReturn(BIKE_OWNER_SET);
        Set<BikeOwner> resultList = bikeOwnerService.findAll();

        assertNotNull(resultList);
        assertEquals(2, resultList.size());
    }

    @Test
    void findById() {
        Mockito.when(bikeOwnerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(BIKE_OWNER_1));
        BikeOwner resultBikeOwner = bikeOwnerService.findById(OWNER_ID);

        assertNotNull(resultBikeOwner);
        assertEquals(OWNER_ID, resultBikeOwner.getId());
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(bikeOwnerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        BikeOwner resultBikeOwner = bikeOwnerService.findById(OWNER_ID);

        assertNull(resultBikeOwner);
    }

    @Test
    void save() {
        BikeOwner bikeOwnerToSave = BikeOwner.builder().id(55L).build();
        Mockito.when(bikeOwnerRepository.save(ArgumentMatchers.any())).thenReturn(bikeOwnerToSave);
        BikeOwner resultBikeOwner = bikeOwnerService.save(bikeOwnerToSave);
        assertNotNull(resultBikeOwner);
        Mockito.verify(bikeOwnerRepository).save(ArgumentMatchers.any());
    }

    @Test
    void delete() {
        bikeOwnerService.delete(BIKE_OWNER_1);
        Mockito.verify(bikeOwnerRepository, Mockito.times(1)).delete(ArgumentMatchers.any());
    }

    @Test
    void deleteById() {
        bikeOwnerService.deleteById(OWNER_ID);
        Mockito.verify(bikeOwnerRepository, Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
    }
}