package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class BasicAuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @PostMapping
    public ResponseEntity<String> login(User user) {
        try {
            return ResponseEntity.status(200).body(jwtTokenUtil.generateToken(user.getUsername())); 
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
