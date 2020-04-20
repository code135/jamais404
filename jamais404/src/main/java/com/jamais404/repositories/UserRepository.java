package com.jamais404.repositories;

import com.jamais404.models.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    
    User findByName(String name);

}