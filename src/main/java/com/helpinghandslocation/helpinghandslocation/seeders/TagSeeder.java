package com.helpinghandslocation.helpinghandslocation.seeders;

import com.helpinghandslocation.helpinghandslocation.models.Tag;
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


    @Override
    public void run(String... args) throws Exception {
        if (tagRespository.count() == 0) {

            //Aqui los Tag
            tagRespository.save(new Tag(1, "Agua Potable"));
            tagRespository.save(new Tag(2, "Comida"));
            tagRespository.save(new Tag(3,"Medicina"));
            tagRespository.save(new Tag(4,"Primeros Auxilios"));
            tagRespository.save(new Tag(5, "Luz"));
            tagRespository.save(new Tag(6,"Cama"));
            tagRespository.save(new Tag(7,"Ropa"));
            tagRespository.save(new Tag(8,"Calefaccion"));
            tagRespository.save(new Tag(9,"WiFi"));
            tagRespository.save(new Tag(10,"Transporte"));
            tagRespository.save(new Tag(11, "Ducha"));
            tagRespository.save(new Tag(12,"Zona de juego"));
            tagRespository.save(new Tag(13,"Suministro de combustible"));
            tagRespository.save(new Tag(14,"Herramientas"));
            tagRespository.save(new Tag(15,"Cuidado de mascotas"));
        }
    }
}

