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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class BikeOwnerControllerTest {

    @Mock
    private BikeOwnerService bikeOwnerService;
    @InjectMocks
    private BikeOwnerController controller;

    private MockMvc mockMvc;
    private Set<BikeOwner> BIKE_OWNERS_SET;
    private List<BikeOwner> BIKE_OWNERS_LIST;

    @BeforeEach
    void setUp() {
        BIKE_OWNERS_SET = new HashSet<>();
        BIKE_OWNERS_SET.add(BikeOwner.builder().id(1L).build());
        BIKE_OWNERS_SET.add(BikeOwner.builder().id(2L).build());

        BIKE_OWNERS_LIST = new ArrayList<>();
        BIKE_OWNERS_LIST.add(BikeOwner.builder().id(1L).build());
        BIKE_OWNERS_LIST.add(BikeOwner.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findBikeOwners() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bikeOwners/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bikeOwners/findOwners"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("bikeOwner"));

        Mockito.verifyNoInteractions(bikeOwnerService);
    }

    @Test
    void testFindFormReturnMultiple() throws Exception {
        Mockito.when(bikeOwnerService.findAll()).thenReturn(BIKE_OWNERS_SET);

        mockMvc.perform(MockMvcRequestBuilders.get("/bikeOwners/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bikeOwners/ownersList"))
                .andExpect(MockMvcResultMatchers.model().attribute("bikeOwners", hasSize(2)));
    }

    @Test
    void testFindFormReturnOne() throws Exception {
        Set<BikeOwner> BIKE_OWNERS_SET_SINGLE = new HashSet<>();
        BIKE_OWNERS_SET_SINGLE.add(BikeOwner.builder().id(1L).build());
        Mockito.when(bikeOwnerService.findAll()).thenReturn(BIKE_OWNERS_SET_SINGLE);

        mockMvc.perform(MockMvcRequestBuilders.get("/bikeOwners/"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/bikeOwners/1"));
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