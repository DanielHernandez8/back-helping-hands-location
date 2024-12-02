package com.helpinghandslocation.helpinghandslocation.services.impl;

import com.helpinghandslocation.helpinghandslocation.config.Encoder;
import com.helpinghandslocation.helpinghandslocation.dto.UserDTO;
import com.helpinghandslocation.helpinghandslocation.models.Type;
import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.repositories.UserRepository;
import com.helpinghandslocation.helpinghandslocation.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {


    @Autowired
    UserRepository  userRepository;


    @Override
    public User createUser(UserDTO userDTO) {
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



