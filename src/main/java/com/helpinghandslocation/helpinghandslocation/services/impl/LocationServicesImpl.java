package com.helpinghandslocation.helpinghandslocation.services.impl;

import com.helpinghandslocation.helpinghandslocation.dto.LocationTagDTO;
import com.helpinghandslocation.helpinghandslocation.dto.response.LocationCreatorDTO;
import com.helpinghandslocation.helpinghandslocation.mappers.LocationMapper;
import com.helpinghandslocation.helpinghandslocation.models.Location;
import com.helpinghandslocation.helpinghandslocation.models.Tag;
import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.repositories.LocationRepository;
import com.helpinghandslocation.helpinghandslocation.repositories.TagRespository;
import com.helpinghandslocation.helpinghandslocation.repositories.UserRepository;
import com.helpinghandslocation.helpinghandslocation.services.LocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServicesImpl implements LocationServices {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private TagRespository tagRespository;

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private LocationMapper locationMapper;

    @Override
    public LocationTagDTO createLocation(LocationTagDTO locationTagDTO) {
        User currentUser = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (locationTagDTO.getId() != null) {
            throw new IllegalArgumentException("El ID no debe proporcionarse en la creación");
        }

        if(authentication != null && authentication.isAuthenticated()){
            currentUser = userRepository.findByUsername(authentication.getName());
        }

        Location location = new Location();
        location.setName(locationTagDTO.getName());
        location.setLatitude(locationTagDTO.getLatitude());
        location.setLongitude(locationTagDTO.getLongitude());
        location.setAddress(locationTagDTO.getAddress());
        location.setCreatedBy(currentUser);

        for (Long tagId : locationTagDTO.getTagIds()) {
            Tag tag = tagRespository.findById(tagId)
                    .orElseThrow(() -> new IllegalArgumentException("Tag no encontrado con ID: " + tagId));
            location.getTags().add(tag);
        }

        Location locationTarget = locationRepository.save(location);

        LocationTagDTO responseDTO = new LocationTagDTO();
        responseDTO.setId(locationTarget.getId()); 
        responseDTO.setName(locationTarget.getName());
        responseDTO.setLatitude(locationTarget.getLatitude());
        responseDTO.setLongitude(locationTarget.getLongitude());
        responseDTO.setTagIds(locationTarget.getTags().stream().map(Tag::getId).toList());

        return responseDTO;
    }

    @Override
    public List<LocationCreatorDTO> getLocations(List<Long> tagIds) {

        if(tagIds ==null || tagIds.isEmpty()){
            return locationMapper.locationTagCreatorDTOList(locationRepository.findAll());
        }
        return locationMapper.locationTagCreatorDTOList(locationRepository.findByTagsIdInAll(tagIds));

    }

    @Override
    public LocationTagDTO updateLocation(Long id, LocationTagDTO locationTagDTO) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ubicación no encontrada con ID: " + id));

        location.setName(locationTagDTO.getName());
        location.setLatitude(locationTagDTO.getLatitude());
        location.setLongitude(locationTagDTO.getLongitude());

        location.getTags().clear();
        for (Long tagId : locationTagDTO.getTagIds()) {
            Tag tag = tagRespository.findById(tagId)
                    .orElseThrow(() -> new IllegalArgumentException("Tag no encontrado con ID: " + tagId));
            location.getTags().add(tag);
        }

        Location locationUpdated = locationRepository.save(location);

        LocationTagDTO responseDTO = new LocationTagDTO();
        responseDTO.setId(locationUpdated.getId()); 
        responseDTO.setName(locationUpdated.getName());
        responseDTO.setLatitude(locationUpdated.getLatitude());
        responseDTO.setLongitude(locationUpdated.getLongitude());
        responseDTO.setTagIds(locationUpdated.getTags().stream().map(Tag::getId).toList());

        return responseDTO;
    }

    @Override
    public void deleteLocation(Long id) {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("The provided ID is invalid.");
            }
            Location location = new Location();
            location.setId(id);
            locationRepository.delete(location);    
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException( "An unexpected error occurred while deleting the location: " + e.getMessage());
        }
    }
}
