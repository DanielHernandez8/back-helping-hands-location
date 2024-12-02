package com.helpinghandslocation.helpinghandslocation.services.impl;

import com.helpinghandslocation.helpinghandslocation.models.Type;
import com.helpinghandslocation.helpinghandslocation.repositories.TypeRepository;
import com.helpinghandslocation.helpinghandslocation.services.TypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeServices {
    @Autowired
    TypeRepository typeRepository;

    @Override
    public List<Type> getTypes() {
        return typeRepository.findAll();
    }
}
