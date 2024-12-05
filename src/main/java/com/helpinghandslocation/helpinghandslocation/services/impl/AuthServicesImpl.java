package com.helpinghandslocation.helpinghandslocation.services.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.helpinghandslocation.helpinghandslocation.config.Encoder;
import com.helpinghandslocation.helpinghandslocation.dto.UserDTO;
import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.repositories.UserRepository;
import com.helpinghandslocation.helpinghandslocation.services.AuthServices;
import com.helpinghandslocation.helpinghandslocation.services.UserServices;
import com.helpinghandslocation.helpinghandslocation.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;

@Service
public class AuthServicesImpl implements AuthServices {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String GOOGLE_CLIENT_ID;

    @Autowired
    private UserServices userServices;

    @Autowired 
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Map<String, String>> handleGoogleAuth(String token) throws GeneralSecurityException, IOException {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                new GsonFactory()
        ).setAudience(Collections.singletonList(GOOGLE_CLIENT_ID)).build();

        GoogleIdToken idToken = verifier.verify(token);
        if (idToken != null) {
            GoogleIdToken.Payload idPayload = idToken.getPayload();

            String email = idPayload.getEmail();
            String firstName = (String) idPayload.get("given_name");
            String lastName = null;

            try {
                lastName = (String) idPayload.get("family_name");
            } catch (Exception e) {
            }

            User user = userRepository.findByUsername(email);

            if (user == null) {
                UserDTO userDTO = new UserDTO();
                userDTO.setEmail(email);
                userDTO.setFirstName(firstName);
                userDTO.setLastName(lastName);
                userDTO.setUsername(email);
                userDTO.setPassword(Encoder.passwordencoder().encode("unused-password"));
                user = userServices.createUser(userDTO);
            } 
            token = jwtTokenUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body(Map.of("Error","Invalid token"));
        }
    }
}
