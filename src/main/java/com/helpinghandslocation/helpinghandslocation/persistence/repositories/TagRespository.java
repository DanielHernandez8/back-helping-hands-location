package com.helpinghandslocation.helpinghandslocation.persistence.repositories;

import com.helpinghandslocation.helpinghandslocation.persistence.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRespository extends JpaRepository<Tag, Integer> {
}
