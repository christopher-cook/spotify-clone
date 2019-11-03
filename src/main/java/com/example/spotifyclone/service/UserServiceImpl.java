package com.example.spotifyclone.service;

import com.example.spotifyclone.config.JwtUtil;
import com.example.spotifyclone.models.Song;
import com.example.spotifyclone.models.User;
import com.example.spotifyclone.models.UserRole;
import com.example.spotifyclone.repositories.SongRepository;
import com.example.spotifyclone.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    JwtUtil jwtUtil;

//    @Override
//    public String login(User user){
//        if(userRepository.login(user.getUsername(), user.getPassword()) != null){
//            UserDetails userDetails = loadUserByUsername(user.getUsername());
//            return jwtUtil.generateToken(userDetails);
//        }
//        return null;
//    }

    @Override
    public Iterable<User> listUsers() {
        System.out.println(userRepository.findAll());
        return userRepository.findAll();
    }

//    @Override
//    public User createUser(User newUser) {
//        return userRepository.save(newUser);
//    }

//    @Override
//    public User createUser(User newUser) {
//        UserRole userRole = userRoleService.getRole(newUser.getUserRole().getName());
//        newUser.setUserRole(userRole);
//        return userRepository.save(newUser);
//    }

//    @Override
//    public String createUser(User newUser) {
//        UserRole userRole = userRoleService.getRole(newUser.getUserRole().getName());
//        newUser.setUserRole(userRole);
//
//        if(userRepository.save(newUser) != null){
//            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
//            return jwtUtil.generateToken(userDetails);
//        }
//        return null;
//    }

//    @Override
//    public User login(String username, String password) {
//        return userRepository.login(username, password);
//    }

    @Override
    public String createUser(User newUser) {
        UserRole userRole = userRoleService.getRole(newUser.getUserRole().getName());
        newUser.setUserRole(userRole);
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        if(userRepository.save(newUser) != null){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }

    @Override
    public String login(User user){
        User newUser = userRepository.findByUsername(user.getUsername());

        if(newUser != null && bCryptPasswordEncoder.matches(user.getPassword(), newUser.getPassword())){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }


    @Override
    public User addSongById(String username, int songId) {
        Song song = songRepository.findById(songId).get();
        User user = getUser(username);
        user.addSong(song);

        return userRepository.save(user);
    }

    @Override
    public User addSong(String username, Song song) {
        User user = getUser(username);
        user.addSong(song);
//        userRepository.save(user);
//        return songRepository.save(song);
        songRepository.save(song);
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);

        if(user==null)
            throw new UsernameNotFoundException("User null");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, new ArrayList<>());
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));

        return authorities;
    }

}