package dev.ericrybarczyk.springbikeclinic.web.controllers;

import dev.ericrybarczyk.springbikeclinic.model.Bike;
import dev.ericrybarczyk.springbikeclinic.model.Visit;
import dev.ericrybarczyk.springbikeclinic.services.BikeService;
import dev.ericrybarczyk.springbikeclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    private BikeService bikeService;

    @Mock
    private VisitService visitService;

    @InjectMocks
    private VisitController visitController;

    private MockMvc mockMvc;

    private Bike BIKE;
    private Visit VISIT;

    private final UriTemplate visitsUriTemplate = new UriTemplate("/bikeOwners/{bikeOwnerId}/bikes/{bikeId}/visits/new");
    private final Map<String, String> uriVariables = new HashMap<>();
    private URI visitsUri;

    @BeforeEach
    void setUp() {
        BIKE = new Bike();
        BIKE.setId(1L);
        VISIT = new Visit();
        VISIT.setId(1L);

        uriVariables.clear();
        uriVariables.put("bikeOwnerId", BIKE.getId().toString());
        uriVariables.put("bikeId", VISIT.getId().toString());
        visitsUri = visitsUriTemplate.expand(uriVariables);

        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();

        // due to class member @ModelAttribute("visit") in Controller, we need this mocked for any Controller call
        Mockito.when(bikeService.findById(ArgumentMatchers.anyLong())).thenReturn(BIKE);
    }

    @Test
    void testGetNewVisitForm() throws Exception {
        //Mockito.when(visitService)
        mockMvc.perform(MockMvcRequestBuilders.get(visitsUri))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("visit"))
                .andExpect(MockMvcResultMatchers.view().name("bikes/createOrUpdateVisitForm"));
    }

    @Test
    void testProcessNewVisitForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                    .post(visitsUri)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("date","2020-07-04")
                        .param("description", "unit test visit")
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.model().attributeExists("visit"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/bikeOwners/{bikeOwnerId}"));

        Mockito.verify(visitService).save(ArgumentMatchers.any(Visit.class));
    }

}