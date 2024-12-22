package com.helpinghandslocation.helpinghandslocation.services.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.helpinghandslocation.helpinghandslocation.config.Encoder;
import com.helpinghandslocation.helpinghandslocation.dto.request.LoginRequestDTO;
import com.helpinghandslocation.helpinghandslocation.dto.request.RegisterUserRequestDTO;
import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.repositories.UserRepository;
import com.helpinghandslocation.helpinghandslocation.services.AuthServices;
import com.helpinghandslocation.helpinghandslocation.services.UserServices;
import com.helpinghandslocation.helpinghandslocation.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import java.util.Collections;

@Service
public class AuthServicesImpl implements AuthServices {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String GOOGLE_CLIENT_ID;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServices userServices;

    @Override
    public String basicLogin(LoginRequestDTO loginRequestDTO) throws IllegalArgumentException{

        User user = userRepository.findByUsername(loginRequestDTO.getUsername());

        if(user == null){
            throw new IllegalArgumentException("User not found");
        } else if(!Encoder.passwordencoder().matches(loginRequestDTO.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Invalid password");
        }

        return jwtTokenUtil.generateToken(loginRequestDTO.getUsername());
    }

    @Override
    public String handleGoogleAuth(String token) throws GeneralSecurityException, IOException {

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
                RegisterUserRequestDTO userDTO = new RegisterUserRequestDTO();

                userDTO.setEmail(email);
                userDTO.setFirstName(firstName);
                userDTO.setLastName(lastName);
                userDTO.setUsername(email);
                userDTO.setPassword(Encoder.passwordencoder().encode("unused-password"));
                userDTO.setPhoneNumber(null);
                userDTO.setTypeId(null);

                user = userServices.createUser(userDTO);
            } 
            return jwtTokenUtil.generateToken(user.getUsername());
        } else {
            throw new IllegalArgumentException("Invalid token");
        }
    }
}
