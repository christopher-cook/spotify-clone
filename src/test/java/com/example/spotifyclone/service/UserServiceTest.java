package com.example.spotifyclone.service;

import com.example.spotifyclone.config.JwtUtil;
import com.example.spotifyclone.models.User;
import com.example.spotifyclone.repositories.SongRepository;
import com.example.spotifyclone.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRoleService userRoleService;

    @Mock
    private SongService songService;

    @Mock
    private SongRepository songRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private User user;

    @Before //create a test user to start with
    public void initializeDummyUser(){
        user.setUsername("chris");
        user.setPassword("pass");
        user.setId(1L);
    }

    @Test
    public void getUser_ReturnsUser_Success(){

        when(userRepository.findByUsername(anyString())).thenReturn(user); //we know we have a user, so mock the find and return dummyuser

        User tempUser = userService.getUser(user.getUsername());    //we inject userservice and can call getuser off a valid dummyuser object that has a username/password

        assertEquals(tempUser.getUsername(), user.getUsername());   //assert both are equal, which they are
    }

    @Test
    public void login_UserNotFound_Error(){

        when(userRepository.findByUsername(anyString())).thenReturn(null); //when we search by username, mock a null return

        String token = userService.login(user); //we use a null user object, and impl login method for a null result

        assertEquals(token, null);  //both are null values
    }

    @Test
    public void deleteUser_ById_Ok() {
//        when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.of(user));
        User deletedUser = userService.deleteById(user.getId());

         assertNull(deletedUser);
    }

}
