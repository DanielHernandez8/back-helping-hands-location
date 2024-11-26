package com.helpinghandslocation.helpinghandslocation.config;

import com.helpinghandslocation.helpinghandslocation.persistence.entities.Role;
import com.helpinghandslocation.helpinghandslocation.persistence.entities.User;
import com.helpinghandslocation.helpinghandslocation.persistence.repositories.RoleRepository;
import com.helpinghandslocation.helpinghandslocation.persistence.repositories.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class DataLoader implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crear roles
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);

        // Crear usuarios
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(Encoder.passwordencoder().encode("adminpass"));
        admin.setRoles(List.of(adminRole));
        admin.setEnabled(true);
        userRepository.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setPassword(Encoder.passwordencoder().encode("userpass"));
        user.setRoles(List.of(userRole));
        user.setEnabled(true);
        userRepository.save(user);
    }
}

