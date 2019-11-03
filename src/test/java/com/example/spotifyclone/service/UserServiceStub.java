package com.example.spotifyclone.service;

import com.example.spotifyclone.controller.UserController;
import com.example.spotifyclone.models.Song;
import com.example.spotifyclone.models.User;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceStub implements UserService{

//    private UserController userController;

    @Override
    public Iterable<User> listUsers() {
        return null;
    }

    @Override
    public User deleteById(Long userId) {

        return null;
    }
    @Override
    public User addSongById(String username, int songId) {
        return null;
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public String login(User user) {
        return null;
    }

    @Override
    public String createUser(User newUser) {
        return null;
    }

    @Override
    public User addSong(String username, Song song) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }


}
