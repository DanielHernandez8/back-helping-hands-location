package com.helpinghandslocation.helpinghandslocation.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import com.helpinghandslocation.helpinghandslocation.models.Tag;

@Getter
@Setter
public class LocationCreatorDTO implements Serializable {

    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private String address;
    private List<Tag> tags;
    private Long creatorId;

    public LocationCreatorDTO(String name, double latitude, double longitude, String address, List<Tag> tags, Long creatorId) {
        this.name = name;
        this.latitude=latitude;
        this.longitude = longitude;
        this.address=address;
        this.tags = tags;
        this.creatorId = creatorId;
    }

    public LocationCreatorDTO() {

    }
}