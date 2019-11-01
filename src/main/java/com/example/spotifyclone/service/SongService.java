package com.example.spotifyclone.service;

import com.example.spotifyclone.models.Song;
import com.example.spotifyclone.models.User;

public interface SongService {

    public User getUser(String username);

    public Iterable<Song> listSongs();

    public Integer deleteSong(String username, int songId);

    public Song createSong(Song song);
}
