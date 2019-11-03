package com.example.spotifyclone.SongControllerTest;

import com.example.spotifyclone.config.JwtUtil;
import com.example.spotifyclone.controller.SongController;
import com.example.spotifyclone.models.Song;
import com.example.spotifyclone.models.User;
import com.example.spotifyclone.service.SongService;
import com.example.spotifyclone.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(SongController.class)
public class SongControllerTest {

    private Song song;
    private List<Song> songs;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongService songService;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;

    @Before
    public void initialize() {
        User user = new User();
        songs = new ArrayList<Song>();
    }

    @Before
    public void initializeSong() {
        song = new Song();

        song.setId(1);
        song.setTitle("test");
        song.setLength(2.0);
    }

    @Test
    @WithMockUser(username = "batman", password = "bat", roles = {"ADMIN"})
    public void createSong_Song_SUCCESS() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/song")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer 123456")
                .content(createSongInJson("test", 2.0));

        when(songService.createSong(any())).thenReturn(song);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"title\":\"test\"}"))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    private static String createSongInJson(String title, Double length) {
        return "{ \"title\": \"" + title + "\", " + "\"length\":\"" + length + "\"}";
    }

    @Test
    @WithMockUser(username = "batman", password = "bat", roles = {"ADMIN"})
    public void listSongs_ListOfSongs_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/song/list")
                .header("Authorization", "Bearer 123456")
                .accept(MediaType.APPLICATION_JSON);

        when(songService.listSongs()).thenReturn(songs);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

    }
}
