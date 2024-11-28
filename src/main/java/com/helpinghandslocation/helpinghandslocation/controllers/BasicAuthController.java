package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.persistence.entities.User;
import com.helpinghandslocation.helpinghandslocation.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BasicAuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(path = "/login")
    public ResponseEntity<String> basicauth(UsernamePasswordAuthenticationToken upa) {

        User u = (User) upa.getPrincipal();


        final String token = jwtTokenUtil.generateToken(u.getUsername());


        return ResponseEntity.ok()
                .body("{\"resp\":\"Login exitoso\", \"id\":" + u.getId() + ", \"token\":\"" + token + "\"}");
    }
}
