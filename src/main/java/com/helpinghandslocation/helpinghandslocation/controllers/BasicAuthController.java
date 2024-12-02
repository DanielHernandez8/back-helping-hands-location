package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.utils.JwtTokenUtil;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class BasicAuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @PostMapping
    public ResponseEntity<?> login(User user) {
        try {
            String token = jwtTokenUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (IllegalArgumentException e) {
                return ResponseEntity.status(400).body("Error: Username o contraseña incorrectos "  + e.getMessage());
            } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error del Servidor " + e.getMessage());
        }
    }
}
