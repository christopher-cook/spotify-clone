package com.example.spotifyclone.UserControllerTest;

import com.example.spotifyclone.config.JwtUtil;
import com.example.spotifyclone.controller.UserController;
import com.example.spotifyclone.models.Song;
import com.example.spotifyclone.models.User;
import com.example.spotifyclone.models.UserRole;
import com.example.spotifyclone.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import java.io.UnsupportedEncodingException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private User user;
    private List<User> users;
    private Song song;
    private UserRole userRole;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;

    @InjectMocks
    UserController userController;

    @Before
    public void initialize() {
        user = new User();
        user.setId(1L);
        user.setUsername("test");
        //user.addSong(song);

        UserRole userRole = new UserRole();
        userRole.setName("ROLE_ADMIN");
        user.setUserRole(userRole);

        users = new ArrayList<User>();
    }

    @Before
    public void initializeSong() {
        song = new Song();

        song.setId(1);
        song.setTitle("title");
        song.setLength(2.0);
    }


    @Test
    public void helloWorld_ReturnsString_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }

    @Test
    public void login_200_Success() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("test","test"));

        when(userService.login(any())).thenReturn("123456");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"123456\"}"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    private static String createUserInJson (String name, String password) {
        return "{ \"name\": \"" + name + "\", " +
                "\"password\":\"" + password + "\"}";
    }

//    @Test
//    public void deleteUserById_Success() throws Exception {
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("/user/{userId}", 1);
//                //.header("Authorization", "Bearer 1234")
//                //.contentType(MediaType.APPLICATION_JSON);
//
//        //doNothing().when(userService.deleteById(any());)
//        Mockito.doThrow(new Exception()).when(userService.deleteById(any())).;
//
//        MvcResult result = mockMvc.perform(requestBuilder)
//                .andExpect((status().isOk()))
//                .andReturn();
//    }

    @Test
    public void signup_User_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("test","tester"));

        when(userService.createUser(any())).thenReturn("123456");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"123456\"}"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }


    //not working - throwing 401... without adding the mockuser - why? I created a new object
    @Test
    @WithMockUser(username = "batman", password = "bat", roles = {"ADMIN"})
    public void addSong_User_Success() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/{username}","batman")
                .header("Authorization", "Bearer 123456")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserSongInJson("test", 2.0));


        when(jwtUtil.getUsernameFromToken(any())).thenReturn("someUser");
        when(userService.addSong(any(), any())).thenReturn(user);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"username\":\"test\"}"))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    private static String createUserSongInJson(String title, Double length) {
        return "{ \"title\": \"" + title + "\", " + "\"length\":\"" + length + "\"}";
    }

    @Test
    @WithMockUser(username = "batman", password = "bat", roles = {"ADMIN"})
    public void listUser_ListOfUsers_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/list")
                .header("Authorization", "Bearer 123456")
                .accept(MediaType.APPLICATION_JSON);

        when(userService.listUsers()).thenReturn(users);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

    }
}
