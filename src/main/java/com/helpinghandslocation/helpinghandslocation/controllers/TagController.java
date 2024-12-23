package com.helpinghandslocation.helpinghandslocation.controllers;


import com.helpinghandslocation.helpinghandslocation.models.Tag;
import com.helpinghandslocation.helpinghandslocation.services.TagServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tags")
public class TagController{
    @Autowired
    TagServices tagServices;


    @GetMapping
    public ResponseEntity<?> getTags() {
        try{
        List<Tag> tags = tagServices.getTags();
        return ResponseEntity.status(200).body(tags);
    }catch (IllegalStateException e) {
        return ResponseEntity.badRequest().body("Error debes iniciar sesión " +e.getMessage());
    }catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener las etiquetas " +e.getMessage());
        }
    }
}