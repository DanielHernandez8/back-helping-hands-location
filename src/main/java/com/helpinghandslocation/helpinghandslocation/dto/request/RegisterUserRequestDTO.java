package com.helpinghandslocation.helpinghandslocation.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterUserRequestDTO implements Serializable {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = true)
    private String lastName;
    @Column(nullable = true)
    private String phoneNumber;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;


    private Long typeId;
}
