package com.crimsonlogic.onlinejobportal.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crimsonlogic.onlinejobportal.dto.LocationDTO;
import com.crimsonlogic.onlinejobportal.entity.Location;
import com.crimsonlogic.onlinejobportal.repository.LocationRepository;

class LocationServiceImplTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllLocations() {
        // Arrange
        Location loc1 = new Location();
        loc1.setLocationId("loc1");
        loc1.setLocationName("New York");

        Location loc2 = new Location();
        loc2.setLocationId("loc2");
        loc2.setLocationName("San Francisco");

        List<Location> locations = Arrays.asList(loc1, loc2);

        when(locationRepository.findAll()).thenReturn(locations);

        // Act
        List<LocationDTO> result = locationService.getAllLocations();

        // Assert
        assertEquals(2, result.size());
        assertEquals("loc1", result.get(0).getLocationId());
        assertEquals("New York", result.get(0).getLocationName());
        assertEquals("loc2", result.get(1).getLocationId());
        assertEquals("San Francisco", result.get(1).getLocationName());
    }
}
