package com.example.spotifyclone.controller;

import com.example.spotifyclone.models.User;
import com.example.spotifyclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/user/list")
    public Iterable<User> listUsers() {
        return userService.listUsers();
    }


}
