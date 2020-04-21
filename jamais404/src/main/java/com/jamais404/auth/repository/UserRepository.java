package com.jamais404.auth.repository;

import com.jamais404.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByEmail(String email);

    User findByUsername(String username);

}