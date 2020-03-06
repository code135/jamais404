package com.jamais404.auth.service;

import com.jamais404.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
