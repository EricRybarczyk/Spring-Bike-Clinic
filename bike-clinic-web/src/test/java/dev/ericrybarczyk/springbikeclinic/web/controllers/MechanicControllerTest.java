package dev.ericrybarczyk.springbikeclinic.web.controllers;

import dev.ericrybarczyk.springbikeclinic.model.Mechanic;
import dev.ericrybarczyk.springbikeclinic.model.Specialty;
import dev.ericrybarczyk.springbikeclinic.services.MechanicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class MechanicControllerTest {

    @Mock
    private MechanicService mechanicService;

    @InjectMocks
    private MechanicController controller;

    private MockMvc mockMvc;
    private Set<Mechanic> MECHANICS_SET;

    @BeforeEach
    void setUp() {
        MECHANICS_SET = new HashSet<>();

        Specialty specialty1 = Specialty.builder().id(1L).description("Specialty 1").build();
        Specialty specialty2 = Specialty.builder().id(2L).description("Specialty 2").build();
        Mechanic mechanic1 = Mechanic.builder().id(1L).firstName("First").lastName("Mechanic").specialty(specialty1).build();
        Mechanic mechanic2 = Mechanic.builder().id(2L).firstName("Primera").lastName("Mecanica").specialty(specialty2).build();

        MECHANICS_SET.add(mechanic1);
        MECHANICS_SET.add(mechanic2);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testListMechanics() throws Exception {
        // given
        Mockito.when(mechanicService.findAll()).thenReturn(MECHANICS_SET);

        // when-then
        mockMvc.perform(MockMvcRequestBuilders.get("/mechanics"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("mechanics"))
                .andExpect(MockMvcResultMatchers.view().name("mechanics/index"));
    }

    @Test
    void testGetMechanicsJson() throws Exception {
        // given
        Mockito.when(mechanicService.findAll()).thenReturn(MECHANICS_SET);
        String EXPECTED_JSON = "[{\"id\":1,\"firstName\":\"First\",\"lastName\":\"Mechanic\",\"specialties\":[{\"id\":1,\"description\":\"Specialty 1\",\"new\":false}],\"new\":false},{\"id\":2,\"firstName\":\"Primera\",\"lastName\":\"Mecanica\",\"specialties\":[{\"id\":2,\"description\":\"Specialty 2\",\"new\":false}],\"new\":false}]";

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/mechanics").accept(MediaType.APPLICATION_JSON)).andReturn();

        // then
        JSONAssert.assertEquals(EXPECTED_JSON, mvcResult.getResponse().getContentAsString(), false);
    }

}