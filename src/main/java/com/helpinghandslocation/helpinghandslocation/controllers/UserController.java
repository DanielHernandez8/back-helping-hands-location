package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.config.Encoder;
import com.helpinghandslocation.helpinghandslocation.persistence.entities.Type;
import com.helpinghandslocation.helpinghandslocation.persistence.entities.User;
import com.helpinghandslocation.helpinghandslocation.persistence.repositories.UserRepository;
import com.helpinghandslocation.helpinghandslocation.services.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    public User register(@RequestBody UserDTO userDTO) {
        User user = new User();
        Type type = new Type();
        type.setId(userDTO.getTypeId());
        String encryptedPassword = Encoder.passwordencoder().encode(userDTO.getPassword());
        user.setPassword(encryptedPassword);
        user.setUsername(userDTO.getUsername().toLowerCase());
        user.setType(type);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

}
