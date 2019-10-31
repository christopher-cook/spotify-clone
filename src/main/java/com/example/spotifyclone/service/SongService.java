package com.example.spotifyclone.service;

import com.example.spotifyclone.models.Song;
import org.springframework.http.HttpStatus;

public interface SongService {

    public Song createSong(Song song);

    public Iterable<Song> listSongs();

    Song createUserSongList(String username, Song song);

    Song getSong(String username);

    HttpStatus deleteById(Integer userId);
}
