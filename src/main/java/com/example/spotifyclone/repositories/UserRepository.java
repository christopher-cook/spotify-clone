package com.example.spotifyclone.repositories;

import com.example.spotifyclone.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}