package com.helpinghandslocation.helpinghandslocation.services.dto;

import com.helpinghandslocation.helpinghandslocation.persistence.entities.User;
import lombok.Data;

@Data
public class UserDTO extends User {
    private int typeId;
}
