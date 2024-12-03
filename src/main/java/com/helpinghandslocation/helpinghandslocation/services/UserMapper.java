package com.helpinghandslocation.helpinghandslocation.services;

import com.helpinghandslocation.helpinghandslocation.dto.CurrentUserDTO;
import com.helpinghandslocation.helpinghandslocation.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Método para convertir un User a un CurrentUserDTO
    @Mapping(source = "type.id", target = "typeId") // Mapear el campo tipo ID
    CurrentUserDTO toCurrentUserDTO(User user);
}