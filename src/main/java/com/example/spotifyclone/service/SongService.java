package com.example.spotifyclone.service;

import com.example.spotifyclone.models.Song;

public interface SongService {

    public Song addSong(Song song);

    public Iterable<Song> listSongs();

//    public void deleteSong(int songId);
}
