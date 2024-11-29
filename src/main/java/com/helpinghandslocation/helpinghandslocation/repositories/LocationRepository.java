package com.helpinghandslocation.helpinghandslocation.repositories;

import com.helpinghandslocation.helpinghandslocation.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
