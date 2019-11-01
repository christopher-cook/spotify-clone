package com.example.spotifyclone.service;

import com.example.spotifyclone.models.Song;
import com.example.spotifyclone.models.User;
import com.example.spotifyclone.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    SongRepository songRepository;

    @Autowired
    UserService userService;

    @Override
    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Iterable<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song getSong(String username) {
        return null;
    }

    @Override
    public void deleteById(Integer userId) {
        songRepository.deleteById(userId);
    }
}
