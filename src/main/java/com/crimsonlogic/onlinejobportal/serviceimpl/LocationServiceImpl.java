package com.crimsonlogic.onlinejobportal.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.onlinejobportal.dto.LocationDTO;
import com.crimsonlogic.onlinejobportal.entity.Location;
import com.crimsonlogic.onlinejobportal.repository.LocationRepository;
import com.crimsonlogic.onlinejobportal.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(location -> {
                    LocationDTO dto = new LocationDTO();
                    dto.setLocationId(location.getLocationId());
                    dto.setLocationName(location.getLocationName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

