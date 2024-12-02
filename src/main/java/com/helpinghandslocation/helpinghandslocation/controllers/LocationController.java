package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.models.Location;
import com.helpinghandslocation.helpinghandslocation.models.Tag;
import com.helpinghandslocation.helpinghandslocation.repositories.LocationRepository;
import com.helpinghandslocation.helpinghandslocation.repositories.TagRespository;
import com.helpinghandslocation.helpinghandslocation.dto.LocationTagDTO;
import com.helpinghandslocation.helpinghandslocation.services.LocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    LocationServices locationServices;

    @PostMapping
    public ResponseEntity<LocationTagDTO> saveLocations(@RequestBody LocationTagDTO locationTagDTO) {
        try {
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(locationServices.createLocation(locationTagDTO));
            //ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
