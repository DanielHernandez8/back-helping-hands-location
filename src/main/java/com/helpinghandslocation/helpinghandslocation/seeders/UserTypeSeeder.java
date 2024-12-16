package com.helpinghandslocation.helpinghandslocation.seeders;

import com.helpinghandslocation.helpinghandslocation.models.Type;
import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.repositories.TagRespository;
import com.helpinghandslocation.helpinghandslocation.repositories.TypeRepository;
import com.helpinghandslocation.helpinghandslocation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserTypeSeeder implements CommandLineRunner {
    @Autowired
    TagRespository tagRespository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

            //aqui empieza los Type
            Type particular = new Type(1L, "Particular");
            typeRepository.save(particular);
            typeRepository.save(new Type(2L, "Comercio"));

            //user
            if (userRepository.findByUsername("admin") == null) {

                userRepository.save(new User(
                    1L,
                    "admin",
                    passwordEncoder.encode("admin"),
                    "admin@example.com",
                    "Admin",
                    "User",
                    "+34 623456890",
                    true,
                    true,
                    true,
                    true,
                        particular
            ));

        }
    }
}

