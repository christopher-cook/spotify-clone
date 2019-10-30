package com.example.spotifyclone.service;

import com.example.spotifyclone.models.User;

public interface UserService {

    public Iterable<User> listUsers();
}