package com.helpinghandslocation.helpinghandslocation.dto;

import com.helpinghandslocation.helpinghandslocation.models.User;
import lombok.Data;

@Data
public class UserDTO extends User {
    private int typeId;
}
