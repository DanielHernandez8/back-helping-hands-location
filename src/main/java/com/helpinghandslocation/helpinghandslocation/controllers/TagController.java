package com.helpinghandslocation.helpinghandslocation.controllers;


import com.helpinghandslocation.helpinghandslocation.models.Tag;
import com.helpinghandslocation.helpinghandslocation.repositories.TagRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TagController{
    @Autowired
    TagRespository tagRespository;

    @GetMapping("/tags")
    public List<Tag> getTags() {
        List<Tag> tags = tagRespository.findAll();
        return tags;
    }

}