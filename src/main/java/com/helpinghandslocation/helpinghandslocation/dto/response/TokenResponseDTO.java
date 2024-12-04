package com.helpinghandslocation.helpinghandslocation.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TokenResponseDTO implements Serializable {
    private String token;
}
