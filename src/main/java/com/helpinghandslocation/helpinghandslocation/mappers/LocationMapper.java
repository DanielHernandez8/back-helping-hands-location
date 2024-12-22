package com.helpinghandslocation.helpinghandslocation.mappers;

import com.helpinghandslocation.helpinghandslocation.dto.response.LocationCreatorDTO;
import com.helpinghandslocation.helpinghandslocation.models.Location;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    
    List<LocationCreatorDTO> locationTagCreatorDTOList(List<Location> locations);
    
    default LocationCreatorDTO locationTagCreatorDTO(Location location) {
        LocationCreatorDTO locationCreatorDTO = new LocationCreatorDTO();
        locationCreatorDTO.setId(location.getId());
        locationCreatorDTO.setName(location.getName());
        locationCreatorDTO.setTags(location.getTags());
        locationCreatorDTO.setAddress(location.getAddress());
        locationCreatorDTO.setLatitude(location.getLatitude());
        locationCreatorDTO.setLongitude(location.getLongitude());

        Long creatorId = null;
        if (location.getCreatedBy() != null) {
            creatorId = location.getCreatedBy().getId();
        }

        locationCreatorDTO.setCreatorId(creatorId);

        return locationCreatorDTO;
    }
}
