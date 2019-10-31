package com.example.spotifyclone.service;

import com.example.spotifyclone.models.Song;
import com.example.spotifyclone.repositories.SongRepository;
import com.example.spotifyclone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spotifyclone.models.Song;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    SongRepository songRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Iterable<Song> listSongs() {
        return songRepository.findAll();
    }

//    @Override
//    public void deleteSong(int songId) {
//         songRepository.deleteSong(songId);
//    }
}
