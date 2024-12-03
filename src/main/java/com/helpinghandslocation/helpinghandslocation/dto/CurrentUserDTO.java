package com.helpinghandslocation.helpinghandslocation.dto;

import lombok.Data;

@Data
public class CurrentUserDTO {
    private int id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private int typeId;
}
