package com.helpinghandslocation.helpinghandslocation.services;

import com.helpinghandslocation.helpinghandslocation.dto.request.RegisterUserRequestDTO;
import com.helpinghandslocation.helpinghandslocation.models.User;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

public interface AuthServices {
    ResponseEntity<Map<String, String>> handleGoogleAuth(String token) throws GeneralSecurityException, IOException;
    User createUser(RegisterUserRequestDTO registerUserRequestDTO);

}
