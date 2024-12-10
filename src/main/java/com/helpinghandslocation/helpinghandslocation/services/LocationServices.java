package com.helpinghandslocation.helpinghandslocation.services;

import com.helpinghandslocation.helpinghandslocation.dto.LocationTagDTO;
import com.helpinghandslocation.helpinghandslocation.models.Location;

import java.util.List;

public interface LocationServices {
    LocationTagDTO createLocation (LocationTagDTO locationTagDTO);

    List<Location> getLocations();

    LocationTagDTO updateLocation(Long id,LocationTagDTO locationTagDTO);

    Location deleteLocation(Long id);
}
