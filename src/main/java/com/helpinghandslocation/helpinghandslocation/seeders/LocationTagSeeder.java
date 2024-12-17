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
            tagRespository.save(new Tag(16,"Ayuda a niños vulnerables"));
            tagRespository.save(new Tag(17,"Ayuda a mujeres vulnerables"));
            tagRespository.save(new Tag(18,"Punto Lila"));
            tagRespository.save(new Tag(19,"Juguetes"));
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


            Location comedorSocialReinaDeLaPaz = new Location (3L, "Comedor Social Reina de la Paz", 41.380066, 2.172430, "Plaça de Sant Agustí, 2, Ciutat Vella, 08001 Barcelona");
            comedorSocialReinaDeLaPaz.setTags(new ArrayList<Tag>(){{
                add(tagRespository.findById(1L).orElse(null));
                add(tagRespository.findById(2L).orElse(null));
                add(tagRespository.findById(4L).orElse(null));
                add(tagRespository.findById(9L).orElse(null));
            }});
            locationRepository.save(comedorSocialReinaDeLaPaz);

            Location comedorSocialDelParalelo = new Location(4L, "Comedor Social del Paralelo", 41.374785, 2.163638, "Av. del Paral·lel, 97B, Sants-Montjuïc, 08004 Barcelona");
            comedorSocialDelParalelo.setTags(new ArrayList<Tag>(){{
                add(tagRespository.findById(1L).orElse(null));
                add(tagRespository.findById(2L).orElse(null));
                add(tagRespository.findById(4L).orElse(null));
                add(tagRespository.findById(9L).orElse(null));
            }});
            locationRepository.save(comedorSocialDelParalelo);

            Location fundacioCatalanaDelEsplai = new Location(5L, "Fundació Catalana del Esplai", 41.315704, 2.082488, "Edifici Centre Esplai, Carrer del Riu Anoia, 44, 54, 08820 El Prat de Llobregat, Barcelona");
            fundacioCatalanaDelEsplai.setTags(new ArrayList<Tag>(){{
                add(tagRespository.findById(1L).orElse(null));
                add(tagRespository.findById(2L).orElse(null));
                add(tagRespository.findById(3L).orElse(null));
                add(tagRespository.findById(4L).orElse(null));
                add(tagRespository.findById(5L).orElse(null));
                add(tagRespository.findById(6L).orElse(null));
                add(tagRespository.findById(8L).orElse(null));
                add(tagRespository.findById(9L).orElse(null));
                add(tagRespository.findById(11L).orElse(null));
                add(tagRespository.findById(12L).orElse(null));
                add(tagRespository.findById(14L).orElse(null));
            }});
            locationRepository.save(fundacioCatalanaDelEsplai);

            Location fundacionMadrina = new Location(6L, "Fundacion Madrina", 40.462528, -3.695973, "C. del Limonero, 26, Tetuán, 28020 Madrid");
            fundacionMadrina.setTags(new ArrayList<Tag>(){{
                add(tagRespository.findById(3L).orElse(null));
                add(tagRespository.findById(17L).orElse(null));
                add(tagRespository.findById(18L).orElse(null));
                add(tagRespository.findById(19L).orElse(null));
            }});
            locationRepository.save(fundacionMadrina);

            Location comedorSocialSanJuanBautista = new Location(7L, "Comedor Social San Juan Bautista", 40.468560, -3.695973, "C. de la Costa Verde, 15, Tetuán, 28029 Madrid");
            comedorSocialSanJuanBautista.setTags(new ArrayList<Tag>(){{
                add(tagRespository.findById(1L).orElse(null));
                add(tagRespository.findById(2L).orElse(null));
                add(tagRespository.findById(3L).orElse(null));
                add(tagRespository.findById(4L).orElse(null));
                add(tagRespository.findById(5L).orElse(null));
                add(tagRespository.findById(7L).orElse(null));
                add(tagRespository.findById(9L).orElse(null));
                add(tagRespository.findById(14L).orElse(null));
                add(tagRespository.findById(19L).orElse(null));
            }});
            locationRepository.save(comedorSocialSanJuanBautista);

            Location ayudaViolenciaDeGenero = new Location(8L, "Ayuda Violencia de Genero", 41.485501, 2.035812, "Carrer de Pitàgores, 5, 08191 Rubí, Barcelona");
            ayudaViolenciaDeGenero.setTags(new ArrayList<Tag>(){{
                add(tagRespository.findById(4L).orElse(null));
                add(tagRespository.findById(17L).orElse(null));
                add(tagRespository.findById(18L).orElse(null));

            }});
            locationRepository.save(ayudaViolenciaDeGenero);

            Location ongAdan = new Location(9L, "ONG Adan", 41.461257, 2.174087, "Carrer de les Agudes, 100, Nou Barris, 08033 Barcelona");
            ongAdan.setTags(new ArrayList<Tag>(){{
                add(tagRespository.findById(1L).orElse(null));
                add(tagRespository.findById(2L).orElse(null));
                add(tagRespository.findById(3L).orElse(null));
                add(tagRespository.findById(4L).orElse(null));
                add(tagRespository.findById(5L).orElse(null));
                add(tagRespository.findById(9L).orElse(null));
                add(tagRespository.findById(12L).orElse(null));
                add(tagRespository.findById(16L).orElse(null));
                add(tagRespository.findById(19L).orElse(null));

            }});
            locationRepository.save(ongAdan);
        }
    }
}