package com.helpinghandslocation.helpinghandslocation.services;

import com.helpinghandslocation.helpinghandslocation.dto.request.LoginRequestDTO;
import com.helpinghandslocation.helpinghandslocation.dto.request.RegisterUserRequestDTO;
import com.helpinghandslocation.helpinghandslocation.models.User;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

public interface AuthServices {
    String handleGoogleAuth(String token) throws GeneralSecurityException, IOException, IllegalArgumentException;

    String basicLogin(LoginRequestDTO loginRequestDTO) throws IllegalArgumentException;
}
