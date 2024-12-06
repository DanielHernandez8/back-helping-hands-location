package com.helpinghandslocation.helpinghandslocation.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Map;

public interface BasicAuthServices {
    ResponseEntity<Map<String, String>> basicauth(UsernamePasswordAuthenticationToken token);
}
