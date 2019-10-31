package com.example.spotifyclone.repositories;

import com.example.spotifyclone.models.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long> {

    @Query("FROM Song sp JOIN User u ON u.username = ?1 and sp.id = u.username.id")
  public Song findSongByUsername(String username);
}
