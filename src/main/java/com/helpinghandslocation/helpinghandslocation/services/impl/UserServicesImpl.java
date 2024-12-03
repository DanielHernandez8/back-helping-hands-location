package com.helpinghandslocation.helpinghandslocation.services.impl;

import com.helpinghandslocation.helpinghandslocation.config.Encoder;
import com.helpinghandslocation.helpinghandslocation.dto.CurrentUserDTO;
import com.helpinghandslocation.helpinghandslocation.dto.UserDTO;
import com.helpinghandslocation.helpinghandslocation.models.Type;
import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.repositories.TypeRepository;
import com.helpinghandslocation.helpinghandslocation.repositories.UserRepository;
import com.helpinghandslocation.helpinghandslocation.services.UserMapper;
import com.helpinghandslocation.helpinghandslocation.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {


    @Autowired
    UserRepository userRepository;
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    private UserMapper userMapper;


    @Override
    public User createUser(UserDTO userDTO) {
        User user = new User();
        Type type = typeRepository.findById(userDTO.getTypeId()).orElse(null);
        String encryptedPassword = Encoder.passwordencoder().encode(userDTO.getPassword());
        user.setPassword(encryptedPassword);
        user.setUsername(userDTO.getUsername().toLowerCase());
        user.setType(type);
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public CurrentUserDTO getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User) {
            User user = (User) principal;

            // Usar el mapper para convertir User a CurrentUserDTO
            return userMapper.toCurrentUserDTO(user);
        } else {
            throw new IllegalStateException("Usuario no autenticado o tipo inesperado");
        }
    }
}




