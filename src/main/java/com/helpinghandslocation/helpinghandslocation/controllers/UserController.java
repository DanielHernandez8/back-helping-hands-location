package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.dto.CurrentUserDTO;
import com.helpinghandslocation.helpinghandslocation.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServices userServices;
    
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
