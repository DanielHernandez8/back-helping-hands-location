package com.helpinghandslocation.helpinghandslocation.mappers;

import com.helpinghandslocation.helpinghandslocation.dto.response.CurrentUserResponseDTO;
import com.helpinghandslocation.helpinghandslocation.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Método para convertir un User a un CurrentUserDTO
    @Mapping(source = "type.id", target = "typeId") // Mapear el campo tipo ID
    CurrentUserResponseDTO toCurrentUserDTO(User user);
}