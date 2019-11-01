package com.example.spotifyclone.service;

import com.example.spotifyclone.models.Song;
import com.example.spotifyclone.models.User;
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
    public User getUser(String username) {
        return userRepository.findByUsername(username);

    }

    @Override
    public Iterable<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override

    public Integer deleteSong(String username, int songId) {
        User user = getUser(username);
        Song song = songRepository.findById(songId).get();
        user.getSongs().remove(song);
        userRepository.save(user);
        songRepository.delete(song);
        return songId;
    }

    @Override
    public Song createSong(Song song) {
        return songRepository.save(song);

    public Song getSong(String username) {
        return null;
    }

    @Override
    public void deleteById(Integer userId) {
        songRepository.deleteById(userId);

    }
}
