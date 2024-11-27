package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.persistence.entities.Location;
import com.helpinghandslocation.helpinghandslocation.persistence.repositories.LocationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
public class LocationController {
    @Autowired
    LocationRepository locationRepository;

    @PostMapping("/locations")
    public Location getLocations(@RequestBody Location locations) {
        Location location = new Location();
        location.setName(location.getName());
        location.setLatitude(location.getLatitude());
        location.setLongitude(location.getLongitude());
        Location savedLocation = locationRepository.save(location);
        return savedLocation;
    }

}
