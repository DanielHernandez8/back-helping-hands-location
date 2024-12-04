package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.config.Encoder;
import com.helpinghandslocation.helpinghandslocation.dto.UserDTO;
import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.repositories.UserRepository;
import com.helpinghandslocation.helpinghandslocation.services.UserServices;
import com.helpinghandslocation.helpinghandslocation.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.util.Map;
import java.util.Collections;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserServices userServices;

    @Autowired
    UserRepository userRepository;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String GOOGLE_CLIENT_ID;


    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
        try {
            User createdUser = userServices.createUser(userDTO);
            String token = jwtTokenUtil.generateToken(createdUser.getUsername());
            return ResponseEntity.status(201).body(Map.of("token", token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al crear el usuario " + e.getMessage());
        }
    }

    @PostMapping ("/basic/login")
    public ResponseEntity<?> basicLogin(User user) {
        try {
            String token = jwtTokenUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (IllegalArgumentException e) {
                return ResponseEntity.status(400).body("Error: Username o contraseña incorrectos "  + e.getMessage());
            } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error del Servidor " + e.getMessage());
        }
    }

    @PostMapping ("/basic/register")
    public ResponseEntity<?> basicRegister(@RequestBody UserDTO userDTO) {
        try {
            User createdUser = userServices.createUser(userDTO);
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
            // Verify the Google token
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(),
                    new GsonFactory()
            ).setAudience(Collections.singletonList(GOOGLE_CLIENT_ID)).build();

            GoogleIdToken idToken = verifier.verify(token);
            if (idToken != null) {
                GoogleIdToken.Payload idPayload = idToken.getPayload();

                // Extract user details
                String email = idPayload.getEmail();
                String firstName = (String) idPayload.get("given_name");
                String lastName = null;

                try {
                    lastName = (String) idPayload.get("family_name");
                } catch (Exception e) {
                    // Do nothing, lastName will be null
                }

                // Check if user exists in the database
                User user = userRepository.findByUsername(email);
                if (user == null) {
                    user = new User();
                    user.setEmail(email);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setUsername(email);
                    user.setPassword(Encoder.passwordencoder().encode("unused-password"));




                    userRepository.save(user);
                    return ResponseEntity.ok(Map.of(
                            "message", "User registered successfully",
                            "user", user
                    ));
                } else {
                    // Existing user (Login)
                    return ResponseEntity.ok(Map.of(
                            "message", "User logged in successfully",
                            "user", user
                    ));
                }
            } else {
                return ResponseEntity.status(401).body("Invalid token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Token verification failed: " + e.getMessage());
        }
    }
}
