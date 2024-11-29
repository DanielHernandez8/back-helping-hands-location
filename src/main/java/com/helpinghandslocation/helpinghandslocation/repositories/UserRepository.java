package com.helpinghandslocation.helpinghandslocation.repositories;

import com.helpinghandslocation.helpinghandslocation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
