package com.helpinghandslocation.helpinghandslocation.services.impl;

import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.services.BasicAuthServices;
import com.helpinghandslocation.helpinghandslocation.services.UserServices;
import com.helpinghandslocation.helpinghandslocation.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BasicAuthServicesImpl implements BasicAuthServices {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
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
