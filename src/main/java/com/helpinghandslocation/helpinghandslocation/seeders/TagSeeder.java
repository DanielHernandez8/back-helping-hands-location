package com.helpinghandslocation.helpinghandslocation.seeders;

import com.helpinghandslocation.helpinghandslocation.persistence.entities.Tag;
import com.helpinghandslocation.helpinghandslocation.persistence.repositories.TagRespository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TagSeeder implements CommandLineRunner {
    @Autowired
    TagRespository tagRespository;


    @Override
    public void run(String... args) throws Exception {
        if (tagRespository.count() == 0) {
            tagRespository.save(new Tag(1L, "Agua Potable"));
            tagRespository.save(new Tag(2L, "Luz"));
            tagRespository.save(new Tag(3L, "Comida"));
            tagRespository.save(new Tag(4L, "Ducha"));
        }
    }
}
