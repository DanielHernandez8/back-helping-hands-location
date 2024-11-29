package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.persistence.entities.User;
import com.helpinghandslocation.helpinghandslocation.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
public class BasicAuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(path = "/login")
    public ResponseEntity<Map<String, String>> basicauth(UsernamePasswordAuthenticationToken upa) {
        // Validar que el principal exista
        if (upa == null || upa.getPrincipal() == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Invalid authentication token"));
        }

        // Extraer el usuario del principal
        User user = (User) upa.getPrincipal();

        // Generar el token JWT
        final String token = jwtTokenUtil.generateToken(user.getUsername());

        // Retornar solo el token en formato JSON
        return ResponseEntity.ok(Map.of("token", token));
    }
}
