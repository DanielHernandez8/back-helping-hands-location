package com.helpinghandslocation.helpinghandslocation.seeders;

import com.helpinghandslocation.helpinghandslocation.models.Location;
import com.helpinghandslocation.helpinghandslocation.models.Tag;
import com.helpinghandslocation.helpinghandslocation.repositories.LocationRepository;
import com.helpinghandslocation.helpinghandslocation.repositories.TagRespository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LocationTagSeeder implements CommandLineRunner {
    @Autowired
    TagRespository tagRespository;

    @Autowired
    LocationRepository locationRepository;


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

        if(locationRepository.count() == 0){
            Location fundacioRoureLocation = new Location (1L, "Fundació Roure", 41.386459, 2.180347, "Carrer dels Cecs de Sant Cugat, 1, Ciutat Vella, 08003 Barcelona");
            fundacioRoureLocation.setTags(new ArrayList<Tag>(){{
                add(tagRespository.findById(1L).orElse(null));
                add(tagRespository.findById(2L).orElse(null));
                add(tagRespository.findById(7L).orElse(null));
                add(tagRespository.findById(11L).orElse(null));
                add(tagRespository.findById(5L).orElse(null));
            }});
            locationRepository.save(fundacioRoureLocation);

            Location fundacionAltiusLocation = new Location (2L, "Fundacion Altius", 41.382648, 2.186254, "Carrer del Doctor Aiguader 5, 08003 Barcelona");
            fundacionAltiusLocation.setTags(new ArrayList<Tag>(){{
                add(tagRespository.findById(1L).orElse(null));
                add(tagRespository.findById(2L).orElse(null));
                add(tagRespository.findById(7L).orElse(null));
            }});

            locationRepository.save(fundacionAltiusLocation);
        }

    }
}

