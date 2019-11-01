package com.example.spotifyclone.service;

import com.example.spotifyclone.models.UserRole;

public interface UserRoleService {

    public UserRole createRole(UserRole newRole);

    public UserRole getRole(String roleName);
}