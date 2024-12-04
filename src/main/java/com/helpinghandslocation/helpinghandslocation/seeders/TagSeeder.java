package com.helpinghandslocation.helpinghandslocation.seeders;

import com.helpinghandslocation.helpinghandslocation.models.Tag;
import com.helpinghandslocation.helpinghandslocation.models.Type;
import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.repositories.TagRespository;
import com.helpinghandslocation.helpinghandslocation.repositories.TypeRepository;
import com.helpinghandslocation.helpinghandslocation.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TagSeeder implements CommandLineRunner {
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
        if (tagRespository.count() == 0) {

            //Aqui los Tag
            tagRespository.save(new Tag(1L, "Agua Potable"));
            tagRespository.save(new Tag(2L, "Luz"));
            tagRespository.save(new Tag(3L, "Comida"));
            tagRespository.save(new Tag(4L, "Ducha"));
            tagRespository.save(new Tag(5L,"Medicina"));
            tagRespository.save(new Tag(6L,"Cama"));
            tagRespository.save(new Tag(7L,"Ropa"));
            tagRespository.save(new Tag(8L,"Calefaccion"));
            tagRespository.save(new Tag(9L,"WiFi"));
            tagRespository.save(new Tag(10L,"Transporte"));
            tagRespository.save(new Tag(11L,"Primeros Auxilios"));
            tagRespository.save(new Tag(12L,"Zona de juego"));
            tagRespository.save(new Tag(13L,"Suministro de combustible"));
            tagRespository.save(new Tag(14L,"Herramientas"));
            tagRespository.save(new Tag(15L,"Cuidado de mascotas"));


            //aqui empieza los Type

            Type particular = new Type(1L, "Particular");
            typeRepository.save(particular);
            typeRepository.save(new Type(2L, "Comercio"));

            //user

            if (userRepository.findByUsername("admin") == null) {

                userRepository.save(new User(
                    1L,
                    "admin",
                    passwordEncoder.encode("12345"),
                    "admin@example.com",
                    "Admin",
                    "User",
                    123456789,
                    true,
                    true,
                    true,
                    true,
                        particular


            ));

        }
    }
}
}
