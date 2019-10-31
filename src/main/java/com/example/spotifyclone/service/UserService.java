package com.example.spotifyclone.service;

import com.example.spotifyclone.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public Iterable<User> listUsers();
//    public User createUser(User newUser);
//    public User login(String username, String password);
    public void deleteById(Long userId);
    public User addSong(String username, int songId);
    public User getUser(String username);
    public String login(User user);
    public String createUser(User newUser);
}