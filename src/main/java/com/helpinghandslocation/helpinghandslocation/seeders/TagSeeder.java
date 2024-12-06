package com.helpinghandslocation.helpinghandslocation.seeders;

import com.helpinghandslocation.helpinghandslocation.models.Tag;
import com.helpinghandslocation.helpinghandslocation.models.Type;
import com.helpinghandslocation.helpinghandslocation.repositories.TagRespository;
import com.helpinghandslocation.helpinghandslocation.repositories.TypeRepository;
import com.helpinghandslocation.helpinghandslocation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TagSeeder implements CommandLineRunner {
    @Autowired
    TagRespository tagRespository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        if (tagRespository.count() == 0) {

            //Aqui los Tag
            tagRespository.save(new Tag(1, "Agua Potable"));
            tagRespository.save(new Tag(2, "Luz"));
            tagRespository.save(new Tag(3, "Comida"));
            tagRespository.save(new Tag(4, "Ducha"));


            //aqui empieza los Type
            typeRepository.save(new Type(1, "Particular"));
            typeRepository.save(new Type(2, "Comercio"));

            //user

        }
    }
}
