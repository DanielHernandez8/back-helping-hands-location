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
    public ResponseEntity<List<Type>> getTypes() {
        try{
            List<Type> type = typeServices.getTypes();
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(type);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    }


