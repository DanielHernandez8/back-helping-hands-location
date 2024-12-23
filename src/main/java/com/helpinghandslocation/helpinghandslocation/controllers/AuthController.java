package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.dto.request.LoginRequestDTO;
import com.helpinghandslocation.helpinghandslocation.dto.request.RegisterUserRequestDTO;
import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.services.AuthServices;
import com.helpinghandslocation.helpinghandslocation.services.UserServices;
import com.helpinghandslocation.helpinghandslocation.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServices userServices;
    
    @Autowired
    AuthServices authServices;

    @PostMapping ("/basic/login")
    public ResponseEntity<?> basicLogin(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            String token = authServices.basicLogin(loginRequestDTO);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (IllegalArgumentException e) {
                return ResponseEntity.status(400).body("Error: "  + e.getMessage());
            } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error del Servidor " + e.getMessage());
        }
    }

    @PostMapping ("/basic/register")
    public ResponseEntity<?> basicRegister(@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        try {
            User createdUser = userServices.createUser(registerUserRequestDTO);
            String token = jwtTokenUtil.generateToken(createdUser.getUsername());
            return ResponseEntity.status(201).body(Map.of("token", token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al crear el usuario " + e.getMessage());
        }
    }

    @PostMapping ("/google")
    public ResponseEntity<?> handleGoogleToken(@RequestBody Map<String, String> payload) {
        String token = payload.get("token");
        try {
            token =  authServices.handleGoogleAuth(token);
            return ResponseEntity.status(200).body(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Token verification failed: " + e.getMessage());
        }
    }
}
