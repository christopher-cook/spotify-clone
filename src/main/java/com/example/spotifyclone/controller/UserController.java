package com.example.spotifyclone.controller;

import com.example.spotifyclone.models.JwtResponse;

import com.example.spotifyclone.models.Song;
import com.example.spotifyclone.models.User;
import com.example.spotifyclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/list")
    public Iterable<User> listUsers(){
        return userService.listUsers();
    }

    @Autowired
    private UserService userService;
//
//    @GetMapping("/hello")
//    public String hello() {
//        return "Hello World!";
//    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/list")
    public Iterable<User> listUsers() {
        return userService.listUsers();
    }

//    @PostMapping("/signup")
//    public User createUser(@RequestBody User newUser) {
//        return userService.createUser(newUser);
//    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        return ResponseEntity.ok(new JwtResponse(userService.createUser(newUser)));
    }

//    @GetMapping("/login/{username}/{password}")
//    public User login(@PathVariable String username, @PathVariable String password) {
//        return userService.login(username, password);
//    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
    return ResponseEntity.ok(new JwtResponse(userService.login(user)));
}



    @DeleteMapping("/user/{userId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) {
        userService.deleteById(userId);
        return HttpStatus.OK;
    }


    @PutMapping("/user/{username}/{songId}")
    public User addSong(@PathVariable String username, @PathVariable int songId){
        return userService.addSongById(username, songId);
    }

    @PostMapping("/user/{username}")
    public User addSong(@PathVariable String username, @RequestBody Song song){
        return userService.addSong(username, song);
    }
}
