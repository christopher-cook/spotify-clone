package com.example.spotifyclone.controller;

import com.example.spotifyclone.config.JwtUtil;
import com.example.spotifyclone.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RestController;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

   @MockBean
    private UserService userService;

   @MockBean
    private JwtUtil jwtUtil;

   @Test
    public void login_Success() throws Exception {

       RequestBuilder requestBuilder = MockMvcRequestBuilders
               .post("/login")
               .contentType(MediaType.APPLICATION_JSON)
               .content(createUserInJson("chris","pass"));

       when(userService.login(any())).thenReturn("123456");

       MvcResult result = mockMvc.perform(requestBuilder)
               .andExpect(status().isOk())
               .andExpect(content().json("{\"token\":\"123456\"}"))
               .andReturn();
   }

   private static String createUserInJson(String name, String password){
       return "{ \"name\": \"" + name + "\", " +
               "\"password\":\"" + password + "\"}";
   }

}
