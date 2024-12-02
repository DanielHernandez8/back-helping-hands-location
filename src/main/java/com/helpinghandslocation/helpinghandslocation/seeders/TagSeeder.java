package com.helpinghandslocation.helpinghandslocation.seeders;

import com.helpinghandslocation.helpinghandslocation.persistence.entities.Tag;
import com.helpinghandslocation.helpinghandslocation.persistence.entities.User;
import com.helpinghandslocation.helpinghandslocation.persistence.repositories.TagRespository;
import com.helpinghandslocation.helpinghandslocation.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder; // Importa PasswordEncoder
import org.springframework.stereotype.Component;

@Component
public class TagSeeder implements CommandLineRunner {

    @Autowired
    private TagRespository tagRespository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Insertar Tags directamente
        tagRespository.save(new Tag(1, "Agua Potable"));
        tagRespository.save(new Tag(2, "Luz"));
        tagRespository.save(new Tag(3, "Comida"));
        tagRespository.save(new Tag(4, "Ducha"));

        // Crear el usuario admin sin asignar un tipo
        String rawPassword = "12345";
        String encryptedPassword = passwordEncoder.encode(rawPassword);

        // Crear el usuario sin asignar tipo (type puede ser asignado manualmente después)
        userRepository.save(new User("admin", encryptedPassword, true, true, true, true, null));
    }
}
