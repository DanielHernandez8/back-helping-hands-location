package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.persistence.entities.Location;
import com.helpinghandslocation.helpinghandslocation.persistence.entities.Tag;
import com.helpinghandslocation.helpinghandslocation.persistence.repositories.LocationRepository;
import com.helpinghandslocation.helpinghandslocation.persistence.repositories.TagRespository;
import com.helpinghandslocation.helpinghandslocation.services.dto.LocationTagDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    TagRespository tagRespository;

    @PostMapping
    public Location saveLocations(@RequestBody LocationTagDTO locationTagDTO) {
        Location location = new Location();
        location.setName(locationTagDTO.getName());
        location.setLatitude(locationTagDTO.getLatitude());
        location.setLongitude(locationTagDTO.getLongitude());

        for (Integer tagId : locationTagDTO.getTagIds()) {
            Tag tag = tagRespository.findById(Long.valueOf(tagId)).orElse(null);
            location.getTags().add(tag);
        }

        return locationRepository.save(location);
    }
}
