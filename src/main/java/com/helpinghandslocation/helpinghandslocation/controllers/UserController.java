package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.dto.response.CurrentUserResponseDTO;
import com.helpinghandslocation.helpinghandslocation.dto.response.TokenResponseDTO;
import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.dto.request.RegisterUserRequestDTO;
import com.helpinghandslocation.helpinghandslocation.services.UserServices;
import com.helpinghandslocation.helpinghandslocation.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServices userServices;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        try {
            User createdUser = userServices.createUser(registerUserRequestDTO);
            String token = jwtTokenUtil.generateToken(createdUser.getUsername());
//            return ResponseEntity.status(201).body(Map.of("token", token));
            return ResponseEntity.status(201).body(TokenResponseDTO.builder().token(token).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al crear el usuario " + e.getMessage());
        }
    }
    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser() {
        try {
            CurrentUserResponseDTO currentUserResponseDTO = userServices.getCurrentUser();
            return ResponseEntity.status(200).body(currentUserResponseDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al obtener el usuario: " + e.getMessage());
        }
    }
}
