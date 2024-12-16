package com.helpinghandslocation.helpinghandslocation.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CurrentUserResponseDTO implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private int typeId;
}
