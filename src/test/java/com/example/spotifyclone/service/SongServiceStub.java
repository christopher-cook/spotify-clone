package com.example.spotifyclone.service;

import com.example.spotifyclone.models.Song;
import com.example.spotifyclone.models.User;

public class SongServiceStub implements SongService {

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public Iterable<Song> listSongs() {
        return null;
    }

    @Override
    public Integer deleteSong(String username, int songId) {
        return null;
    }

    @Override
    public Song createSong(Song song) {
        return null;
    }
}


