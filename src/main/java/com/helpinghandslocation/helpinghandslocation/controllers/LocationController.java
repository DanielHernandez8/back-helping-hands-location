package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.dto.LocationTagDTO;
import com.helpinghandslocation.helpinghandslocation.services.LocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    LocationServices locationServices;

    @PostMapping
    public ResponseEntity<?> saveLocations(@RequestBody LocationTagDTO locationTagDTO) {
        try {
            LocationTagDTO saveLocation = locationServices.createLocation(locationTagDTO);
            return ResponseEntity.status(201).body(saveLocation);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Error en los datos proporcionados " +e.getMessage());
        }catch (HttpMessageNotReadableException e) {
            return ResponseEntity.status(404).body("Error en los datos proporcionados " +e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al crear la ubicación " +e.getMessage());
        }
    }
}
