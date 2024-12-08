package com.helpinghandslocation.helpinghandslocation.services.impl;

import com.helpinghandslocation.helpinghandslocation.dto.LocationTagDTO;
import com.helpinghandslocation.helpinghandslocation.models.Location;
import com.helpinghandslocation.helpinghandslocation.models.Tag;
import com.helpinghandslocation.helpinghandslocation.repositories.LocationRepository;
import com.helpinghandslocation.helpinghandslocation.repositories.TagRespository;
import com.helpinghandslocation.helpinghandslocation.services.LocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServicesImpl implements LocationServices {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    TagRespository tagRespository;

//    @Override
//    public Boolean createLocation(LocationTagDTO locationTagDTO) {
//        Location location = new Location();
//        location.setName(locationTagDTO.getName());
//        location.setLatitude(locationTagDTO.getLatitude());
//        location.setLongitude(locationTagDTO.getLongitude());
//
//        for (Integer tagId : locationTagDTO.getTagIds()) {
//            Tag tag = tagRespository.findById(tagId).orElse(null);
//            location.getTags().add(tag);
//        }
//
//        locationRepository.save(location);
//
//        return true;
//    }

    @Override
    public LocationTagDTO createLocation(LocationTagDTO locationTagDTO) {
        Location location = new Location();
        location.setName(locationTagDTO.getName());
        location.setLatitude(locationTagDTO.getLatitude());
        location.setLongitude(locationTagDTO.getLongitude());

        for (Long tagId : locationTagDTO.getTagIds()) {
            Tag tag = tagRespository.findById(tagId).orElse(null);
            location.getTags().add(tag);
        }

       Location locationTarget = locationRepository.save(location);

//         Mapear la entidad de vuelta a un DTO para devolverlo
        LocationTagDTO responseDTO = new LocationTagDTO();
        responseDTO.setName(locationTarget.getName());
        responseDTO.setId(locationTarget.getId());
        responseDTO.setLongitude(locationTarget.getLongitude());
        responseDTO.setLatitude(locationTarget.getLatitude());
        responseDTO.setTagIds(locationTarget.getTags().stream().map(Tag::getId).toList());
        return responseDTO;
    }
}
