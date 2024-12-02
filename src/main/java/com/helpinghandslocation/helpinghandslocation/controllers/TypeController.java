package com.helpinghandslocation.helpinghandslocation.controllers;


import com.helpinghandslocation.helpinghandslocation.models.Type;
import com.helpinghandslocation.helpinghandslocation.services.TypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/types")
public class TypeController {

    @Autowired
    TypeServices typeServices;

    @GetMapping
    public ResponseEntity<?> getTypes() {
        try{
            List<Type> type = typeServices.getTypes();
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(type);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Error " +e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener los tipos " +e.getMessage());
        }
    }
    }


