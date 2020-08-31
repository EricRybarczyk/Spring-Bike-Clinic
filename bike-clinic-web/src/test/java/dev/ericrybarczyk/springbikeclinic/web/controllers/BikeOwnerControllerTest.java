package dev.ericrybarczyk.springbikeclinic.web.controllers;

import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import dev.ericrybarczyk.springbikeclinic.services.BikeOwnerService;
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

import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class BikeOwnerControllerTest {

    @Mock
    private BikeOwnerService bikeOwnerService;
    @InjectMocks
    private BikeOwnerController controller;

    private MockMvc mockMvc;
    private Set<BikeOwner> BIKE_OWNERS;
    public static final String BIKE_OWNERS_INDEX_GET_PATH = "/bikeOwners";
    public static final String BIKE_OWNERS_FIND_GET_PATH = "/bikeOwners/find";
    private static final String BIKE_OWNERS_INDEX_VIEW_NAME = "bikeOwners/index";
    private static final String BIKE_OWNERS_MODEL_ATTRIBUTE_NAME = "owners";
    private static final String NOT_IMPLEMENTED_VIEW_NAME = "notimplemented";

    @BeforeEach
    void setUp() {
        BIKE_OWNERS = new HashSet<>();
        BIKE_OWNERS.add(BikeOwner.builder().id(1L).build());
        BIKE_OWNERS.add(BikeOwner.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listBikeOwners() throws Exception {
        Mockito.when(bikeOwnerService.findAll()).thenReturn(BIKE_OWNERS);

        mockMvc.perform(MockMvcRequestBuilders.get(BIKE_OWNERS_INDEX_GET_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(BIKE_OWNERS_INDEX_VIEW_NAME))
                .andExpect(MockMvcResultMatchers.model().attribute(BIKE_OWNERS_MODEL_ATTRIBUTE_NAME, hasSize(2)));
    }

    @Test
    void listBikeOwnersWithIndexPathSegment() throws Exception {
        Mockito.when(bikeOwnerService.findAll()).thenReturn(BIKE_OWNERS);

        mockMvc.perform(MockMvcRequestBuilders.get(BIKE_OWNERS_INDEX_GET_PATH + "/index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(BIKE_OWNERS_INDEX_VIEW_NAME))
                .andExpect(MockMvcResultMatchers.model().attribute(BIKE_OWNERS_MODEL_ATTRIBUTE_NAME, hasSize(2)));
    }

    @Test
    void findBikeOwners() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BIKE_OWNERS_FIND_GET_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(NOT_IMPLEMENTED_VIEW_NAME));
        // currently the method being tested is not implemented, so make sure no interactions
        Mockito.verifyNoInteractions(bikeOwnerService);
    }

    @Test
    void testShowBikeOwner() throws Exception {
        Mockito.when(bikeOwnerService.findById(ArgumentMatchers.anyLong())).thenReturn(BikeOwner.builder().id(1L).build());

        mockMvc.perform(MockMvcRequestBuilders.get("/bikeOwners/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bikeOwners/bikeOwnerDetails"))
                .andExpect(MockMvcResultMatchers.model().attribute("bikeOwner", hasProperty("id", is(1L))));
    }

}