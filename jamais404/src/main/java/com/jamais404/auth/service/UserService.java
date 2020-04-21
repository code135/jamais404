package com.jamais404.auth.service;

import com.jamais404.model.User;

public interface UserService {

    void save(User user);

    User findByEmail(String email);

    User findByUsername(String username);

}