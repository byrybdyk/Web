package com.example.repositories;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    User getUserByUsernameAndPassword(String username, String password);
    UserDetails findUserByUsername(String username);
}