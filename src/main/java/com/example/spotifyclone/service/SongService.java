package com.example.spotifyclone.service;

import com.example.spotifyclone.models.Song;
import org.springframework.http.HttpStatus;

public interface SongService {

    public Song createSong(Song song);

    public Iterable<Song> listSongs();

    Song getSong(String username);

    void deleteById(Integer userId);
}
