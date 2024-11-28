package com.helpinghandslocation.helpinghandslocation.persistence.repositories;

import com.helpinghandslocation.helpinghandslocation.persistence.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
