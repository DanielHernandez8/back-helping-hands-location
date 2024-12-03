package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.dto.CurrentUserDTO;
import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.dto.UserDTO;
import com.helpinghandslocation.helpinghandslocation.services.UserServices;
import com.helpinghandslocation.helpinghandslocation.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServices userServices;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

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
    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser() {
        try {
            CurrentUserDTO currentUserDTO = userServices.getCurrentUser();
            return ResponseEntity.status(200).body(currentUserDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al obtener el usuario: " + e.getMessage());
        }
    }
}
