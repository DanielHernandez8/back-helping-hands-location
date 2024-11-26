package com.helpinghandslocation.helpinghandslocation.persistence.repositories;

import com.helpinghandslocation.helpinghandslocation.persistence.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
