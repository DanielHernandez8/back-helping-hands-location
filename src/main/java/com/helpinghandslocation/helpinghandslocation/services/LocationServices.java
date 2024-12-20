package com.helpinghandslocation.helpinghandslocation.services;

import com.helpinghandslocation.helpinghandslocation.dto.LocationTagDTO;
import com.helpinghandslocation.helpinghandslocation.dto.response.LocationCreatorDTO;
import com.helpinghandslocation.helpinghandslocation.models.Location;

import java.util.List;

public interface LocationServices {
    LocationTagDTO createLocation (LocationTagDTO locationTagDTO);

    List<LocationCreatorDTO> getLocations(List<Long> tagIds);

    LocationTagDTO updateLocation(Long id,LocationTagDTO locationTagDTO);

    void deleteLocation(Long id);
}
