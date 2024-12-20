package com.helpinghandslocation.helpinghandslocation.services.impl;

import com.helpinghandslocation.helpinghandslocation.dto.LocationTagDTO;
import com.helpinghandslocation.helpinghandslocation.dto.response.LocationCreatorDTO;
import com.helpinghandslocation.helpinghandslocation.mappers.LocationMapper;
import com.helpinghandslocation.helpinghandslocation.models.Location;
import com.helpinghandslocation.helpinghandslocation.models.Tag;
import com.helpinghandslocation.helpinghandslocation.models.User;
import com.helpinghandslocation.helpinghandslocation.repositories.LocationRepository;
import com.helpinghandslocation.helpinghandslocation.repositories.TagRespository;
import com.helpinghandslocation.helpinghandslocation.repositories.UserRepository;
import com.helpinghandslocation.helpinghandslocation.services.LocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServicesImpl implements LocationServices {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private TagRespository tagRespository;

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private LocationMapper locationMapper;

    @Override
    public LocationTagDTO createLocation(LocationTagDTO locationTagDTO) {
        User currentUser = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Validar que el ID no sea proporcionado en la creación
        if (locationTagDTO.getId() != null) {
            throw new RuntimeException("El ID no debe proporcionarse en la creación");
        }

        if(authentication != null && authentication.isAuthenticated()){
            currentUser = userRepository.findByUsername(authentication.getName());
        }

        // Crear nueva ubicación
        Location location = new Location();
        location.setName(locationTagDTO.getName());
        location.setLatitude(locationTagDTO.getLatitude());
        location.setLongitude(locationTagDTO.getLongitude());
        location.setAddress(locationTagDTO.getAddress());
        location.setCreatedBy(currentUser);

        // Asociar los tags
        for (Long tagId : locationTagDTO.getTagIds()) {
            Tag tag = tagRespository.findById(tagId)
                    .orElseThrow(() -> new RuntimeException("Tag no encontrado con ID: " + tagId));
            location.getTags().add(tag);
        }

        // Guardar la ubicación en la base de datos
        Location locationTarget = locationRepository.save(location);

        // Mapear la entidad guardada al DTO de respuesta
        LocationTagDTO responseDTO = new LocationTagDTO();
        responseDTO.setId(locationTarget.getId()); // ID generado
        responseDTO.setName(locationTarget.getName());
        responseDTO.setLatitude(locationTarget.getLatitude());
        responseDTO.setLongitude(locationTarget.getLongitude());
        responseDTO.setTagIds(locationTarget.getTags().stream().map(Tag::getId).toList());

        return responseDTO;
    }

    @Override
    public List<LocationCreatorDTO> getLocations(List<Long> tagIds) {

        if(tagIds ==null || tagIds.isEmpty()){
            return locationMapper.locationTagCreatorDTOList(locationRepository.findAll());
        }
        return locationMapper.locationTagCreatorDTOList(locationRepository.findByTagsIdInAll(tagIds));

    }

    @Override
    public LocationTagDTO updateLocation(Long id, LocationTagDTO locationTagDTO) {
        // Buscar la ubicación por ID
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada con ID: " + id));

        // Actualizar los datos de la ubicación
        location.setName(locationTagDTO.getName());
        location.setLatitude(locationTagDTO.getLatitude());
        location.setLongitude(locationTagDTO.getLongitude());

        // Limpiar y actualizar los tags asociados
        location.getTags().clear();
        for (Long tagId : locationTagDTO.getTagIds()) {
            Tag tag = tagRespository.findById(tagId)
                    .orElseThrow(() -> new RuntimeException("Tag no encontrado con ID: " + tagId));
            location.getTags().add(tag);
        }

        // Guardar los cambios
        Location locationUpdated = locationRepository.save(location);

        // Mapear la entidad actualizada al DTO de respuesta
        LocationTagDTO responseDTO = new LocationTagDTO();
        responseDTO.setId(locationUpdated.getId()); // ID actualizado
        responseDTO.setName(locationUpdated.getName());
        responseDTO.setLatitude(locationUpdated.getLatitude());
        responseDTO.setLongitude(locationUpdated.getLongitude());
        responseDTO.setTagIds(locationUpdated.getTags().stream().map(Tag::getId).toList());

        return responseDTO;
    }

    @Override
    public void deleteLocation(Long id) {

        Location location = new Location();

        location.setId(id);

        locationRepository.delete(location);
    }
}
