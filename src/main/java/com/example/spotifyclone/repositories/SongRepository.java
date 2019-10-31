package com.example.spotifyclone.repositories;

import com.example.spotifyclone.models.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Integer> {
//    public Song deleteSong(Integer songId);
}
