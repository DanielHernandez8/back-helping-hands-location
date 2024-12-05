package com.helpinghandslocation.helpinghandslocation.services;

import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

public interface AuthServices {
    ResponseEntity<Map<String, String>> handleGoogleAuth(String token) throws GeneralSecurityException, IOException;
}
