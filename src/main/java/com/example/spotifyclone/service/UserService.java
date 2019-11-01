package com.example.spotifyclone.service;

import com.example.spotifyclone.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public Iterable<User> listUsers();
    public String createUser(User newUser);
    public void deleteById(Long userId);

    User getUser(String username);
    public User addSong(String username, int songId);
    public String login(User user);
    public User deleteSong(String username, int songId);

}