package com.helpinghandslocation.helpinghandslocation.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class LocationTagDTO implements Serializable {
    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private String address;
    private List<Long>tagIds;
}