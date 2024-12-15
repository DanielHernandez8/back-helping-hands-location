package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.dto.LocationTagDTO;
import com.helpinghandslocation.helpinghandslocation.services.LocationServices;

import java.util.List;

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
            return ResponseEntity.status(200).body(saveLocation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Error en los datos proporcionados " + e.getMessage());
        } catch (HttpMessageNotReadableException e) {
            return ResponseEntity.status(404).body("Error en los datos proporcionados " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al crear la ubicación " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteLocation(@PathVariable Long id) {
        try {
            locationServices.deleteLocation(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error en los datos proporcionados: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al eliminar la ubicación: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getLocations(@RequestParam(required = false) List<Long> tagIds) {
        try {
            if(tagIds ==null || tagIds.isEmpty()){
                return ResponseEntity.status(200).body(locationServices.getLocations());
            }
            return ResponseEntity.status(200).body(locationServices.getLocationsByTagIdsAll(tagIds));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al obtener las ubicaciones " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable Long id, @RequestBody LocationTagDTO locationTagDTO) {
        try {
            // Llama al servicio con el ID del path y el DTO del body
            LocationTagDTO updatedLocation = locationServices.updateLocation(id, locationTagDTO);
            return ResponseEntity.ok(updatedLocation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error en los datos proporcionados: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al actualizar la ubicación: " + e.getMessage());
        }
    }
}
