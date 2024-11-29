package com.helpinghandslocation.helpinghandslocation.dto;

import com.helpinghandslocation.helpinghandslocation.models.Location;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LocationTagDTO extends Location {
    private List<Integer>tagIds;
}