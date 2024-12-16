package com.helpinghandslocation.helpinghandslocation.controllers;

import com.helpinghandslocation.helpinghandslocation.models.Type;
import com.helpinghandslocation.helpinghandslocation.services.TypeServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Extiende con MockitoExtension
public class TypeControllerTest {

    @Mock
    private TypeServices typeServices;

    @InjectMocks
    private TypeController typeController;

    @BeforeEach
    void setUp() {
        // Ya no es necesario llamar a MockitoAnnotations.openMocks(this)
    }

    @Test
    public void getTypesSuccess() {
        Type type1 = new Type(1L, "Particular");
        Type type2 = new Type(2L, "Comercio");
        List<Type> mockTypes = Arrays.asList(type1, type2);

        when(typeServices.getTypes()).thenReturn(mockTypes);

        ResponseEntity<?> response = typeController.getTypes();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockTypes, response.getBody());
    }

    @Test
    public void testGetTypesException() {
        when(typeServices.getTypes()).thenThrow(new RuntimeException("Error al obtener los tipos"));
        ResponseEntity<?> response = typeController.getTypes();
        assertEquals(500, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Error al obtener los tipos"));
    }
}
