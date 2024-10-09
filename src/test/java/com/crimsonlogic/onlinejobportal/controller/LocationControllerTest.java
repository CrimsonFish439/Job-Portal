package com.crimsonlogic.onlinejobportal.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crimsonlogic.onlinejobportal.dto.LocationDTO;
import com.crimsonlogic.onlinejobportal.service.LocationService;

class LocationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(locationController).build();
    }

    @Test
    void testGetAllLocations() throws Exception {
        LocationDTO location1 = new LocationDTO();
        location1.setLocationId("LOC123");
        location1.setLocationName("New York");

        LocationDTO location2 = new LocationDTO();
        location2.setLocationId("LOC124");
        location2.setLocationName("Los Angeles");

        List<LocationDTO> locations = Arrays.asList(location1, location2);
        when(locationService.getAllLocations()).thenReturn(locations);

        mockMvc.perform(get("/api/locations/getalllocations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].locationName").value("New York"))
                .andExpect(jsonPath("$[1].locationName").value("Los Angeles"));

        verify(locationService, times(1)).getAllLocations();
    }
}
