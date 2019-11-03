package com.example.spotifyclone.service;

import com.example.spotifyclone.models.Song;
import com.example.spotifyclone.models.User;
import com.example.spotifyclone.repositories.SongRepository;
import com.example.spotifyclone.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SongServiceTest {

    @Mock
    SongRepository songRepository;

    @Mock
    private User user;

    @Mock
    private UserRepository userRepository;

    @Mock
    UserServiceImpl userService;

    @InjectMocks
    SongServiceImpl songService;

    @InjectMocks
    private Song song;
    

    @Before
    public void initTestSong() {
        ArrayList<Song> songList = new ArrayList<>();
        song.setTitle("Cigarette Daydreams");
        song.setLength(200D);
        song.setId(1);
        songList.add(song);
    }

    @Test
    public void getSongList_ReturnList_Success() {
        ArrayList<Song> songList = new ArrayList<>();
        when(songRepository.findAll()).thenReturn(songList);
       ArrayList<Song> actualSongs = (ArrayList<Song>) songService.listSongs();

        assertNotNull(songList);
    }

//    @Test
//    public void deleteSong_FromUser_Success() {
////        ArrayList<Song> songList = new ArrayList<>();
////        when(userRepository.findByUsername(anyString())).thenReturn(user);  //we get a user object
//        when(songRepository.findById(anyInt())).thenReturn(java.util.Optional.of(song)); //search db and get song
//        when(userService.addSongById(anyString(), anyInt())).thenReturn(user);   //add song to user object
//
//
//        Integer deletedSong = songService.deleteSong(user.getUsername(), song.getId());
//
//        assertNull(user.getSongs());
//    }

}
