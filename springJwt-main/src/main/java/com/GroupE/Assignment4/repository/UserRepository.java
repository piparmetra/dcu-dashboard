package com.GroupE.Assignment4.repository;

import com.GroupE.Assignment4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Optional<User> deleteByUsername(String username);
    Optional<User> findById(int id);
    
}
