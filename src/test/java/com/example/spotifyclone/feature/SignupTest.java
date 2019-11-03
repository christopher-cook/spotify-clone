package com.example.spotifyclone.feature;

import com.example.spotifyclone.models.User;
import com.example.spotifyclone.models.UserRole;
import com.example.spotifyclone.repositories.UserRepository;
import com.example.spotifyclone.repositories.UserRoleRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("qa")
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignupTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    UserRepository userRepository;

    private User user;

    @Test
    public void a_signup_User_Success() throws Exception {

        UserRole userRole = new UserRole();
        userRole.setName("ROLE_ADMIN");
        userRoleRepository.save(userRole);

        user = new User();
        user.setUsername("chris");
        user.setPassword("pass");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson(user.getUsername(), user.getPassword(), userRole.getName()));

        MvcResult token = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(token);
        System.out.println(token.getResponse().getContentAsString());

    }

    @Test
    @WithMockUser(username = "rando", password = "testPass", roles = {"ADMIN"})
    public void b_delete_User_Success() throws Exception {
        this.user = userRepository.findByUsername("chris");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/user/" + user.getId());

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

    }

    private static String createUserInJson (String name, String password, String roleName) {
        return "{ \"username\": \"" + name + "\", " +
                "\"password\":\"" + password + "\", " +
                "\"userRole\": { \"name\": \"" + roleName +"\" }}";
    }
}
