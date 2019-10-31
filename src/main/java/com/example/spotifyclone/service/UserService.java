package com.example.spotifyclone.service;

import com.example.spotifyclone.models.User;

public interface UserService {

    public Iterable<User> listUsers();
    public User createUser(User newUser);
    public User login(String username, String password);
    public void deleteById(Long userId);
    public User addSong(String username, int songId);
    public User getUser(String username);
}