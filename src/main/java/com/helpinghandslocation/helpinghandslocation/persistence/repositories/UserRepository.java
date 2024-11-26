package com.helpinghandslocation.helpinghandslocation.persistence.repositories;

import com.helpinghandslocation.helpinghandslocation.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
