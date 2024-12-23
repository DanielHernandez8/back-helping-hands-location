package com.helpinghandslocation.helpinghandslocation.services;

import com.helpinghandslocation.helpinghandslocation.dto.request.RegisterUserRequestDTO;
import com.helpinghandslocation.helpinghandslocation.dto.response.CurrentUserResponseDTO;
import com.helpinghandslocation.helpinghandslocation.models.User;

public interface UserServices {
    CurrentUserResponseDTO getCurrentUser();
    User createUser(RegisterUserRequestDTO registerUserRequestDTO);


}
