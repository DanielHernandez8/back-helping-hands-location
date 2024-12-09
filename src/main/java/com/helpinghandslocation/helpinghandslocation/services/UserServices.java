package com.helpinghandslocation.helpinghandslocation.services;

import com.helpinghandslocation.helpinghandslocation.dto.request.RegisterUserRequestDTO;
import com.helpinghandslocation.helpinghandslocation.dto.response.CurrentUserResponseDTO;
import com.helpinghandslocation.helpinghandslocation.models.User;

public interface UserServices {
    User createUser(RegisterUserRequestDTO registerUserRequestDTO);
    CurrentUserResponseDTO getCurrentUser();

}
