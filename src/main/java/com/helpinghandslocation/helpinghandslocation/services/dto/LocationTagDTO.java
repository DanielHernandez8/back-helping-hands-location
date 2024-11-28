package com.helpinghandslocation.helpinghandslocation.services.dto;

import com.helpinghandslocation.helpinghandslocation.persistence.entities.Location;
import lombok.Data;

import java.util.List;

@Data
public class LocationTagDTO extends Location {
    private List<Integer>tagIds;
}