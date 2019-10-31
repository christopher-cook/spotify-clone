package com.example.spotifyclone.service;

import com.example.spotifyclone.models.User;
import com.example.spotifyclone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User login(String username, String password) {
        return userRepository.login(username, password);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
}