package dev.ericrybarczyk.springbikeclinic.web.controllers;

import dev.ericrybarczyk.springbikeclinic.model.Bike;
import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import dev.ericrybarczyk.springbikeclinic.model.BikeType;
import dev.ericrybarczyk.springbikeclinic.services.BikeOwnerService;
import dev.ericrybarczyk.springbikeclinic.services.BikeService;
import dev.ericrybarczyk.springbikeclinic.services.BikeTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class BikeControllerTest {

    @Mock
    BikeService bikeService;

    @Mock
    BikeOwnerService bikeOwnerService;

    @Mock
    BikeTypeService bikeTypeService;

    @InjectMocks
    BikeController bikeController;

    MockMvc mockMvc;
    Bike BIKE;
    BikeOwner BIKE_OWNER;
    Set<BikeType> BIKE_TYPES;

    @BeforeEach
    void setUp() {
        BIKE = new Bike();
        BIKE.setId(1L);
        BIKE_OWNER = BikeOwner.builder().id(1L).build();
        BIKE_TYPES = new HashSet<>();
        BIKE_TYPES.add(BikeType.builder().id(1L).build());
        BIKE_TYPES.add(BikeType.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(bikeController).build();
    }

    @Test
    void testGetNewBikeForm() throws Exception {
        Mockito.when(bikeOwnerService.findById(ArgumentMatchers.anyLong())).thenReturn(BIKE_OWNER);
        Mockito.when(bikeTypeService.findAll()).thenReturn(BIKE_TYPES);

        mockMvc.perform(MockMvcRequestBuilders.get("/bikeOwners/1/bikes/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("bikeOwner"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("bike"))
                .andExpect(MockMvcResultMatchers.view().name("bikes/createOrUpdateBikeForm"));
    }

    @Test
    void testProcessNewBikeForm() throws Exception {
        Mockito.when(bikeOwnerService.findById(ArgumentMatchers.anyLong())).thenReturn(BIKE_OWNER);
        Mockito.when(bikeTypeService.findAll()).thenReturn(BIKE_TYPES);

        mockMvc.perform(MockMvcRequestBuilders.post("/bikeOwners/1/bikes/new"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/bikeOwners/1"));

        Mockito.verify(bikeService).save(ArgumentMatchers.any(Bike.class));
    }

    @Test
    void testGetEditExistingBikeForm() throws Exception {
        Mockito.when(bikeOwnerService.findById(ArgumentMatchers.anyLong())).thenReturn(BIKE_OWNER);
        Mockito.when(bikeService.findById(ArgumentMatchers.anyLong())).thenReturn(BIKE);
        Mockito.when(bikeTypeService.findAll()).thenReturn(BIKE_TYPES);

        mockMvc.perform(MockMvcRequestBuilders.get("/bikeOwners/1/bikes/1/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("bikeOwner"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("bike"))
                .andExpect(MockMvcResultMatchers.view().name("bikes/createOrUpdateBikeForm"));
    }

    @Test
    void testSaveUpdateExistingBikeForm() throws Exception {
        Mockito.when(bikeOwnerService.findById(ArgumentMatchers.anyLong())).thenReturn(BIKE_OWNER);
        Mockito.when(bikeService.findById(ArgumentMatchers.anyLong())).thenReturn(BIKE);
        Mockito.when(bikeTypeService.findAll()).thenReturn(BIKE_TYPES);

        mockMvc.perform(MockMvcRequestBuilders.post("/bikeOwners/1/bikes/1/edit"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/bikeOwners/1"));

        Mockito.verify(bikeService).save(ArgumentMatchers.any(Bike.class));
    }
}